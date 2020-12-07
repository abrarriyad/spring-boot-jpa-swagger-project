package com.rashid.abrar.controller;

import com.rashid.abrar.model.Book;
import com.rashid.abrar.model.JournalBook;
import com.rashid.abrar.model.StoryBook;
import com.rashid.abrar.model.ThesisBook;
import com.rashid.abrar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book>  getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable int id){
        return  bookService.getBook(id);
    }

    @PostMapping("/books/story")
    public void addStoryBook(@RequestBody StoryBook storyBook){
        bookService.addBook(storyBook);
    }
    @PostMapping("/books/thesis")
    public void addThesisBook(@RequestBody ThesisBook thesisBook){
        bookService.addBook(thesisBook);
    }
    @PostMapping("/books/journal")
    public void addJournalBook(@RequestBody JournalBook journalBook){
        bookService.addBook(journalBook);
    }



    @PutMapping("/books/story/{id}")
    public void updateStoryBook(@PathVariable int id, @RequestBody StoryBook book){
        book.setId(id);
        bookService.updateBook(id,book);
    }

    @PutMapping("/books/thesis/{id}")
    public void updateThesisBook(@PathVariable int id, @RequestBody ThesisBook book){
        book.setId(id);
        bookService.updateBook(id,book);
    }

    @PutMapping("/books/journal/{id}")
    public void updateJournalBook(@PathVariable int id, @RequestBody JournalBook book){
        book.setId(id);
        bookService.updateBook(id,book);
    }


    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable int id){
         bookService.deleteBook(id);
    }


}
