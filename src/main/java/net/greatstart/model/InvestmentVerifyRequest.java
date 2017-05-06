package net.greatstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * {@link }
 *
 * @author Oleksii Polite Rudenko
 * @version 1.0
 */

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentVerifyRequest extends AbstractModel {

    @ManyToOne
    @Column(name = "admin_verify_id")
    private User admin;

    @Column(name = "verify_date")
    private LocalDate verifyDate;
}
