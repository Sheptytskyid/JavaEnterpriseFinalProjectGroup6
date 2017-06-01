package net.greatstart.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_types")
public class Type extends AbstractModel {

    private String name;
}
