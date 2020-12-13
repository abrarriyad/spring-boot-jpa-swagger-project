package com.rashid.abrar.repository;

import com.rashid.abrar.model.Book;
import com.rashid.abrar.model.JournalBook;
import com.rashid.abrar.model.StoryBook;
import com.rashid.abrar.model.ThesisBook;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.rashid.abrar.util.Constants.*;

@Repository
public class BookDao {
    @PersistenceContext
    protected EntityManager entityManager;

    public List<Book> getAllBooksbyAuthorId(int id){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = query.from(Book.class);
        query.where(criteriaBuilder.equal(from.get("author"),id));

        return entityManager.createQuery(query).getResultList();

    }
    public List<Book> getAllBooksbyType(String type){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = query.from(Book.class);

        if(type.equals(STORY)) {
            query.where(criteriaBuilder.equal(from.type(), StoryBook.class));
        }
        else if(type.equals(THESIS)){
            query.where(criteriaBuilder.equal(from.type(), ThesisBook.class));
        }
        else if(type.equals(JOURNAL)){
            query.where(criteriaBuilder.equal(from.type(), JournalBook.class));
        }


        return entityManager.createQuery(query).getResultList();

    }

    public String getTypeOfBook(int id){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = query.from(Book.class);

        query.select(from);
        query.where(criteriaBuilder.equal(from.get("id"),id));

        return  entityManager.createQuery(query).getSingleResult().getType();


    }


}
