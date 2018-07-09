package com.example.demo.Entities.Genre;

public class Genre {
    private int id;

    private String title;

    public Genre(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Genre(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
