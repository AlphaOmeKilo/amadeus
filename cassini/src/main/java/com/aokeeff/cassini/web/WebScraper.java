package com.aokeeff.cassini.web;


import com.aokeeff.cassini.exception.InvalidMatchElementException;
import com.aokeeff.cassini.model.StatsPack;
import com.sun.org.glassfish.external.statistics.Stats;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {

    private static final String FOOTBALL_HOME = "http://www.bbc.co.uk/sport/football/";
    private static final String RESULTS_PAGE = FOOTBALL_HOME + "premier-league/results";
    private static final String FIXTURES_PAGE = FOOTBALL_HOME + "premier-league/fixtures";
    private static final String MATCH_DETAILS = "td.match-details";

    public static WebScraper getInstance() {
        return new WebScraper();
    }

    public void scrapeResults(StatsPack statsPack) throws IOException{
        Document doc = Jsoup.connect(RESULTS_PAGE).get();
        Elements matchDetails = doc.select(MATCH_DETAILS);


        matchDetails.forEach(matchDetail -> {
            try {
                ResultExtractor.extractResult(statsPack, matchDetail);
            } catch (InvalidMatchElementException imee) {
                System.out.println("There was an error parsing the match details");
            }

        });

    }

    public void scrapeFixtures(StatsPack statsPack) throws IOException {
        Document doc = Jsoup.connect(FIXTURES_PAGE).get();
        Elements matchDetails = doc.select(MATCH_DETAILS);

        matchDetails.forEach(matchDetail -> {
            try {
                FixtureExtractor.extractFixture(statsPack, matchDetail);
            } catch (InvalidMatchElementException imee) {
                System.out.println("There was an error parsing the match details");
            }

        });
    }

}
