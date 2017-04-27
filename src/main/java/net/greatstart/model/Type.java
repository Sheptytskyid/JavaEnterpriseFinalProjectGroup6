package net.greatstart.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "user_types")
public class Type extends AbstractModel {

    private String name;
    @OneToMany(fetch =  FetchType.LAZY, mappedBy = "type")
    private Set<User> users;
}
