package com.rashid.abrar.repository;

import com.rashid.abrar.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author,Integer> {

}
