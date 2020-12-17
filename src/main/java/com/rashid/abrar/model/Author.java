package com.rashid.abrar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class Author {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(unique = true)
    private int id;
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @Override
    public String toString() {
        return "Author{" +
                "pk=" + pk +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", books=" + books +
                '}';
    }

    @JsonIgnore
    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
    private List books;


    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List getBooks() {
        return books;
    }

    public void setBooks(List books) {
        this.books = books;
    }
}
