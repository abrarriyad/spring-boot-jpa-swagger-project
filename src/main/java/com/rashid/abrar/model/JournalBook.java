package com.rashid.abrar.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class JournalBook extends Book {

    private String publisher;

}
