package com.cezary.phonebook;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepository {
    private final List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void removeById(int id) {
        contacts.stream()
                .filter(contact -> contact.getId() == id)
                .findAny().ifPresent(contacts::remove);
    }

    public void updateContact(Contact toUpdate) {
        contacts.stream()
                .filter(contact -> contact.getId() == toUpdate.getId())
                .findAny().ifPresent(contact -> {
                    contacts.remove(contact);
                    addContact(toUpdate);
                });
    }

    public Optional<Contact> getContactByName(String name) {
        return contacts.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Optional<Contact> getContactById(int id) {
        return contacts.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();
    }
}
