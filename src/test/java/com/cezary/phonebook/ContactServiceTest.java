package com.cezary.phonebook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;
    private ContactService contactService;

    @BeforeEach
    void initService() {
        contactService = new ContactService(contactRepository);
    }

    @Test
    void shouldAddNewContact() {
        //given
        when(contactRepository.findAll()).thenReturn(List.of());
        Contact expectedContact = new Contact(1, "name", "123", "a@gmail.com");

        // when
        contactService.addContact("name", "123", "a@gmail.com");

        // then
        verify(contactRepository, times(1)).save(expectedContact);
    }

    @Test
    void shouldRemoveContact() {
        // when
        contactService.addContact("name", "123", "a@gmail.com");
        contactService.removeContact(1);

        // then
        final var contacts = contactService.getContacts();
        assertEquals(0, contacts.size());
    }

    @Test
    void shouldUpdateContactName() {
        // when
        contactService.addContact("name", "123", "a@gmail.com");
        contactService.updateName(1, "surname");

        // then
        final var contacts = contactService.getContacts();
        assertEquals("surname", contacts.get(0).getName());
    }

    @Test
    void shouldUpdateContactPhoneNumber() {
        // when
        contactService.addContact("name", "123", "a@gmail.com");
        contactService.updatePhoneNumber(1, "312");

        // then
        final var contacts = contactService.getContacts();
        assertEquals("312", contacts.get(0).getPhoneNumber());
    }

    @Test
    void shouldUpdateContactEmail() {
        // when
        contactService.addContact("name", "123", "a@gmail.com");
        contactService.updateEmail(1, "b@hotmail.com");

        // then
        final var contacts = contactService.getContacts();
        assertEquals("b@hotmail.com", contacts.get(0).getEmail());
    }

    @Test
    void shouldGenerateId() {
        //when
        contactService.addContact("name", "123", "a@gmail.com");
        contactService.addContact("name", "123", "a@gmail.com");
        contactService.addContact("name", "123", "a@gmail.com");
        contactService.removeContact(2);
        contactService.addContact("test", "123", "a@gmail.com");

        //then
        final var contact = contactService.getContact("test");
        assertEquals(4, contact.getId());
    }
}