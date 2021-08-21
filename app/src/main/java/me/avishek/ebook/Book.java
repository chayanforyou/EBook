package me.avishek.ebook;

public class Book {
    private String name;
    private double rating;
    private int thumbnail;

    public Book(String name, double rating, int thumbnail) {
        this.name = name;
        this.rating = rating;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
