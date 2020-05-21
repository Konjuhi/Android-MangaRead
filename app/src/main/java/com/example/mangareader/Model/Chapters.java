package com.example.mangareader.Model;

import java.util.List;

public class Chapters {
    private String Name;
    private List<String> Links;

    public Chapters(){

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getLinks() {
        return Links;
    }

    public void setLinks(List<String> links) {
        Links = links;
    }
}

