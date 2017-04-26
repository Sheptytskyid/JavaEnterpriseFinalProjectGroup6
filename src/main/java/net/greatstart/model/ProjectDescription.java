package net.greatstart.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Embeddable
public class ProjectDescription {

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "description")
    private String description;

    @Column(name = "date_added")
    private LocalDate addDate;

    @Column(name = "date_start")
    private LocalDate addStart;

    @Column(name = "min_invest")
    private int minInvestment;

    @Column(name = "verified")
    private boolean isVerifed;

    @Column(name = "goal")
    private String goal;

    @Column(name = "other")
    private String other;
    @Column(name = "logo_url")
    private String logotype;

}