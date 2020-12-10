package com.rashid.abrar.service;

import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;

import java.util.List;

public interface AuthorService {

    void addAuthor(Author author);
    void removeAuthor(int id);
    List<Author> getAllAuthors();
    List<Book> getAllBooksByAuthorId(int id);
    Author getAuthorById(int id);
    Author updateAuthor(Author author);


}
