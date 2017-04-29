package net.greatstart.model;

public enum InvestmentGoal {
    STARTUP("Start-ups and investment projects"),
    VENTURE("Venture loans to business"),
    BUSINESS_ACQUISITION("Business acquisition");

    private String goal;

    InvestmentGoal(String goal) {
        this.goal = goal;
    }

    public String getName() {
        return goal;
    }
}
