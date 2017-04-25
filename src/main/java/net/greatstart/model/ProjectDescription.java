package net.greatstart.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDescription {

    private LocalDate date;
    private String description;
    private String goal;
    private String other;
}
