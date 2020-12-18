package com.rashid.abrar.service;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.exception.AuthorNotFoundException;
import com.rashid.abrar.exception.IllegalAuthorException;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addAuthor(AuthorDTO authorDto) {
        if(authorDto.getName().equals(null)){
            throw new IllegalAuthorException();
        }
        Author author = modelMapper.map(authorDto, Author.class);
        authorRepository.save(author);

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
    @Transactional
    public int updateAuthor(int id, AuthorUpdateDTO authorDto){

        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        CriteriaUpdate<Author> update = cb.createCriteriaUpdate(Author.class);

        Root<Author> authorRoot = update.from(Author.class);

        if(authorDto.getEmail()!=null) {
            update.set(authorRoot.get("email"), authorDto.getEmail());
        }
        if(authorDto.getName()!=null) {
            update.set(authorRoot.get("name"), authorDto.getName());
        }
        Query query = entityManager.createQuery(update);

       return query.executeUpdate();


    }

    @Override
    public void removeAuthor(int id) {

        if(authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        }
        else {
            throw new AuthorNotFoundException();
        }

    }


}
