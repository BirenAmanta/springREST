package com.mindtree.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	public List<Book> findByAuthorName(String name);
	public List<Book> findByPriceGreaterThanEqual(Integer Price);
	public List<Book>	findByPriceLessThan(Integer Price);
	public List<Book> findByPublishedYearBetween(LocalDate startYear,LocalDate endYear);
	public List<Book> findByPublishedYearAfter(LocalDate year);
	public List<Book> findByAuthorNameAndPublisher(String authorName, String publisher);
}
