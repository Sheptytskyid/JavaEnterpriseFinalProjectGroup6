package net.greatstart.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProjectDescription {

    private LocalDate date;
    private String description;
    private String goal;
    private String other;
}
