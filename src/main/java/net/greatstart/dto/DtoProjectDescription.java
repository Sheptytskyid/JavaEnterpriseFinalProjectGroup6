package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DtoProjectDescription {
    @NotNull
    @Size(min = 3, max = 35, message = "{name.size}")
    private String name;
    private String goal;
    private String description;
    @DecimalMin(value = "1000.00", message = "{project.cost.min}")
    private BigDecimal cost;
    private BigDecimal minInvestment;
    private LocalDate dateStart;
    private LocalDate dateAdded;
    private byte[] image;
}
