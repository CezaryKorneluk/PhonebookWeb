package com.cezary.phonebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private final ContactService contactService = new ContactService();

    @Test
    void shouldAddNewContact() {
        // when
        contactService.addContact("name", "123", "a@gmail.com");

        // then
        final var contacts = contactService.getContacts();
        assertEquals(1, contacts.size());
        assertEquals(1, contacts.get(0).getId());
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