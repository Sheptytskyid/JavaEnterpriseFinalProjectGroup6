package net.greatstart.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Project {
    private boolean isVerifed;
    private String name;
    private ProjectDescription desc;
    private int cost;
    private int minInvestment;
    private LocalDate addDate;
    private User owner;
    private List<User> investors;
}
