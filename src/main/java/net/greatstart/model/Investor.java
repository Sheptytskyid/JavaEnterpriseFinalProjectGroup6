package net.greatstart.model;

import net.greatstart.model.Cards.Project;

import java.util.List;


public class Investor extends AbstractUser {

    private List<Project> investedProjects;

    public Investor(String name, String password, String email, Role role, Contact contact) {
        super(name, password, email, role, contact);
    }

}

