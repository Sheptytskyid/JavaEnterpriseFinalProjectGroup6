package net.greatstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Contact {

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phoneNumber;

}
