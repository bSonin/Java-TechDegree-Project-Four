package com.bsonin.sparkblog.service;

import com.bsonin.sparkblog.model.BlogEntry;
import com.bsonin.sparkblog.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class TestingService {

    public TestingService()
    {
    }

    public static List<BlogEntry> getTestBlogEntries()
    {
        List<BlogEntry> entries = new ArrayList<>();

        String title1 = "My First Post";
        String body1 = getLongDummyText();
        String summary1 = "A summary of my first post!";
        BlogEntry entry1 = new BlogEntry(title1, body1, summary1);
        entries.add(entry1);

        String title2 = "My Second Post";
        String body2 = getLongDummyText();
        String summary2 = "A summary of my second post!";
        BlogEntry entry2 = new BlogEntry(title2, body2, summary2);
        Comment comment2 = new Comment(entry2, "Ben", getShortDummyText());
        entry2.getComments().add(comment2);
        entries.add(entry2);

        String title3 = "My Third Post";
        String body3 = getLongDummyText();
        String summary3 = "A summary of my third post!";
        BlogEntry entry3 = new BlogEntry(title3, body3, summary3);
        Comment comment3_1 = new Comment(entry2, "Ben", getShortDummyText());
        Comment comment3_2 = new Comment(entry2, "Alex", getShortDummyText());
        entry3.getComments().add(comment3_1);
        entry3.getComments().add(comment3_2);
        entries.add(entry3);

        return entries;
    }

    private static String getShortDummyText()
    {
        return "A single line of text to fill the body of a test entry.";
    }
    private static String getLongDummyText() {
        return "Economics focuses on the behaviour and interactions of economic agents and how " +
                "economies work. Microeconomics analyzes basic elements in the economy, " +
                "including individual agents and markets, their interactions, and the " +
                "outcomes of interactions. Individual agents may include, for example, " +
                "households, firms, buyers, and sellers. \n\n Macroeconomics analyzes the entire " +
                "economy (meaning aggregated production, consumption, savings, and investment) " +
                "and issues affecting it, including unemployment of resources (labour, capital," +
                " and land), inflation, economic growth, and the public policies that address " +
                "these issues (monetary, fiscal, and other policies). See glossary of economics." +
                " - https://en.wikipedia.org/wiki/Economics";
    }
}
