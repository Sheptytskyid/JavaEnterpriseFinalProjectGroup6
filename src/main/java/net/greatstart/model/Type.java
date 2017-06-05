package net.greatstart.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * An entity class to manage {@link net.greatstart.model.User} types.
 */

@Data
@Entity
@Table(name = "user_types")
public class Type extends AbstractModel {

    private String name;
}
