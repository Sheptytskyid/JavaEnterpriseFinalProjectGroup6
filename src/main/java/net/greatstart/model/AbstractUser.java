package net.greatstart.model;


public abstract class AbstractUser {

    private int id;
    private String name;
    private String password;
    private String email;
    private Role role;
    private Contact contact;

    public AbstractUser(String name, String password, String email, Role role, Contact contact) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.contact = contact;
        role = Role.USER;
    }
}
