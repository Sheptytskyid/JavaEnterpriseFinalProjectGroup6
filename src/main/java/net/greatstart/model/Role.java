package net.greatstart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * An entity class to handle user roles (like Admin, User etc.).
 */

@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AbstractModel {

    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }

}
