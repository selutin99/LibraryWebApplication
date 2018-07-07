package com.example.demo.Entities.Book;

public class Book {
    private Long id;

    private String title;
    private String bookAuthor, bookGenre;
    private String publisher;
    private String year;
    private String city;

    public Book(Long id, String title, String bookAuthor, String bookGenre, String publisher, String year, String city) {
        this.id = id;
        this.title = title;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.publisher = publisher;
        this.year = year;
        this.city = city;
    }

    public Book(String title, String bookAuthor, String bookGenre, String publisher, String year, String city) {
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

    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }
    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
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
