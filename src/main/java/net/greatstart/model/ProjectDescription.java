package net.greatstart.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

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

    @Column(name = "min_invest")
    private int minInvestment;

    @Column(name = "verified")
    private boolean isVerifed;


}
