package com.rashid.abrar.repository;

import com.rashid.abrar.model.Author;
import com.rashid.abrar.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Integer> {

}
