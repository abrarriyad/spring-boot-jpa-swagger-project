package com.rashid.abrar.controller;
import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.dto.BookDTO;
import com.rashid.abrar.dto.BookUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.model.StoryBook;
import com.rashid.abrar.prototype.AuthorsPrototype;
import com.rashid.abrar.prototype.BookPrototype;
import com.rashid.abrar.springbootapi.SpringBootApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.rashid.abrar.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( classes = SpringBootApiApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllBooksWithoutParams_OK(){
        createBook();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/");
        ResponseEntity<BookDTO[]> booksListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), BookDTO[].class);

        assertEquals(HttpStatus.OK,booksListResponseEntity.getStatusCode());
    }
    @Test
    public void testGetAllBooksWithoutParams_NO_CONTENT(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/");
        ResponseEntity<BookDTO[]> booksListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), BookDTO[].class);

        assertEquals(HttpStatus.NO_CONTENT,booksListResponseEntity.getStatusCode());
    }
    @Test
    public void testGetAllBooksWithParams_OK(){
        createBook();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/")
                .queryParam(PAGE_NO,0)
                .queryParam(PAGE_SIZE, 10)
                .queryParam(SORT_BY, ID)
                .queryParam(TYPE,STORY);

        ResponseEntity<BookDTO[]> booksListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), BookDTO[].class);

        assertEquals(HttpStatus.OK,booksListResponseEntity.getStatusCode());
    }

    @Test
    public void testGetAllBooksWithParams_NO_CONTENT(){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/")
                .queryParam(PAGE_NO,0)
                .queryParam(PAGE_SIZE, 10)
                .queryParam(SORT_BY, ID)
                .queryParam(TYPE,STORY);

        ResponseEntity<BookDTO[]> booksListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), BookDTO[].class);

        assertEquals(HttpStatus.NO_CONTENT,booksListResponseEntity.getStatusCode());
    }



    @Test
    public void testPOSTBook_OK(){
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand  = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}")
                .queryParam(TYPE,STORY).buildAndExpand(params);

        HttpEntity<BookDTO> bookDTOHttpEntity = new HttpEntity<BookDTO>(BookPrototype.aBookDto());
        ResponseEntity<BookDTO> responseEntity = this.restTemplate.postForEntity(buildAndExpand.toUriString(),bookDTOHttpEntity,BookDTO.class);

        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode(),"Book Created Successfully");
    }

    @Test
    public void testGETBookByAuthorId_OK(){
        createBook();
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}").buildAndExpand(params);
        ResponseEntity<BookDTO> booksListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), BookDTO.class);

        assertEquals(HttpStatus.OK, booksListResponseEntity.getStatusCode());
    }
    @Test
    public void testGETBookByAuthorId_NOT_FOUND(){

        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}").buildAndExpand(params);
        ResponseEntity<BookDTO> booksListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), BookDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, booksListResponseEntity.getStatusCode());
    }

    @Test
    public void testUpdateBook_OK(){
        createBook();
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}").buildAndExpand(params);

        HttpEntity<BookUpdateDTO> bookUpdateDTOHttpEntity = new HttpEntity<BookUpdateDTO>(BookPrototype.aBookUpdateDto());
        ResponseEntity<BookUpdateDTO> responseEntity = this.restTemplate.exchange(buildAndExpand.toUriString(),HttpMethod.PUT, bookUpdateDTOHttpEntity,BookUpdateDTO.class);

        assertSame(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testUpdateBook_NOT_FOUND(){
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}").buildAndExpand(params);

        HttpEntity<BookUpdateDTO> bookUpdateDTOHttpEntity = new HttpEntity<BookUpdateDTO>(BookPrototype.aBookUpdateDto());
        ResponseEntity<BookUpdateDTO> responseEntity = this.restTemplate.exchange(buildAndExpand.toUriString(),HttpMethod.PUT, bookUpdateDTOHttpEntity,BookUpdateDTO.class);

        assertSame(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void testDeleteBook_NO_CONTENT(){
        createBook();
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}").buildAndExpand(params);

        ResponseEntity<String> deleteResponse = restTemplate.exchange(buildAndExpand.toUriString(),  HttpMethod.DELETE, new HttpEntity<>(null,null),String.class);
        assertSame(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    }

    @Test
    public void testDeleteBook_NOT_FOUND(){

        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}").buildAndExpand(params);

        ResponseEntity<String> deleteResponse = restTemplate.exchange(buildAndExpand.toUriString(),  HttpMethod.DELETE, new HttpEntity<>(null,null),String.class);
        assertSame(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());

    }

    public void createBook(){

        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand  = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/book/{id}")
                .queryParam(TYPE,STORY).buildAndExpand(params);

        HttpEntity<BookDTO> bookDTOHttpEntity = new HttpEntity<BookDTO>(BookPrototype.aBookDto());
        ResponseEntity<BookDTO> responseEntity = this.restTemplate.postForEntity(buildAndExpand.toUriString(),bookDTOHttpEntity,BookDTO.class);

    }




}
