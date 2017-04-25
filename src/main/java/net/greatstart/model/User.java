package net.greatstart.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "users")
public class User {
	
    private Long id;
    private String name;
    private String password;
    private String confirmPassword;
//    private String email;
//    private Role role;
//    private Contact contact;
//    private List<Investment> investments;
//    private List<Project> ownedProjects;

    public User(){
    	
    }
    
    public User(String name, String password, String email, Role role, Contact contact) {
        this.setName(name);
        this.setPassword(password);
        //this.setEmail(email);
//        this.setRole(role);
//        this.setContact(contact);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	@Column(name = "email")
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

//	@Column(name = "role")
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}
//
//	@Column(name = "contact")
//	public Contact getContact() {
//		return contact;
//	}
//
//	public void setContact(Contact contact) {
//		this.contact = contact;
//	}
//
//	public List<Investment> getInvestments() {
//		return investments;
//	}
//
//	public void setInvestments(List<Investment> investments) {
//		this.investments = investments;
//	}

	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
