package ua.goit.group6.model;

import ua.goit.group6.model.Cards.Project;

import java.util.List;


public class Investor extends AbstractUser {

    private List<Project> investedProjects;

    public Investor(String name, String password, String email, Role role, Contact contact) {
        super(name, password, email, role, contact);
    }

}

