package com.aokeeff.cassini.model;

/**
 * Created by aokeeff on 04/12/2016.
 */
public class FootballTeam {

    private final String teamName;
    private int totalGoals;
    private int totalPoints;

    public FootballTeam(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addGoals(int goals) {
        totalGoals = totalGoals + goals;
    }

    public int getTotalGoals() {
        return this.totalGoals;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void addPoints(int points) {
        this.totalPoints = totalPoints + points;
    }
}
