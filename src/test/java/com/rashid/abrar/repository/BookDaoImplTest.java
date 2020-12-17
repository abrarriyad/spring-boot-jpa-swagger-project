package com.rashid.abrar.repository;

import com.rashid.abrar.model.Book;
import com.rashid.abrar.prototype.AuthorsPrototype;
import com.rashid.abrar.prototype.BookPrototype;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class BookDaoImplTest {

    @InjectMocks
    private BookDaoImpl bookDao;
    @Mock
    private EntityManager entityManager;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<Book> query;
    @Mock
    private Root<Book> from;



    @Test
    void testGetAllBooksByAuthorIdSuccess() {

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Book.class)).thenReturn(query);
        when(query.from(Book.class)).thenReturn(from);

        when(entityManager.createQuery(query).getResultList()).thenReturn(AuthorsPrototype.bookList());

        List<Book> books = bookDao.getAllBooksbyAuthorId(1);

//        assertEquals(AuthorsPrototype.bookList(), books);


    }

    @Test
    void getAllBooksbyType() {
    }
}