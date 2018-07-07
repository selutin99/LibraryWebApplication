package com.example.demo.Entities.Book;

public class Book {
    private Long id;

    private String title;
    private int bookAuthor, bookGenre;
    private String publisher;
    private int year;
    private String city;

    public Book(Long id, String title, int bookAuthor, int bookGenre, String publisher, int year, String city) {
        this.id = id;
        this.title = title;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.publisher = publisher;
        this.year = year;
        this.city = city;
    }

    public Book(String title, int bookAuthor, int bookGenre, String publisher, int year, String city) {
        this.title = title;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.publisher = publisher;
        this.year = year;
        this.city = city;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }

    public int getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(int bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookGenre() {
        return bookGenre;
    }
    public void setBookGenre(int bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) { this.city = city; }
}
