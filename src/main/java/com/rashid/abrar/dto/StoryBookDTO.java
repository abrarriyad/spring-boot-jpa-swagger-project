package com.rashid.abrar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import lombok.Data;


public class StoryBookDTO {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;
    private String genre;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
