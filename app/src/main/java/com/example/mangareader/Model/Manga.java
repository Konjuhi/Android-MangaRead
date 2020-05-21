package com.example.mangareader.Model;

import java.util.List;

public class Manga {
    private String Name;
    private String Image;
    private String Category;
    private List<Chapters> Chapters;

    public Manga() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public List<com.example.mangareader.Model.Chapters> getChapters() {
        return Chapters;
    }

    public void setChapters(List<com.example.mangareader.Model.Chapters> chapters) {
        Chapters = chapters;
    }
}