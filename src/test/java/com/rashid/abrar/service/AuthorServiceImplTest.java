package com.rashid.abrar.service;

import com.rashid.abrar.model.Author;
import com.rashid.abrar.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import static org.mockito.ArgumentMatchers.any;
import  com.rashid.abrar.prototype.AuthorsPrototype;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testAddAuthor(){


     when(authorRepository.save(any(Author.class))).thenReturn(AuthorsPrototype.aAuthor());
     when(modelMapper.map(any(),any())).thenReturn(AuthorsPrototype.aAuthor());
     authorService.addAuthor(AuthorsPrototype.aDtoAuthor());

     verify(authorRepository).save(isA(Author.class));


    }

}