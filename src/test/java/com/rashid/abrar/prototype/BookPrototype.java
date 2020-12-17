package com.rashid.abrar.prototype;

import com.rashid.abrar.dto.BookDTO;
import com.rashid.abrar.dto.BookUpdateDTO;
import com.rashid.abrar.model.Book;
import com.rashid.abrar.model.StoryBook;
import com.rashid.abrar.model.ThesisBook;
import org.springframework.beans.support.PagedListHolder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookPrototype {

    public static Book aBook(){
        StoryBook book = new StoryBook();

        book.setTitle("A Beautiful Mind");
        book.setGenre("Drama");
        book.setId(1);

        return book;

    }
    public static Optional<Book> aOptionalBook(){
        StoryBook book = new StoryBook();

        book.setTitle("A Beautiful Mind");
        book.setGenre("Drama");
        book.setId(1);

        return Optional.ofNullable(book);

    }

    public static BookDTO aBookDto(){

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1);
        bookDTO.setGenre("Drama");
        bookDTO.setTitle("A Beautiful Mind");

        return  bookDTO;
    }

    public static BookUpdateDTO aBookUpdateDto(){

        BookUpdateDTO bookDTO = new BookUpdateDTO();
        bookDTO.setGenre("Drama");
        bookDTO.setTitle("A Beautiful Mind");

        return  bookDTO;
    }


    public static List<Book> pagableBookList(){

        List<Book> bookList = new ArrayList<>();

        StoryBook storyBook = new StoryBook();

        storyBook.setTitle("A Beautiful Mind");
        storyBook.setGenre("Drama");
        storyBook.setId(1);


        ThesisBook thesisBook = new ThesisBook();
        thesisBook.setId(2);
        thesisBook.setTopic("Keyboard Interfacing");
        thesisBook.setTitle("Thesis Title");

        bookList.add(storyBook);
        bookList.add(thesisBook);

        PagedListHolder page = new PagedListHolder(bookList);
        page.setPage(0);
        page.setPageSize(10);

        return page.getPageList();
    }
}
