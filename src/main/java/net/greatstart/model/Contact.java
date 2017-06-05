package net.greatstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * An entity class to provide contact information (like address and phone number).
 */

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phoneNumber;

}
