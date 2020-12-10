package com.rashid.abrar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public  class Book {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "book_id")
//    private int id;


    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(unique = true)
    private int id;
    private String title;


    @OneToOne(targetEntity = Author.class)
    private Author author;


    public Author getAuthor() {
        return author;
    }


    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
