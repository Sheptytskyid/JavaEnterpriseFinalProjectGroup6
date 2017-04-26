package net.greatstart.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;

    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "inv")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Investment> investments;

    @OneToMany(mappedBy = "owner")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Project> ownedProjects;
}
