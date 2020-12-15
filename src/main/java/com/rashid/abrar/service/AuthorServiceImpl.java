package com.rashid.abrar.service;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.repository.AuthorRepository;
import com.rashid.abrar.repository.BookDaoImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.CharMatcher.any;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookDaoImpl bookDaoImpl;



    @Override
    public void addAuthor(AuthorDTO authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
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
        return bookDaoImpl.getAllBooksbyAuthorId(id);

    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }



    @Override
    public Author updateAuthor(int id, AuthorUpdateDTO authorDto) {

        Author author = getAuthorById(id);

        if(authorDto.getEmail() !=null){
            author.setEmail(authorDto.getEmail());
        }

        if(authorDto.getEmail() !=null) {
            author.setName(authorDto.getName());
        }
        author.setBooks(getAllBooksByAuthorId(id));

        return authorRepository.save(author);
    }


}
