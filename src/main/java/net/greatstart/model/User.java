package net.greatstart.model;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private Role role;
    private Contact contact;
    private List<Investment> investments;
    private List<Project> ownedProjects;

    public User(String name, String password, String email, Role role, Contact contact) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.contact = contact;
    }
}
