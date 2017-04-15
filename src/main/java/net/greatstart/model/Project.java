package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name = "owner_id")
    private User owner;

    /** Does not exist in DB table
     private List<User> investors;
     */
    @OneToMany
    @JoinColumn(name = "project_id")
    private List<Investment> investments;

}
