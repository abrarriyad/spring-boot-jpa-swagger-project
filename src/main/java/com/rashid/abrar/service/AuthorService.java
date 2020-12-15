package com.rashid.abrar.service;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;

import java.util.List;

public interface AuthorService {

    void addAuthor(AuthorDTO author);
    void removeAuthor(int id);
    List<Author> getAllAuthors(int pageNo, int pageSize,String sortBy);
    List<Book> getAllBooksByAuthorId(int id);
    Author getAuthorById(int id);
    Author updateAuthor(int id, AuthorUpdateDTO author);


}
