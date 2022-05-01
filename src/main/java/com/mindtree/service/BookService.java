package com.mindtree.service;

import java.time.LocalDate;
import java.util.List;

import com.mindtree.dto.BookDTO;
import com.mindtree.exception.BookException;

public interface BookService {
	public BookDTO getBookDetails(Integer bookId) throws BookException;

	public String addBook(BookDTO bookDTO) throws BookException;

	public List<BookDTO> getBookByAuthorName(String authorName) throws BookException;

	public List<BookDTO> getBookGreaterThanEqualToPrice(Integer price) throws BookException;

	public List<BookDTO> getBookLessThanPrice(Integer price) throws BookException;

	public List<BookDTO> bookPublishedBetweenYear(LocalDate startYear, LocalDate endYear) throws BookException;

	public List<BookDTO> bookPublishedAfterYear(LocalDate year) throws BookException;

	public List<BookDTO> getBookByAuthorNameAndPublisher(String authorName, String publisher) throws BookException;

	public void updateBookPrice(Integer bookId, Integer price) throws BookException;

	public void deleteBook(Integer bookId) throws BookException;

}
