package com.example.demo.Entities.Book;

public class Book {
    private int id;

    private String name;
    private String lastName, title;
    private String publisher;
    private String year;
    private String city;

    public Book(int id, String name, String lastName, String title, String publisher, String year, String city) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.city = city;
    }

    public Book(String name, String lastName, String title, String publisher, String year, String city) {
        this.name = name;
        this.lastName = lastName;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.city = city;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) { this.city = city; }
}
