package com.rashid.abrar.controller;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.prototype.AuthorsPrototype;
import com.rashid.abrar.service.AuthorService;
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

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;
    @Mock
    AuthorService authorService;



    @Test
    void testGetAllAuthorsSuccess() {
        when(authorService.getAllAuthors(anyInt(), anyInt(), any())).thenReturn(AuthorsPrototype.authorList());

        List<Author> authors = authorController.getAllAuthors(1,10,"id");

        assertEquals(AuthorsPrototype.authorList().size(), authors.size());
        assertEquals(AuthorsPrototype.authorList().get(0).getId(),authors.get(0).getId());
        assertEquals(AuthorsPrototype.authorList().get(0).getPk(), authors.get(0).getPk());
    }

    @Test
    void testGetAuthorByIdSuccess() {
        when(authorService.getAuthorById(anyInt())).thenReturn(AuthorsPrototype.aAuthor());

        Author author = authorController.getAuthorById(1);

        assertEquals(AuthorsPrototype.aAuthor().getId(), author.getId());
        assertEquals(AuthorsPrototype.aAuthor().getPk(), author.getPk());

    }

    @Test
    void testGetAllBooksByAuthorIdSuccess() {

        when(authorService.getAllBooksByAuthorId(anyInt())).thenReturn(AuthorsPrototype.bookList());

        List<Book> books = authorController.getAllBooksByAuthorId(1);

        assertEquals(AuthorsPrototype.bookList(), books);
        assertEquals(AuthorsPrototype.bookList().size(), books.size());
    }

    @Test
    void testAddAuthorSuccess() {
        doNothing().when(authorService).addAuthor(any(AuthorDTO.class));

        authorController.addAuthor(AuthorsPrototype.aDtoAuthor());

        verify(authorService, times(1)).addAuthor(isA(AuthorDTO.class));
    }

    @Test
    void testRemoveAuthorSuccess() {

        doNothing().when(authorService).removeAuthor(anyInt());

        authorController.removeAuthor(1);

        verify(authorService, times(1)).removeAuthor(1);
    }

    @Test
    void testUpdateAuthorByIdSuccess() {

        doReturn(1).when(authorService).updateAuthor(anyInt(), any(AuthorUpdateDTO.class));

        int count = authorController.updateAuthor(1, AuthorsPrototype.aUpdateDtoAuthor());

       assertEquals(1, count);

    }
}