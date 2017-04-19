package net.greatstart.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactTest {
    private Contact contact;

    @Before
    public void setUp() {
        contact = new Contact("StreetTest", "123456");
    }

    @Test
    public void addressShouldEqual() {
        assertEquals("StreetTest", contact.getAddress());
    }

    @Test
    public void phoneShouldEqual() {
        assertEquals("123456", contact.getPhoneNumber());
    }

}