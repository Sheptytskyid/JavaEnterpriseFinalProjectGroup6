package net.greatstart.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @Column(name = "last_name")
    private String lastName;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "investor", fetch = FetchType.LAZY)

    private List<Investment> investments;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Project> ownedProjects;

    @OneToMany(mappedBy = "investor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<InvestmentInterest> investmentInterests;

}
