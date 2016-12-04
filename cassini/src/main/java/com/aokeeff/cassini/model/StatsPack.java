package com.aokeeff.cassini.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

/**
 * Created by aokeeff on 23/11/2016.
 *
 * Model to hold all of the necessary statistics to calculate results
 */
public class StatsPack {

    List<FootballMatch> footballMatches = new ArrayList<>();
    Map<String, FootballTeam> footballTeams = new HashMap<>();

    private int totalPoints;
    private String teamName;

    public void addFootballMatch(FootballMatch footballMatch) {
        footballMatches.add(footballMatch);
    }

    public List<FootballMatch> getFootballMatches() {
        return footballMatches;
    }

    public void addFootballTeam(FootballTeam footballTeam) {
        footballTeams.put(footballTeam.getTeamName(), footballTeam);
    }

    public FootballTeam getFootballTeam(String teamName) {
        return footballTeams.get(teamName);
    }

    public Map<String, FootballTeam> getFootballTeams() {
        return footballTeams;
    }

    public void getLeagueTable() {
        Map<String, FootballTeam> footballTeams = getFootballTeams();
        List<FootballTeam> footballTeamsCopy = new ArrayList<>();

        footballTeams.values().forEach(footballTeam -> footballTeamsCopy.add(footballTeam));

        while (footballTeamsCopy.size() > 0) {
            this.totalPoints = 0;

            footballTeamsCopy.forEach(footballTeam -> {

                int totalPoints = footballTeam.getTotalPoints();

                if (totalPoints > this.totalPoints) {
                    setTotalPointsCurrent(totalPoints, footballTeam.getTeamName());
                }

            });

            System.out.println(format("%30s\t%d", this.teamName, this.totalPoints));
            footballTeamsCopy.removeIf(footballTeam -> footballTeam.getTeamName().equals(this.teamName));
        }
    }

    private void setTotalPointsCurrent(int totalPoints, String teamName) {
        this.totalPoints = totalPoints;
        this.teamName = teamName;
    }
//    private ScoreStatistics scoreStatistics;
//    private ShotStatistics shotStatistics;
//    private CornerStatistics cornerStatistics;
//
//    public void setScoreStatistics(final ScoreStatistics scoreStatistics) {
//        this.scoreStatistics = scoreStatistics;
//    }
//
//    public ScoreStatistics getScoreStatistics() {
//        return this.scoreStatistics;
//    }
//
//    public void setShotStatistics(final ShotStatistics shotStatistics) {
//        this.shotStatistics = shotStatistics;
//    }
//
//    public ShotStatistics getShotStatistics() {
//        return this.shotStatistics;
//    }
//
//    public void setCornerStatistics(final CornerStatistics cornerStatistics) {
//        this.cornerStatistics = cornerStatistics;
//    }
//
//    public CornerStatistics getCornerStatistics() {
//        return this.cornerStatistics;
//    }


}
