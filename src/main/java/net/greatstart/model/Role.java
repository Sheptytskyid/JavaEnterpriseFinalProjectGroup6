package net.greatstart.model;

import org.springframework.stereotype.Component;


@Component
public enum Role {
    USER("user"), ADMIN("admin");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
