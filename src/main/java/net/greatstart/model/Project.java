package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.List;

@Component
@Entity
@Table(name = "projects")
public class Project extends AbstractModel {
    @Embedded
    private ProjectDescription desc;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    /*
     * Does not exist in DB table
     * private List<User> investors;
     */
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "project")
    private List<Investment> investments;

}
