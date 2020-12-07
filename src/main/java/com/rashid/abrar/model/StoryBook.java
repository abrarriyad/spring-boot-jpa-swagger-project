package com.rashid.abrar.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class StoryBook extends Book {

    private String genre;

}
