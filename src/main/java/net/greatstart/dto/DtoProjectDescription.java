package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Data
public class DtoProjectDescription {
    @NotNull
    @Size(min = 3, max = 105, message = "{name.size}")
    private String name;
    private String goal;
    private String description;
    @DecimalMin(value = "1000.00", message = "{project.cost.min}")
    private BigDecimal cost;
    private BigDecimal minInvestment;
    private LocalDate dateStart;
    private LocalDate dateAdded;
    private String other;
    private byte[] image;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DtoProjectDescription that = (DtoProjectDescription) obj;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (goal != null ? !goal.equals(that.goal) : that.goal != null) {
            return false;
        }
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) {
            return false;
        }
        if (minInvestment != null ? !minInvestment.equals(that.minInvestment) : that.minInvestment != null) {
            return false;
        }
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) {
            return false;
        }
        if (dateAdded != null ? !dateAdded.equals(that.dateAdded) : that.dateAdded != null) {
            return false;
        }
        return Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (minInvestment != null ? minInvestment.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateAdded != null ? dateAdded.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
