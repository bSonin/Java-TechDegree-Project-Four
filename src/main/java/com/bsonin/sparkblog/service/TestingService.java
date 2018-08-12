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
        String body1 = getShortDummyText();
        String summary1 = "A summary of my first post!";
        BlogEntry entry1 = new BlogEntry(title1, body1, summary1);
        entries.add(entry1);

        String title2 = "My Second Post";
        String body2 = getShortDummyText();
        String summary2 = "A summary of my second post!";
        BlogEntry entry2 = new BlogEntry(title2, body2, summary2);
        Comment comment2 = new Comment(entry2, "Ben", getShortDummyText());
        entry2.getComments().add(comment2);
        entries.add(entry2);

        String title3 = "My Third Post";
        String body3 = getShortDummyText();
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
}
