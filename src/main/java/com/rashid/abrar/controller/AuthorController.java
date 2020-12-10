package com.rashid.abrar.controller;


import com.rashid.abrar.dto.AddAuthorDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.service.AuthorService;
import com.rashid.abrar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/author")
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable int id){
        return  authorService.getAuthorById(id);
    }

    @GetMapping("/{id}/books")
    public List<Book> getAllBooksByAuthorId(@PathVariable int id){
        return authorService.getAllBooksByAuthorId(id);
    }

    @PostMapping("/add")
    public void addAuthor(@RequestBody AddAuthorDTO auth){

        Author author = new Author();

        author.setId(auth.getId());
        author.setName(auth.getName());
        author.setEmail(auth.getEmail());

        authorService.addAuthor(author);
    }

    @DeleteMapping("/remove/{id}")
    public void removeAuthor(@PathVariable int id){
        authorService.removeAuthor(id);
    }

    @PutMapping("/update/{author_id}")
    public Author updateAuthor(@PathVariable int author_id, @RequestBody AddAuthorDTO author){

        Author auth = authorService.getAuthorById(author_id);

        auth.setId(author.getId());
        auth.setEmail(author.getEmail());
        auth.setName(author.getEmail());
        auth.setBooks(authorService.getAllBooksByAuthorId(author_id));

        return authorService.updateAuthor(auth);
    }

}
