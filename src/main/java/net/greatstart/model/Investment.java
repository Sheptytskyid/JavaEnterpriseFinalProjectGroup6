package net.greatstart.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "investments")
public class Investment extends AbstractModel {

    @Column(name = "date")
    LocalDateTime dateOfInvestment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    User investor;

    @Column(name = "sum")
    BigDecimal sum;

    @Column(name = "verified")
    boolean verified;

    @Column(name = "paid")
    boolean paid;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        Investment that = (Investment) obj;

        if (verified != that.verified) {
            return false;
        }
        if (paid != that.paid) {
            return false;
        }
        if (dateOfInvestment != null ? !dateOfInvestment.equals(that.dateOfInvestment) : that.dateOfInvestment != null) {
            return false;
        }
        if (project != null ? !project.equals(that.project) : that.project != null) {
            return false;
        }
        if (investor.getId() != 0 ? investor.getId() != that.investor.getId() : that.investor.getId() != 0) {
            return false;
        }
        return sum != null ? sum.equals(that.sum) : that.sum == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateOfInvestment != null ? dateOfInvestment.hashCode() : 0);
        result = 31 * result + (project.getId() != 0 ? project.hashCode() : 0);
        result = 31 * result + (investor.getId() != 0 ? investor.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (verified ? 1 : 0);
        result = 31 * result + (paid ? 1 : 0);
        return result;
    }
}
