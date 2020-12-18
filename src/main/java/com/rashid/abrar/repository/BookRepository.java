package com.rashid.abrar.repository;

import com.rashid.abrar.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Integer> {


}
