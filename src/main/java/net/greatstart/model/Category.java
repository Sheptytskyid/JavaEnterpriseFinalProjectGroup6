/**
 * An entity class for categories.
 */
package net.greatstart.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "categories")
public class Category extends AbstractModel {
    @Column(name = "name")
    private String name;
}
