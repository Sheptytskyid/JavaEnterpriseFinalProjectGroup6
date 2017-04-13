package net.greatstart.model.cards;

import net.greatstart.model.User;

import java.util.Date;
import java.util.List;

public class Project {

    private boolean isVerifed;
    private String name;
    private ProjectDescription desc;
    private int cost;
    private int minInvestment;
    private Date addDate;
    private User owner;
    private List<User> investors;
//    later: int rating (view counter)
//    URl logo
}
