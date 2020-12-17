package com.rashid.abrar.controller;

import com.rashid.abrar.dto.BookDTO;
import com.rashid.abrar.dto.BookUpdateDTO;
import com.rashid.abrar.model.*;
import com.rashid.abrar.repository.BookDaoImpl;
import com.rashid.abrar.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.rashid.abrar.util.Constants.*;


@RequestMapping( value = "/book", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookDaoImpl bookDaoImpl;


    @GetMapping("/")
    @ApiOperation(
            value = "Get All Books",
            notes = "Get All Book information.",
            response = Book.class
            )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public List<Book>  getAllBooks(
            @ApiParam(value = "Filter (can be 'story' or 'thesis' or 'journal )")
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(name = "type", required = false)  String type ){
        if(type!="" && type!=null){

            return bookDaoImpl.getAllBooksbyType(type);
        }
        return bookService.getAllBooks(pageNo,pageSize,sortBy);
    }


    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get Book by id.",
            notes = "Get Book information by book id",
            response = Book.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    public Book getBookById(@PathVariable int id){
        return  bookService.getBook(id);
    }



    @ApiOperation(
            value = "Add new book",
            notes = "Add new book information.",
            response = BookDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    @PostMapping("/{id}")
    public void addBook(
            @ApiParam(value = "Book type ('story' or 'thesis' or 'journal)")
            @Min(1)@PathVariable int id,
            @Valid @RequestBody BookDTO bookDto,
            @RequestParam(name = "type") String type){

        bookService.addBook(id,bookDto,type);


    }

    @ApiOperation(
            value = "Update book",
            notes = "Update book information.",
            response = BookDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })

    @PutMapping("/{id}")
    public void updateStoryBook(@PathVariable int id, @Valid @RequestBody BookUpdateDTO book){

        bookService.updateBook(id,book);

    }

    @ApiOperation(
            value = "Delete book",
            notes = "Delete book by id.",
            response = BookDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR)
    })
    @DeleteMapping("/{id}")
    public void deleteBook(@Min(1)@PathVariable int id){
         bookService.deleteBook(id);
    }


}
