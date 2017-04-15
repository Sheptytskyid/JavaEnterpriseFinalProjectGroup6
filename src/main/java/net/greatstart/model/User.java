package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Embedded;
import javax.persistence.Enumerated;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.List;

@Component
@Entity
@Table(name = "users")
public class User extends AbstractModel {
    //    private int id;
    private String name;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    @Embedded
    private Contact contact;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Investment> investments;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private List<Project> ownedProjects;

    public User(String name, String password, String email, Role role, Contact contact) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.contact = contact;
    }

    public User() {
    }
}
