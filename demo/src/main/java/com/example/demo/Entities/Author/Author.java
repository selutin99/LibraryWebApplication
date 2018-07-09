package com.example.demo.Entities.Author;

public class Author {

    private int id;

    private String firstName, lastName, patronymic;

    public Author(int id, String firstName, String lastName, String patronymic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public Author(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPatronymic() {return patronymic; }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

}