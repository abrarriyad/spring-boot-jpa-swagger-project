package com.rashid.abrar.service;

import com.rashid.abrar.model.Book;
import com.rashid.abrar.model.StoryBook;
import com.rashid.abrar.prototype.BookPrototype;
import com.rashid.abrar.repository.BookDaoImpl;
import com.rashid.abrar.repository.BookRepository;
import com.rashid.abrar.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import  com.rashid.abrar.prototype.AuthorsPrototype;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private  AuthorService authorService;
    @Mock
    private BookDaoImpl bookDaoImpl;



    @Test
    void testGetBookByIdSuccess() {

        when(bookRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(BookPrototype.aBook()));

        Book book = bookServiceImpl.getBook(1);

        assertThat(book).isNotNull();
        assertEquals(BookPrototype.aBook().getId(),book.getId());
        assertEquals(BookPrototype.aBook(),book);
    }

    @Test
    void testAddBookByAuthorsIdAndTypeSuccess() {
        when(authorService.getAuthorById(1)).thenReturn(AuthorsPrototype.aAuthor());
        when(modelMapper.map(any(), any())).thenReturn(BookPrototype.aBook());
        when(bookRepository.save(any(Book.class))).thenReturn(BookPrototype.aBook());

        bookServiceImpl.addBook(1,BookPrototype.aBookDto(),"StorY");

        verify(bookRepository, times(1)).save(isA(Book.class));

    }

    @Test
    void testGetAllBooksPagableSuccess() {

        when(bookRepository.findAll(ArgumentMatchers.isA(Pageable.class ))).thenReturn(new PageImpl<>(BookPrototype.pagableBookList()));

        List<Book> books = bookServiceImpl.getAllBooks(1,10,"id");

        assertThat(books).isNotNull();
        assertThat(books).size().isEqualTo(2);

    }

    @Test
    void testUpdateBookSuccess() {
        when(bookRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(BookPrototype.aBook()));
        when(bookRepository.save(any(Book.class))).thenReturn(BookPrototype.aBook());

        bookServiceImpl.updateBook(1,BookPrototype.aBookUpdateDto());

        verify(bookRepository, times(1)).save(isA(Book.class));
    }

    @Test
    void testDeleteBookSuccess() {

        doNothing().when(bookRepository).deleteById(1);
        bookServiceImpl.deleteBook(1);
        verify(bookRepository, times(1)).deleteById(isA(Integer.class));
    }


}