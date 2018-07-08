package com.example.demo.Entities.Statistic;

public class Statistic {

    private String title, quantity;

    public Statistic(String title, String quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

