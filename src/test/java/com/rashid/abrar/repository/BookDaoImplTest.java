package com.rashid.abrar.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookDaoImplTest {

    @Mock
    private BookDao bookDao;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private  BookRepository bookRepository;

    @Test
    void getAllBooksbyAuthorId() {

    }
}