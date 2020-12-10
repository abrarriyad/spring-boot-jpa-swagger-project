package com.rashid.abrar.service;

import com.rashid.abrar.model.Book;
import com.rashid.abrar.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl  implements BookService{

    @Autowired
    private BookRepository bookRepository;



    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null);

    }

    @Override
    public void addBook(Book book) {

        bookRepository.save(book);

    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        bookRepository.findAll()
                .forEach(books::add);
        return books;
    }

    @Override
    public void updateBook(int id, Book updatedBook) {
        bookRepository.save(updatedBook);

    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }


}
