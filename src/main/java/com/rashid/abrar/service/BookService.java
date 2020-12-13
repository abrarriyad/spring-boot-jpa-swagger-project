package com.rashid.abrar.service;

import com.rashid.abrar.model.Book;

import java.util.List;

public interface BookService {

    Book getBook(int id);
    void addBook(Book book);
    List<Book> getAllBooks(int pageNo, int pageSize,String sortBy);
    void deleteBook(int id);
    void updateBook (int id, Book updatedBook);



}
