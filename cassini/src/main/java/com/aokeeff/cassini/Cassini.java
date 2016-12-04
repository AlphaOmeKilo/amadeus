package com.aokeeff.cassini;

import com.aokeeff.cassini.model.StatsPack;
import com.aokeeff.cassini.web.WebScraper;

import java.io.IOException;

/**
 * Created by aokeeff on 23/11/2016.
 */
public class Cassini {

    private StatsPack statsPack;


    public static void main(String[] args) throws IOException {
        Cassini cassini = new Cassini();
        cassini.populateStatsPack();

    }

    public void populateStatsPack() throws IOException {
        this.statsPack = new StatsPack();
        WebScraper.getInstance().scrape(this.statsPack);

        statsPack.getLeagueTable();

    }
}
