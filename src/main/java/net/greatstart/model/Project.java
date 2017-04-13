package net.greatstart.model;

import java.time.LocalDate;
import java.util.List;

public class Project {

    private boolean isVerifed;
    private String name;
    private ProjectDescription desc;
    private int cost;
    private int minInvestment;
    private LocalDate addDate;
    private User owner;
    private List<User> investors;
//    later: int rating (view counter)
//    URl logo
}
