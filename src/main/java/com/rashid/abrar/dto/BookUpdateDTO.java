package com.rashid.abrar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rashid.abrar.model.Author;

import javax.validation.constraints.NotNull;

public class BookUpdateDTO {

    private String title;
    private String publisher;
    private String topic;
    private String genre;

    @JsonIgnore
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
