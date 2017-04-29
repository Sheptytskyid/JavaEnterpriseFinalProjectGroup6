package net.greatstart.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User extends AbstractModel {
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 5, max = 35, message = "{password.size}")
    @Column(name = "password")
    private String password;

    @NotNull
    @Pattern(regexp = "|^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
            + "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "email.valid")
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany(fetch = FetchType.LAZY)
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
