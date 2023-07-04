package com.cezary.phonebook;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PhonebookController {
    private final ContactService contactService;

    @GetMapping("/contacts")
    List<Contact> getContacts() {
        return contactService.getContacts();
    }

    @PostMapping("/contact")
    void addContact(@RequestBody ContactDto contactDto) {
        contactService.addContact(contactDto.name, contactDto.phoneNumber, contactDto.email);
    }

    @DeleteMapping("/contact")
    void deleteContact(@RequestParam("id") int id) {
        contactService.removeContact(id);
    }

    @GetMapping("/contact")
    Contact getContact(@RequestParam("name") String name) {
        return contactService.getContact(name);
    }

    @PatchMapping("/contact/update")
    void updateContact(@RequestParam("id") int id, @RequestBody ContactDto contactDto) {
        if (contactDto.name != null) {
            contactService.updateName(id, contactDto.name);
        }
        if (contactDto.phoneNumber != null) {
            contactService.updatePhoneNumber(id, contactDto.phoneNumber);
        }
        if (contactDto.email != null) {
            contactService.updateEmail(id, contactDto.email);
        }
    }

    @GetMapping("/contacts/size")
    int getContactsSize() {
        return contactService.getContactsSize();
    }

    @Value
    public static class ContactDto {
        String name;
        String phoneNumber;
        String email;
    }
}
