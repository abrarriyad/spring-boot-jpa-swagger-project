package com.rashid.abrar.prototype;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.dto.AuthorUpdateDTO;
import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.model.StoryBook;
import com.rashid.abrar.model.ThesisBook;
import org.springframework.beans.support.PagedListHolder;

import java.util.ArrayList;
import java.util.List;

public class AuthorsPrototype{

    public static Author  aAuthor(){
        Author author = new Author();

        author.setPk(1);
        author.setId(1);
        author.setName("Humayun Ahmed");
        author.setEmail("humayun@gmail.com");
        return author;
    }

    public static AuthorDTO aDtoAuthor(){
        AuthorDTO authorDto = new AuthorDTO();

        authorDto.setId(1);
        authorDto.setName("Humayun Ahmed");
        authorDto.setEmail("humayun@gmail.com");
        return authorDto;
    }

    public static AuthorUpdateDTO aUpdateDtoAuthor(){
        AuthorUpdateDTO authorDto = new AuthorUpdateDTO();

        authorDto.setName("Humayun Ahmed");
        authorDto.setEmail("humayun@gmail.com");
        return authorDto;
    }

    public static List<Book> bookList(){
        List<Book> books = new ArrayList<>();

        StoryBook storyBook = new StoryBook();
        storyBook.setId(1);
        storyBook.setGenre("Drama");
        storyBook.setTitle("A Beautiful Mind");
        storyBook.setAuthor(aAuthor());

        ThesisBook thesisBook = new ThesisBook();
        thesisBook.setId(2);
        thesisBook.setTopic("Keyboard Interfacing");
        thesisBook.setTitle("Thesis Title");
        thesisBook.setAuthor(aAuthor());

        return books;
    }

    public static List<Book> storyBooks(){
        List<Book> books = new ArrayList<>();

        StoryBook storyBook = new StoryBook();

        storyBook.setId(1);
        storyBook.setGenre("Drama");
        storyBook.setTitle("A Beautiful Mind");
        storyBook.setAuthor(aAuthor());

        StoryBook storyBook2 = new StoryBook();

        storyBook2.setId(2);
        storyBook2.setGenre("Romacne");
        storyBook2.setTitle("Titanic");
        storyBook2.setAuthor(aAuthor());


        books.add(storyBook);
        books.add(storyBook2);

        return books;
    }

    public static List<Author> authorList(){
        List<Author> authors = new ArrayList<>();

        Author author1 = new Author();

        author1.setId(1);
        author1.setName("Humayun Ahmed");
        author1.setEmail("humayun@gmail.com");
        author1.setPk(0);
        author1.setBooks(bookList());

        Author author2 = new Author();

        author2.setId(2);
        author2.setName("Jafor Iqbal");
        author2.setEmail("iqbal@gmail.com");
        author2.setBooks(bookList());
        author1.setPk(0);

        Author author3 = new Author();

        author3.setId(3);
        author3.setName("Abdur Razzak");
        author3.setEmail("abdur@gmail.com");
        author3.setBooks(bookList());
        author1.setPk(0);

        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        return  authors;

    }
    public static List<Author> pagableAuthorList(){
        List<Author> authors = new ArrayList<>();

        Author author1 = new Author();

        author1.setId(1);
        author1.setName("Humayun Ahmed");
        author1.setEmail("humayun@gmail.com");
        author1.setPk(0);
        author1.setBooks(null);

        Author author2 = new Author();

        author2.setId(2);
        author2.setName("Jafor Iqbal");
        author2.setEmail("iqbal@gmail.com");
        author2.setBooks(null);
        author1.setPk(0);

        Author author3 = new Author();

        author3.setId(3);
        author3.setName("Abdur Razzak");
        author3.setEmail("abdur@gmail.com");
        author3.setBooks(null);
        author1.setPk(0);

        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        PagedListHolder page = new PagedListHolder(authors);
        page.setPage(0);
        page.setPageSize(10);

        return  page.getPageList();

    }
}
