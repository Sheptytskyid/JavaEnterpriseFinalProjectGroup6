package net.greatstart.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Component
@Entity
@Table(name = "projects")
public class Project  extends AbstractModel{
    private boolean isVerifed;
    private String name;
    private ProjectDescription desc;
    private int cost;
    private int minInvestment;
    private LocalDate addDate;
    private User owner;
    private List<User> investors;
}
