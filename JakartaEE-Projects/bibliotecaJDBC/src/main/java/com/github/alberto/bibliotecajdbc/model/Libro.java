package com.github.alberto.bibliotecajdbc.model;

import java.io.Serializable;
import java.util.Date;

public class Libro implements Serializable {
    private Long id;
    private String title;
    private Long author_id;
    private Date publication_date;

    public Libro() {
    }

    public Libro(Long id, String title, Long author_id, Date publication_date) {
        this.id = id;
        this.title = title;
        this.author_id = author_id;
        this.publication_date = publication_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author_id=" + author_id +
                ", publication_date=" + publication_date +
                '}';
    }
}
