package com.rashid.abrar.service;

import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.repository.AuthorRepository;
import com.rashid.abrar.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Autowired
    private BookDao bookDao;

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void removeAuthor(int id) {
        authorRepository.deleteById(id);

    }


    @Override
    public List<Author> getAllAuthors(int pageNo, int pageSize, String sortBy) {

        List <Author> authors = new ArrayList<>();
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));

        authorRepository.findAll(paging)
                .forEach(authors::add);

        return authors;
    }

    @Override
    public List<Book> getAllBooksByAuthorId(int id) {
        return bookDao.getAllBooksbyAuthorId(id);

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
