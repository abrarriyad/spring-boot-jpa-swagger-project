package com.rashid.abrar.service;

import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.repository.AuthorRepository;
import com.rashid.abrar.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Autowired
    private CommonRepository commonRepository;

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void removeAuthor(int id) {
        authorRepository.deleteById(id);
    }


    @Override
    public List<Author> getAllAuthors() {
        List <Author> authors = new ArrayList<>();

        authorRepository.findAll()
                .forEach(authors::add);

        return authors;
    }

    @Override
    public List<Book> getAllBooksByAuthorId(int id) {
        return commonRepository.getAllBooksbyAuthorId(id);
    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }



    @Override
    public Author updateAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }


}
