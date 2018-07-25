package com.bsonin.sparkblog.model;

import java.util.ArrayList;
import java.util.List;

public class Blog {
    public static final String BLOG_NAME = "Ben's Spark Blog";
    List<BlogEntry> entries;

    public Blog() {
        entries = new ArrayList<>();
    }

    public List<BlogEntry> getEntries() { return entries; }
}
