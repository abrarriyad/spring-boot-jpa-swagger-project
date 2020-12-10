package com.rashid.abrar.repository;

import com.rashid.abrar.model.Book;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CommonRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    public List<Book> getAllBooksbyAuthorId(int id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = query.from(Book.class);
//        query.equals(criteriaBuilder.equal(from.get("author_id"),id))
        query.where(criteriaBuilder.equal(from.get("author"),id));

        return entityManager.createQuery(query).getResultList();


    }
}
