package com.example.mangareader.Model;

public class FeedEntry {

    private String name;
    private String artist;
    private String releaseDate;
    private String summary;

    public FeedEntry(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

     /* @Override
    public String toString() {
       return "name=" + name + '\n' +
                ", artist=" + artist + '\n' +
               ", releaseDate=" + releaseDate + '\n' +
               ", imageURL=" + imageURL + '\n';
   }*/


}
