package com.cezary.phonebook;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    private Optional<Contact> findContact(int id) {
        return contactRepository.findById(id);
    }

    public void addContact(String name, String phoneNumber, String email) {
        Contact contact = new Contact(generateContactId(), name, phoneNumber, email);

        contactRepository.save(contact);
    }

    private int generateContactId() {
        return getContacts().stream().map(Contact::getId).max(Integer::compareTo).orElse(0) + 1;
    }

    public void updateName(int id, String name) {
        findContact(id).ifPresent(contact -> contactRepository.save(contact.withName(name)));
    }

    public void updatePhoneNumber(int id, String phoneNumber) {
        findContact(id).ifPresent(contact -> contactRepository.save(contact.withPhoneNumber(phoneNumber)));
    }

    public void updateEmail(int id, String email) {
        findContact(id).ifPresent(contact -> contactRepository.save(contact.withEmail(email)));
    }

    public void removeContact(int id) {
        contactRepository.deleteById(id);
    }

    public int getContactsSize() {
        return getContacts().size();
    }

    public Contact getContact(String name) {
        Optional<Contact> maybeContact = contactRepository.findByName(name);

        if (maybeContact.isPresent()) {
            return maybeContact.get();
        } else {
            throw new RuntimeException("Contact not found");
        }
    }
}

