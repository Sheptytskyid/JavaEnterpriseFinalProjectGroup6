package net.greatstart.model;

import lombok.Data;

@Data
public class Contact {
    private String address;
    private String phoneNumber;

    public Contact(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
