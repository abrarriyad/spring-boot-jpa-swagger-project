package com.rashid.abrar.controller;


import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.service.AuthorService;
import com.rashid.abrar.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.rashid.abrar.util.Constants.*;
import static com.rashid.abrar.util.Constants.INTERNAL_SERVER_ERROR;

@RequestMapping(value = "/author", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;


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
    public List<Author> getAllAuthors(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return authorService.getAllAuthors(pageNo,pageSize,sortBy);
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
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public Author getAuthorById(@Min(1) @PathVariable int id){
        return  authorService.getAuthorById(id);
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
    public List<Book> getAllBooksByAuthorId(@Min(1) @PathVariable int id){
        return authorService.getAllBooksByAuthorId(id);
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
    public void addAuthor(@Valid @RequestBody AuthorDTO authorDTO){

        authorService.addAuthor(authorDTO);
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
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public void removeAuthor(@PathVariable int id){
        authorService.removeAuthor(id);
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
    public void updateAuthor(@PathVariable int id, @Valid @RequestBody AuthorUpdateDTO authorDTO){

        authorService.updateAuthor(id,authorDTO);
    }

}
