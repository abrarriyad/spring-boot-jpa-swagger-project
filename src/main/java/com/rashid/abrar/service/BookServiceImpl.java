package com.rashid.abrar.service;

import com.rashid.abrar.dto.BookDTO;
import com.rashid.abrar.dto.BookUpdateDTO;
import com.rashid.abrar.model.*;
import com.rashid.abrar.repository.BookDaoImpl;
import com.rashid.abrar.repository.BookRepository;
import org.modelmapper.ModelMapper;
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
import java.util.ArrayList;
import java.util.List;

import static com.rashid.abrar.util.Constants.JOURNAL;
import static com.rashid.abrar.util.Constants.STORY;
import static com.rashid.abrar.util.Constants.THESIS;

@Service
public class BookServiceImpl  implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  AuthorService authorService;
    @Autowired
    private BookDaoImpl bookDaoImpl;


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null);

    }

    @Override
    public void addBook(int id,BookDTO bookDto,String type) {
        Author author =  authorService.getAuthorById(id);

        if(type.toLowerCase().trim().equals(STORY)){

            StoryBook sb = modelMapper.map(bookDto,StoryBook.class);
            sb.setAuthor(author);
            bookRepository.save(sb);
        }
        else if(type.toLowerCase().trim().equals(THESIS)){

            ThesisBook tb = modelMapper.map(bookDto, ThesisBook.class);
            tb.setAuthor(author);
            bookRepository.save(tb);
        }
        else if(type.toLowerCase().trim().equals(JOURNAL)){

            JournalBook jb = modelMapper.map(bookDto, JournalBook.class);
            jb.setAuthor(author);
            bookRepository.save(jb);
        }



    }

    @Override
    public List<Book> getAllBooks(int pageNo,int pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));

        List<Book> books = new ArrayList<>();

        bookRepository.findAll(paging)
                .forEach(books::add);
        return books;
    }

    @Override
    public void updateBook(int id, BookUpdateDTO bookDto) {

//
//        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//
//
//        CriteriaUpdate<StoryBook> update = cb.createCriteriaUpdate(StoryBook.class);
//
//        Root<StoryBook> bookRoot = update.from(StoryBook.class);
//
//        if(book.getTitle()!=null) {
//            update.set(bookRoot.get("title"), book.getTitle());
//        }
//        if (book.getTopic()!=null){
//            update.set(bookRoot.get("topic"), book.getTopic());
//
//        }
//        if(book.getGenre()!=null){
//            update.set(bookRoot.get("genre"), book.getGenre());
//        }
//        if(book.getPublisher()!=null){
//            update.set(bookRoot.get("publisher"), book.getPublisher());
//        }
//
//        Query query = entityManager.createQuery(update);
//
//        return query.executeUpdate();


        Book book = getBook(id);

        if(book.getType().equals(STORY)){
            StoryBook sb = (StoryBook) book;

            if(bookDto.getTitle()!=null)
                sb.setTitle(bookDto.getTitle());

            if(bookDto.getGenre()!=null)
                sb.setGenre(bookDto.getGenre());

            bookRepository.save(sb);

        }

        else if(book.getType().equals(JOURNAL)){

            JournalBook jb = (JournalBook) book;

            if(bookDto.getTitle()!=null)
                jb.setTitle(bookDto.getTitle());

            if(bookDto.getPublisher()!=null)
                jb.setPublisher(bookDto.getPublisher());


            bookRepository.save(jb);

        }

        else if(book.getType().equals(THESIS) ){

            ThesisBook tb = (ThesisBook) book;
            if(bookDto.getTitle()!=null)
                tb.setTitle(bookDto.getTitle());

            if(bookDto.getTopic()!=null)
                tb.setTopic(bookDto.getTopic());

            bookRepository.save(tb);

        }


    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }


}
