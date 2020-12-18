package com.rashid.abrar.controller;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.exception.AuthorNotFoundException;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.prototype.AuthorsPrototype;
import com.rashid.abrar.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

        ResponseEntity<List<Author>> responseEntity = authorController.getAllAuthors(1,10,"id");

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(AuthorsPrototype.authorList().size(), responseEntity.getBody().size());
        assertEquals(AuthorsPrototype.authorList().get(0).getId(), responseEntity.getBody().get(0).getId());
    }

    @Test
    void testGetAllAuthors_NO_CONTENT() {
        when(authorService.getAllAuthors(anyInt(), anyInt(), any())).thenReturn(null);

        ResponseEntity<List<Author>> responseEntity = authorController.getAllAuthors(1,10,"id");

        assertEquals(HttpStatus.NO_CONTENT,responseEntity.getStatusCode());

    }

    @Test
    void testGetAuthorByIdSuccess() {
        when(authorService.getAuthorById(anyInt())).thenReturn(AuthorsPrototype.aAuthor());

        ResponseEntity<Author> responseEntity = authorController.getAuthorById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(AuthorsPrototype.aAuthor().getId(), responseEntity.getBody().getId());

    }

    @Test
    void testGetAuthorById_NO_CONTENT() {
        when(authorService.getAuthorById(anyInt())).thenReturn(null);

        ResponseEntity<Author> responseEntity = authorController.getAuthorById(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    void testGetAllBooksByAuthorIdSuccess() {

        when(authorService.getAllBooksByAuthorId(anyInt())).thenReturn(AuthorsPrototype.bookList());

        ResponseEntity< List<Book>> responseEntity = authorController.getAllBooksByAuthorId(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(AuthorsPrototype.bookList(), responseEntity.getBody());
    }
    @Test
    void testGetAllBooksByAuthorId_NO_CONTENT() {

        when(authorService.getAllBooksByAuthorId(anyInt())).thenReturn(null);

        ResponseEntity< List<Book>> responseEntity = authorController.getAllBooksByAuthorId(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    void testAddAuthorSuccess() {
        doNothing().when(authorService).addAuthor(any(AuthorDTO.class));

        ResponseEntity responseEntity = authorController.addAuthor(AuthorsPrototype.aDtoAuthor());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(authorService, times(1)).addAuthor(isA(AuthorDTO.class));
    }


    @Test
    void testRemoveAuthorSuccess() {

        doNothing().when(authorService).removeAuthor(anyInt());

        ResponseEntity responseEntity = authorController.removeAuthor(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(authorService, times(1)).removeAuthor(1);
    }

    @Test
    void testRemoveAuthorNOT_FOUND() {

        doThrow(new AuthorNotFoundException()).when(authorService).removeAuthor(anyInt());

        ResponseEntity responseEntity = authorController.removeAuthor(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(authorService, times(1)).removeAuthor(1);
    }


    @Test
    void testUpdateAuthorByIdSuccess() {

        doReturn(1).when(authorService).updateAuthor(anyInt(), any(AuthorUpdateDTO.class));

        ResponseEntity responseEntity = authorController.updateAuthor(1, AuthorsPrototype.aUpdateDtoAuthor());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void testUpdateAuthorByIdBAD_REQUEST() {

        when(authorService.updateAuthor(anyInt(), any(AuthorUpdateDTO.class))).thenThrow(new AuthorNotFoundException());
        ResponseEntity responseEntity = authorController.updateAuthor(1, AuthorsPrototype.aUpdateDtoAuthor());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }


}