package net.greatstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "investments")
public class Investment extends AbstractModel {
    @Column(name = "date")
    private LocalDateTime dateOfInvestment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User investor;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "paid")
    private boolean paid;
}
