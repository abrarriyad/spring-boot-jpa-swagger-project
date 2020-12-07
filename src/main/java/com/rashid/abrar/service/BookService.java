package com.rashid.abrar.service;

import com.rashid.abrar.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getBook(int id);
    void addBook(Book book);
    List<Book> getAllBooks();
    void deleteBook(int id);
    void updateBook (int id, Book updatedBook);



}
