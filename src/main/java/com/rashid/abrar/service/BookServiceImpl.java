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


    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null);

    }

    @Override
    public void addBook(int id,BookDTO bookDto,String type) {
        Author author =  authorService.getAuthorById(id);

        if(type.equals(STORY)){

            StoryBook sb = modelMapper.map(bookDto,StoryBook.class);
            sb.setAuthor(author);
            bookRepository.save(sb);
        }
        else if(type.equals(THESIS)){

            ThesisBook tb = modelMapper.map(bookDto, ThesisBook.class);
            tb.setAuthor(author);
            bookRepository.save(tb);
        }
        else if(type.equals(JOURNAL)){

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
    public void updateBook(int id, BookUpdateDTO book) {

        String type = bookDaoImpl.getTypeOfBook(id);

        if(type.equals(STORY)){
            StoryBook sb = (StoryBook) getBook(id);

            if(book.getTitle()!=null)
                sb.setTitle(book.getTitle());

            if(book.getGenre()!=null)
                sb.setGenre(book.getGenre());

            bookRepository.save(sb);

        }

        else if(type.equals(JOURNAL)){

            JournalBook jb = (JournalBook) getBook(id);

            if(book.getTitle()!=null)
                jb.setTitle(book.getTitle());

            if(book.getPublisher()!=null)
                jb.setPublisher(book.getPublisher());


            bookRepository.save(jb);

        }

        else if(type.equals(THESIS) ){

            ThesisBook tb = (ThesisBook) getBook(id);
            if(book.getTitle()!=null)
                tb.setTitle(book.getTitle());

            if(book.getTopic()!=null)
                tb.setTopic(book.getTopic());

            bookRepository.save(tb);


        }




    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }


}
