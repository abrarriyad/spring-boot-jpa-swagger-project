package com.rashid.abrar.service;

import com.rashid.abrar.dto.BookDTO;
import com.rashid.abrar.dto.BookUpdateDTO;
import com.rashid.abrar.model.Book;

import java.util.List;

public interface BookService {

    Book getBook(int id);
    void addBook(int id, BookDTO book, String type);
    List<Book> getAllBooks(int pageNo, int pageSize,String sortBy);
    void deleteBook(int id);
    void updateBook (int id, BookUpdateDTO updatedBook);



}
