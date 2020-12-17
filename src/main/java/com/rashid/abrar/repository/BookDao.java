package com.rashid.abrar.repository;

import com.rashid.abrar.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAllBooksbyAuthorId(int id);
    List<Book> getAllBooksbyType(String type);


}
