package net.greatstart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class DtoProject {
    private Long id;
    @NotNull
    @Size(min = 3, max = 35, message = "{name.size}")
    private String name;
    private String goal;
    private String description;
    private String category;
    private Integer cost;
    private Integer minInvestment;
    private LocalDate dateStart;
    private byte[] image;

}
