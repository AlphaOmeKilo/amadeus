package com.aokeeff.cassini.web;


import com.aokeeff.cassini.exception.InvalidMatchElementException;
import com.aokeeff.cassini.model.StatsPack;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {

    private static final String RESULTS_PAGE = "http://www.bbc.co.uk/sport/football/premier-league/results";
    private static final String MATCH_DETAILS = "td.match-details";

    public static WebScraper getInstance() {
        return new WebScraper();
    }

    public void scrape(StatsPack statsPack) throws IOException{
        Document doc = Jsoup.connect(RESULTS_PAGE).get();
        Elements newsHeadlines = doc.select(MATCH_DETAILS);


        newsHeadlines.forEach(headline -> {
            try {
                ResultExtractor.extractResult(statsPack, headline);
            } catch (InvalidMatchElementException imee) {
                System.out.println("There was an error parsing the match details");
            }

        });

    }

}
