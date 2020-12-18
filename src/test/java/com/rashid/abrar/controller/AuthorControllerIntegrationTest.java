package com.rashid.abrar.controller;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.prototype.AuthorsPrototype;
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
public class AuthorControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGETAuthorsWithoutParams_OK(){

        createAuthor();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/");
        ResponseEntity<Author[]> authorsListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), Author[].class);

        assertSame(HttpStatus.OK, authorsListResponseEntity.getStatusCode());
        assertSame(1,authorsListResponseEntity.getBody().length);
        assertSame(AuthorsPrototype.aDtoAuthor().getId(), authorsListResponseEntity.getBody()[0].getId());

    }

    @Test
    public void testGETAuthorsWithoutParams_NO_CONTENT(){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/");
        ResponseEntity<Author[]> authorsListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), Author[].class);

        assertSame(HttpStatus.NO_CONTENT, authorsListResponseEntity.getStatusCode());

    }


    @Test
    public void testGETRequestAuthorsWithParam_OK(){

        createAuthor();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/")
                .queryParam(PAGE_NO,0)
                .queryParam(PAGE_SIZE, 10)
                .queryParam(SORT_BY, ID);

        ResponseEntity<Author[]> authorsListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), Author[].class);


       assertSame(HttpStatus.OK, authorsListResponseEntity.getStatusCode());
       assertSame(1,authorsListResponseEntity.getBody().length);
       assertSame(AuthorsPrototype.aDtoAuthor().getId(), authorsListResponseEntity.getBody()[0].getId());

    }

    @Test
    public void testGETRequestAuthorsWithParam_NO_CONTENT(){

        //GET Author List
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/")
                .queryParam(PAGE_NO,0)
                .queryParam(PAGE_SIZE, 10)
                .queryParam(SORT_BY, ID);

        ResponseEntity<Author[]> authorsListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), Author[].class);

        assertSame(HttpStatus.NO_CONTENT, authorsListResponseEntity.getStatusCode());

    }


    @Test
    public void testGETAuthorById_OK(){
        createAuthor();
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/{id}").
                buildAndExpand(params);

        ResponseEntity<Author> authorResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), Author.class);

        assertSame(HttpStatus.OK, authorResponseEntity.getStatusCode());
        assertSame(AuthorsPrototype.aDtoAuthor().getId(), authorResponseEntity.getBody().getId());
    }

    @Test
    public void testGETAuthorById_NOT_FOUND(){
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/{id}").
                buildAndExpand(params);

        ResponseEntity<Author> authorResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), Author.class);

        assertSame(HttpStatus.NOT_FOUND, authorResponseEntity.getStatusCode());
   }


    @Test
    public void testGETBooksByAuthor_OK(){

        //GET Books
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/{id}/books").buildAndExpand(params);

        ResponseEntity<Book[]> authorsListResponseEntity = this.restTemplate.getForEntity(builder.toUriString(), Book[].class);
        assertSame(HttpStatus.NO_CONTENT, authorsListResponseEntity.getStatusCode());

    }

    @Test
    public void testPOSTAuthor_CREATED(){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/");
        HttpEntity<AuthorDTO> authorDTOHttpEntity = new HttpEntity<AuthorDTO>(AuthorsPrototype.aDtoAuthor());
        ResponseEntity<AuthorDTO> responseEntity = this.restTemplate.postForEntity(builder.toUriString(),authorDTOHttpEntity,AuthorDTO.class);

        assertSame(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testRemoveAuthor_NO_CONTENT(){
        createAuthor();

        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/{id}").
                buildAndExpand(params);

        ResponseEntity<String> deleteResponse = restTemplate.exchange(buildAndExpand.toUriString(),  HttpMethod.DELETE, new HttpEntity<>(null,null),String.class);
        assertSame(HttpStatus.NO_CONTENT,deleteResponse.getStatusCode());

    }

    @Test
    public void testRemoveAuthor_NOT_FOUND(){

        //DELETE Author
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/{id}").
                buildAndExpand(params);

        ResponseEntity<String> deleteResponse = restTemplate.exchange(buildAndExpand.toUriString(),  HttpMethod.DELETE, new HttpEntity<>(null,null),String.class);

        assertSame(HttpStatus.NOT_FOUND,deleteResponse.getStatusCode());

    }

    @Test
    public void testUpdateAuthor_OK(){

         createAuthor();
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/{id}").buildAndExpand(params);

        HttpEntity<AuthorUpdateDTO> authorUpdateEntity = new HttpEntity<AuthorUpdateDTO>(AuthorsPrototype.aUpdateDtoAuthor());
        ResponseEntity<AuthorUpdateDTO> responseEntity = this.restTemplate.exchange(buildAndExpand.toUriString(),HttpMethod.PUT, authorUpdateEntity,AuthorUpdateDTO.class);

        assertSame(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateAuthor_NOT_FOUND(){

        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1");

        UriComponents buildAndExpand = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/{id}").buildAndExpand(params);

        HttpEntity<AuthorUpdateDTO> authorUpdateEntity = new HttpEntity<AuthorUpdateDTO>(AuthorsPrototype.aUpdateDtoAuthor());
        ResponseEntity<AuthorUpdateDTO> responseEntity = this.restTemplate.exchange(buildAndExpand.toUriString(),HttpMethod.PUT, authorUpdateEntity,AuthorUpdateDTO.class);

        assertSame(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }


    public void createAuthor(){

        // POST Author
        UriComponentsBuilder postBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/author/");
        HttpEntity<AuthorDTO> authorDTOHttpEntity = new HttpEntity<AuthorDTO>(AuthorsPrototype.aDtoAuthor());
        ResponseEntity<AuthorDTO> responseEntity = this.restTemplate.postForEntity(postBuilder.toUriString(),authorDTOHttpEntity,AuthorDTO.class);

    }


}
