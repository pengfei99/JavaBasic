package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation.source.bookstore;

import java.io.Serializable;

public class MyBook implements Serializable {
    private static final long serialVersionUID = 1190476516911661470L;

    private String title;
    private String isbn;
    private double cost;

    public MyBook(String isbn) {
        this.isbn = isbn;
    }

    public MyBook(String title, String isbn, double cost) {
        this.title = title;
        this.isbn = isbn;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "MyBook{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", cost=" + cost +
                '}';
    }
}
