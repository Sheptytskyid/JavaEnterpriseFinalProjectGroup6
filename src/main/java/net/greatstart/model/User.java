package net.greatstart.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User extends AbstractModel {
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "inv", fetch = FetchType.EAGER)
    private List<Investment> investments;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Project> ownedProjects;
}
