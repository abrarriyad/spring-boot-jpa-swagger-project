package com.rashid.abrar.service;

import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.repository.AuthorRepository;
import com.rashid.abrar.repository.BookDao;
import com.rashid.abrar.repository.BookDaoImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import  com.rashid.abrar.prototype.AuthorsPrototype;
import org.springframework.data.domain.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private BookDaoImpl bookDao;


    @Test
    public void testAddAuthorSuccess(){

     when(authorRepository.save(any(Author.class))).thenReturn(AuthorsPrototype.aAuthor());
     when(modelMapper.map(any(),any())).thenReturn(AuthorsPrototype.aAuthor());

     authorService.addAuthor(AuthorsPrototype.aDtoAuthor());
     verify(authorRepository).save(isA(Author.class));

    }

    @Test
    public void testGetAllAuthorsSuccess(){

      when(authorRepository.findAll(ArgumentMatchers.isA(Pageable.class))).thenReturn( new PageImpl<>(AuthorsPrototype.pagableAuthorList()) );

      List<Author> authors = authorService.getAllAuthors(0,10,"id");

      assertThat(authors).isNotNull();
      assertThat(authors).size().isEqualTo(3);

    }

    @Test
    public void testGetAllBooksByAuthorIdSuccess(){
        when(bookDao.getAllBooksbyAuthorId(1)).thenReturn(AuthorsPrototype.bookList());

        List<Book> books = authorService.getAllBooksByAuthorId(1);

        assertEquals(AuthorsPrototype.bookList(), books);
    }

    @Test
    public void testGetAuthorsByIdSuccess(){
        when(authorRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(AuthorsPrototype.aAuthor()));

        Author author = authorService.getAuthorById(1);

        assertThat(author).isNotNull();
        assertEquals(AuthorsPrototype.aAuthor().getId(), author.getId());
    }


}