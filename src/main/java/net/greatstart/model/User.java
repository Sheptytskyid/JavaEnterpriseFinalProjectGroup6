package net.greatstart.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private Role role;
    private Contact contact;
    private List<Investment> investments;
    private List<Project> ownedProjects;
}
