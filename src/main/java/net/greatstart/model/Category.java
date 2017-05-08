package net.greatstart.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "categories")
@Component
public class Category extends AbstractModel {
    @Column(name = "name")
    private String name;
}
