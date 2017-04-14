package net.greatstart.model;

import org.springframework.stereotype.Component;

@Component
public class Contact {
    private String address;
    private String phoneNumber;

    public Contact(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
