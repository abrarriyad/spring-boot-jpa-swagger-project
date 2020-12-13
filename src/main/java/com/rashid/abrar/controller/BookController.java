package com.rashid.abrar.controller;

import com.rashid.abrar.dto.BookDTO;
import com.rashid.abrar.dto.BookUpdateDTO;
import com.rashid.abrar.model.*;
import com.rashid.abrar.repository.BookDao;
import com.rashid.abrar.service.AuthorService;
import com.rashid.abrar.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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


@RequestMapping( value = "/book", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private ModelMapper modelMapper;

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

            return bookDao.getAllBooksbyType(type);
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
            @RequestParam(name = "type") String type,
            @Min(1)@PathVariable int id,
           @Valid @RequestBody BookDTO bookDto){

        Author author =  authorService.getAuthorById(id);

        if(type.equals(STORY)){

            StoryBook sb = modelMapper.map(bookDto,StoryBook.class);
            sb.setAuthor(author);
            bookService.addBook(sb);
        }
        else if(type.equals(THESIS)){

            ThesisBook tb = modelMapper.map(bookDto, ThesisBook.class);
            tb.setAuthor(author);
            bookService.addBook(tb);
        }
        else if(type.equals(JOURNAL)){

            JournalBook jb = modelMapper.map(bookDto, JournalBook.class);
            jb.setAuthor(author);
            bookService.addBook(jb);
        }

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

        String type = bookDao.getTypeOfBook(id);

        if(type.equals(STORY)){
            StoryBook sb = (StoryBook) bookService.getBook(id);

            if(book.getTitle()!=null)
                sb.setTitle(book.getTitle());

            if(book.getGenre()!=null)
                sb.setGenre(book.getGenre());

            bookService.updateBook(id,sb);
        }

        else if(type.equals(JOURNAL)){

            JournalBook jb = (JournalBook) bookService.getBook(id);

            if(book.getTitle()!=null)
                jb.setTitle(book.getTitle());

            if(book.getPublisher()!=null)
                jb.setPublisher(book.getPublisher());


            bookService.updateBook(id,jb);

        }

        else if(type.equals(THESIS) ){

            ThesisBook tb = (ThesisBook) bookService.getBook(id);
            if(book.getTitle()!=null)
                tb.setTitle(book.getTitle());

            if(book.getTopic()!=null)
                tb.setTopic(book.getTopic());

            bookService.updateBook(id,tb);


        }

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
