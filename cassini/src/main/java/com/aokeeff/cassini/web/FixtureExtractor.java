package com.aokeeff.cassini.web;

import com.aokeeff.cassini.exception.InvalidMatchElementException;
import com.aokeeff.cassini.model.FootballMatch;
import com.aokeeff.cassini.model.StatsPack;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by aokeeff on 04/12/2016.
 */
public class FixtureExtractor {
    public static void extractFixture(StatsPack statsPack, Element matchDetail) throws  InvalidMatchElementException {

        String homeTeam = extractTeamName(matchDetail, "team-home");
        String awayTeam = extractTeamName(matchDetail, "team-away");

        FootballMatch footballMatch = new FootballMatch(statsPack, homeTeam, awayTeam);

        statsPack.addFootballMatch(footballMatch);

    }

    private static String extractTeamName(Element matchElement, String className) throws InvalidMatchElementException {
        Elements teamElements = matchElement.getElementsByClass(className);
        if (teamElements.size() > 1) {
            throw new InvalidMatchElementException();
        }

        return teamElements.get(0).getElementsByTag("a").get(0).text();
    }

}
