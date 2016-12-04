package com.aokeeff.cassini.model;

/**
 * Created by aokeeff on 04/12/2016.
 */
public class FootballMatch {

    private FootballTeam homeTeam;
    private FootballTeam awayTeam;
    private Score score;

    private StatsPack statsPack;

    public FootballMatch(StatsPack statsPack, String homeTeam, String awayTeam) {
        this.statsPack = statsPack;
        setHomeTeam(homeTeam);
        setAwayTeam(awayTeam);
    }

    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    private void setHomeTeam(String teamName) {
        if ((statsPack.getFootballTeam(teamName) == null)) {
            FootballTeam footballTeam = new FootballTeam(teamName);
            statsPack.addFootballTeam(footballTeam);
        }
        this.homeTeam = statsPack.getFootballTeam(teamName);
    }

    public FootballTeam getAwayTeam() {
        return awayTeam;
    }

    private void setAwayTeam(String teamName) {
        if ((statsPack.getFootballTeam(teamName) == null)) {
            FootballTeam footballTeam = new FootballTeam(teamName);
            statsPack.addFootballTeam(footballTeam);
        }
        this.awayTeam = statsPack.getFootballTeam(teamName);
    }

    public int setScore(String score) {
        this.score = new Score(score);
        if (this.score.homeGoals < 0) {
            return -1;
        }
        homeTeam.addGoals(this.score.homeGoals);
        awayTeam.addGoals(this.score.awayGoals);

        addPoints();

        return 0;
    }

    public Score getScore() {
        return this.score;
    }

    private void addPoints() {
        if (score.homeGoals > score.awayGoals) {
            homeTeam.addPoints(3);
        } else if (score.homeGoals < score.awayGoals) {
            awayTeam.addPoints(3);
        } else {
            homeTeam.addPoints(1);
            awayTeam.addPoints(1);
        }

    }

    public class Score {

        private final int homeGoals;
        private final int awayGoals;

        private Score(String score) {
            String[] parts = score.split("-");
            //Guard against Postponed games
            if (!parts[0].equals("P")) {
                homeGoals = Integer.parseInt(parts[0]);
                awayGoals = Integer.parseInt(parts[1]);
            } else {
                homeGoals = -1;
                awayGoals = -1;
            }


        }

        public int getHomeGoals() {
            return homeGoals;
        }

        public int getAwayGoals() {
            return awayGoals;
        }
    }
}
