package net.greatstart.model;


import net.greatstart.model.Cards.Project;

import java.util.List;

public class ProjectOwner extends AbstractUser {

    private List<Project> ownedProjects;

    public ProjectOwner(String name, String password, String email, Role role, Contact contact) {
        super(name, password, email, role, contact);
    }
}
