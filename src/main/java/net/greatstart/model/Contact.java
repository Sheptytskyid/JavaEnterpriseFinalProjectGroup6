package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Component
@Embeddable
public class Contact {
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phoneNumber;

    public Contact(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact() {
    }

}

