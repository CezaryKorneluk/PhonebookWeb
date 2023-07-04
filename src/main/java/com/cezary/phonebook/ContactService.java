package com.cezary.phonebook;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactService {
    private final ContactRepository contactRepository = new ContactRepository();

    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    public void addContact(String name, String phoneNumber, String email) {
        Contact contact = new Contact(generateContactId(), name, phoneNumber, email);

        contactRepository.addContact(contact);
    }

    private int generateContactId() {
        return contactRepository.getContacts().stream().map(Contact::getId).max(Integer::compareTo).orElse(0) + 1;
    }

    public void updateName(int id, String name) {
        Optional<Contact> maybeContact = contactRepository.getContactById(id);

        maybeContact.ifPresent(contact -> contactRepository.updateContact(contact.withName(name)));
    }

    public void updatePhoneNumber(int id, String phoneNumber) {
        Optional<Contact> maybeContact = contactRepository.getContactById(id);

        maybeContact.ifPresent(contact -> contactRepository.updateContact(contact.withPhoneNumber(phoneNumber)));
    }

    public void updateEmail(int id, String email) {
        Optional<Contact> maybeContact = contactRepository.getContactById(id);

        maybeContact.ifPresent(contact -> contactRepository.updateContact(contact.withEmail(email)));
    }

    public void removeContact(int id) {
        contactRepository.removeById(id);
    }

    public int getContactsSize() {
        return contactRepository.getContacts().size();
    }

    public Contact getContact(String name) {
        Optional<Contact> maybeContact = contactRepository.getContactByName(name);

        if (maybeContact.isPresent()) {
            return maybeContact.get();
        } else {
            throw new RuntimeException("Contact not found");
        }
    }
}

