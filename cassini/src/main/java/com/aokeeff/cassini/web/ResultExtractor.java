package com.aokeeff.cassini.web;

import com.aokeeff.cassini.exception.InvalidMatchElementException;
import com.aokeeff.cassini.model.FootballMatch;
import com.aokeeff.cassini.model.StatsPack;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static java.lang.String.format;

/**
 * Created by aokeeff on 04/12/2016.
 */
public class ResultExtractor {

    public static void extractResult(StatsPack statsPack, Element matchElement) throws InvalidMatchElementException {
        String homeTeam = extractTeamName(matchElement, "team-home");
        String awayTeam = extractTeamName(matchElement, "team-away");

        FootballMatch footballMatch = new FootballMatch(statsPack, homeTeam, awayTeam);

        int scoreExtractionResult = footballMatch.setScore(extractScore(matchElement));
        if (scoreExtractionResult == 0) {
            statsPack.addFootballMatch(footballMatch);
        }



//        System.out.println(format("%s %d - %d %s",
//                footballMatch.getHomeTeam().getTeamName(),
//                footballMatch.getScore().getHomeGoals(),
//                footballMatch.getScore().getAwayGoals(),
//                footballMatch.getAwayTeam().getTeamName()));

    }

    private static String extractScore(Element matchElement) {
        return matchElement.getElementsByTag("abbr").get(0).text();
    }

    private static String extractTeamName(Element matchElement, String className) throws InvalidMatchElementException {
        Elements teamElements = matchElement.getElementsByClass(className);
        if (teamElements.size() > 1) {
            throw new InvalidMatchElementException();
        }

        return teamElements.get(0).getElementsByTag("a").get(0).text();
    }

}
