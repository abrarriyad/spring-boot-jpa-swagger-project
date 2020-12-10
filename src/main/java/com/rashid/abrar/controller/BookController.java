package com.rashid.abrar.controller;

import com.rashid.abrar.dto.AddJournalBookDTO;
import com.rashid.abrar.dto.AddStoryBookDTO;
import com.rashid.abrar.dto.AddThesisBookDTO;
import com.rashid.abrar.model.*;
import com.rashid.abrar.service.AuthorService;
import com.rashid.abrar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/books")
    public List<Book>  getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id){
        return  bookService.getBook(id);
    }

    @PostMapping("/books/story/{author_id}")
    public void addStoryBook(@PathVariable int author_id, @RequestBody AddStoryBookDTO storyBook){
        Author author =  authorService.getAuthorById(author_id);
        StoryBook sb = new StoryBook();
        sb.setId(storyBook.getId());
        sb.setGenre(storyBook.getGenre());
        sb.setTitle(storyBook.getTitle());

        sb.setAuthor(author);

        bookService.addBook(sb);


    }

    @PostMapping("/books/thesis/{author_id}")
    public void addThesisBook(@PathVariable int author_id, @RequestBody AddThesisBookDTO thesisBook){
        Author author = authorService.getAuthorById(author_id);
        ThesisBook tb = new ThesisBook();

        tb.setId(thesisBook.getId());
        tb.setTopic(thesisBook.getTopic());
        tb.setTitle(thesisBook.getTitle());
        tb.setAuthor(author);

        bookService.addBook(tb);
    }

    @PostMapping("/books/journal/{author_id}")
    public void addJournalBook(@PathVariable int author_id, @RequestBody AddJournalBookDTO journalBook){

        Author author = authorService.getAuthorById(author_id);
        JournalBook jb = new JournalBook();
        jb.setId(journalBook.getId());
        jb.setPublisher(journalBook.getPublisher());
        jb.setTitle(journalBook.getTitle());
        jb.setAuthor(author);

        bookService.addBook(jb);
    }




    @PutMapping("/books/story/{id}")
    public void updateStoryBook(@PathVariable int id, @RequestBody AddStoryBookDTO book){

        StoryBook sb = (StoryBook) bookService.getBook(id);

        sb.setTitle(book.getTitle());
        sb.setGenre(book.getGenre());
        sb.setId(book.getId());

        bookService.updateBook(id,sb);
    }

    @PutMapping("/books/thesis/{id}")
    public void updateThesisBook(@PathVariable int id, @RequestBody AddThesisBookDTO book){

        ThesisBook tb = (ThesisBook) bookService.getBook(id);

        tb.setTitle(book.getTitle());
        tb.setTopic(book.getTopic());
        tb.setId(book.getId());
        bookService.updateBook(id,tb);
    }

    @PutMapping("/books/journal/{id}")
    public void updateJournalBook(@PathVariable int id, @RequestBody AddJournalBookDTO book){

        JournalBook jb = (JournalBook) bookService.getBook(id);

        jb.setTitle(book.getTitle());
        jb.setPublisher(book.getPublisher());
        jb.setId(book.getId());
        bookService.updateBook(id,jb);
    }


    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable int id){
         bookService.deleteBook(id);
    }


}
