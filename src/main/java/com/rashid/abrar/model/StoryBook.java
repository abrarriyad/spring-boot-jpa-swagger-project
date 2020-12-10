package com.rashid.abrar.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class StoryBook extends Book {

    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
