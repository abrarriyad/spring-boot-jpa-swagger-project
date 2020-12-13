package com.rashid.abrar.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import static com.rashid.abrar.util.Constants.*;

@Entity
@Data
@DiscriminatorValue(value = "story")
public class StoryBook extends Book {

    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getType() {
        return STORY;
    }
}
