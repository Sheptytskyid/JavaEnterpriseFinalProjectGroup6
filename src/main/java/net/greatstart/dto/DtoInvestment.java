package net.greatstart.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoInvestment {
    long id;
    LocalDateTime dateOfInvestment;
    DtoProject project;
    DtoUserProfile investor;
    @NotNull
    BigDecimal sum;
    boolean verified;
    boolean paid;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DtoInvestment that = (DtoInvestment) obj;

        if (id != that.id) {
            return false;
        }
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
        if (investor != null ? !investor.equals(that.investor) : that.investor != null) {
            return false;
        }
        return sum != null ? sum.equals(that.sum) : that.sum == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (dateOfInvestment != null ? dateOfInvestment.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (investor != null ? investor.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (verified ? 1 : 0);
        result = 31 * result + (paid ? 1 : 0);
        return result;
    }
}
