package com.rashid.abrar.controller;

import com.rashid.abrar.dto.BookDTO;
import com.rashid.abrar.dto.BookUpdateDTO;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.prototype.AuthorsPrototype;
import com.rashid.abrar.prototype.BookPrototype;
import com.rashid.abrar.repository.BookDaoImpl;
import com.rashid.abrar.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static com.rashid.abrar.util.Constants.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
    @Mock
    private BookDaoImpl bookDaoImpl;

    @Test
    void testGetAllBooksSuccess() {
        when(bookService.getAllBooks(anyInt(), anyInt(), anyString())).thenReturn(AuthorsPrototype.bookList());

//        List<Book> books = bookController.getAllBooks(1,10,"id",null);

//        assertEquals(AuthorsPrototype.bookList(),books);
    }

    @Test
    void testGetBooksByTypeFilterSuccess() {
        when(bookDaoImpl.getAllBooksbyType(anyString())).thenReturn(AuthorsPrototype.storyBooks());
//        List<Book> books = bookController.getAllBooks(1,10,ID,STORY);

//        assertEquals(AuthorsPrototype.storyBooks(),books);
    }

    @Test
    void testGetBookByIdSuccess() {

        when(bookService.getBook(anyInt())).thenReturn(BookPrototype.aBook());

//        Book book = bookController.getBookById(1);
//
//        assertEquals(BookPrototype.aBook(),book);

    }

    @Test
    void testAddBookSuccess() {
        doNothing().when(bookService).addBook(anyInt(), any(BookDTO.class),anyString());
        bookController.addBook(1,BookPrototype.aBookDto(),STORY);
        verify(bookService, times(1)).addBook(anyInt(),isA(BookDTO.class), anyString());

    }

    @Test
    void testUpdateStoryBookSuccess() {
        doNothing().when(bookService).updateBook(anyInt(), any(BookUpdateDTO.class));
        bookController.updateStoryBook(1, BookPrototype.aBookUpdateDto());
        verify(bookService, times(1)).updateBook(anyInt(),  any(BookUpdateDTO.class));

    }

    @Test
    void testDeleteBookSuccess() {
        doNothing().when(bookService).deleteBook(anyInt());
        bookController.deleteBook(1);
        verify(bookService, times(1)).deleteBook(1);
    }
}