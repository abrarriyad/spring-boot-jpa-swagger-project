package com.rashid.abrar.controller;


import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.rashid.abrar.util.Constants.*;
import static com.rashid.abrar.util.Constants.INTERNAL_SERVER_ERROR;

@RequestMapping(value = "/author", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @GetMapping("/")
    @ApiOperation(
            value = "Get All Authors",
            notes = "Get All Authors information.",
            response = Author.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Author.class),
            @ApiResponse(code = 204, message = NO_AUTHOR_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)

    })
    public ResponseEntity<List<Author>> getAllAuthors(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        List<Author> allAuthors = authorService.getAllAuthors(pageNo,pageSize,sortBy);
        if(allAuthors.isEmpty()) {
            return new ResponseEntity<List<Author>>(allAuthors, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<List<Author>>(allAuthors, HttpStatus.OK);

        }
    }



    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get Author",
            notes = "Get Author information by author Id",
            response = Author.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Author.class),
            @ApiResponse(code = 204, message = NO_AUTHOR_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public ResponseEntity<Author> getAuthorById(@PathVariable int id){
       Author author = authorService.getAuthorById(id);

       if(author != null){
           return new ResponseEntity<Author>(author, HttpStatus.OK);
       }
       else{
           return new ResponseEntity<Author>(author, HttpStatus.NOT_FOUND);
       }
    }


    @GetMapping("/{id}/books")
    @ApiOperation(
            value = "Get All Books by an Author",
            notes = "Get All Books by an Author by author Id",
            response = Author.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Author.class),
            @ApiResponse(code = 204, message = NO_AUTHOR_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public ResponseEntity< List<Book> > getAllBooksByAuthorId(@PathVariable int id){
        List<Book> books = authorService.getAllBooksByAuthorId(id);

        if(books.isEmpty()){
            return new ResponseEntity<List<Book>>(books, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        }
    }


    @PostMapping("/")
    @ApiOperation(
            value = "Add New Author",
            notes = "Add New Author",
            response = Author.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Author.class),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public ResponseEntity addAuthor(@Valid @RequestBody AuthorDTO authorDTO){
        try {
            authorService.addAuthor(authorDTO);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete Author",
            notes = "Delete Author by Id ",
            response = Author.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Author.class),
            @ApiResponse(code = 204, message = NO_AUTHOR_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public ResponseEntity removeAuthor(@PathVariable int id){
        try {
            authorService.removeAuthor(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }catch (Exception e){

            return new ResponseEntity((HttpStatus.NOT_FOUND));
        }


    }


    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update Author",
            notes = "Update Author information by author Id",
            response = Author.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Author.class),
            @ApiResponse(code = 204, message = NO_AUTHOR_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public ResponseEntity updateAuthor(@PathVariable int id, @Valid @RequestBody AuthorUpdateDTO authorDTO){

      try{
          int i = authorService.updateAuthor(id,authorDTO);
          if(i>0)
           return new ResponseEntity(HttpStatus.OK);
          else
              return new ResponseEntity(HttpStatus.NOT_FOUND);
       }
       catch (Exception e){
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }
    }

}
