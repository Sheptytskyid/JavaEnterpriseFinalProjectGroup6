package net.greatstart.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project extends AbstractModel {

    @Embedded
    private ProjectDescription desc;

    @Column(name = "min_invest")
    private int minInvestment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
    /*
     * Does not exist in DB table
     * private List<User> investors;
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<Investment> investments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
