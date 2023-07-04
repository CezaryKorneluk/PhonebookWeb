package com.cezary.phonebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

@With
@Getter
@AllArgsConstructor
public class Contact {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
}
