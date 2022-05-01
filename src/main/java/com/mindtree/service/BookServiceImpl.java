package com.mindtree.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dto.BookDTO;
import com.mindtree.entity.Book;
import com.mindtree.exception.BookException;
import com.mindtree.repository.BookRepository;
import com.mindtree.validator.Validator;

import javax.transaction.Transactional;

@Service(value="bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public BookDTO getBookDetails(Integer bookId) throws BookException {
		Optional<Book> data = bookRepository.findById(bookId);
		Book book = data.orElseThrow(() -> new BookException("Service.BOOK_DETAILS_NOT_FOUND"));
		BookDTO result = new BookDTO(book.getBookId(), book.getTitle(), book.getAuthorName(), book.getPublishedYear(),
				book.getPublisher(), book.getIsbn(), book.getPrice());
		return result;

	}

	@Override
	public String addBook(BookDTO bookDTO) throws BookException {
		Optional<Book> option = bookRepository.findById(bookDTO.getBookId());
		if (!option.isPresent()) {
			Book book = new Book(bookDTO.getBookId(), bookDTO.getTitle(), bookDTO.getAuthorName(),
					bookDTO.getPublishedYear(), bookDTO.getPublisher(), bookDTO.getIsbn(), bookDTO.getPrice());
			new Validator().validate(book);
			return bookRepository.save(book).getTitle();
		} else {
			throw new BookException("Service.BOOK_ALREADY_PRESENT");
		}
	}

	@Override
	public List<BookDTO> getBookByAuthorName(String authorName) throws BookException {
		List<Book> options = bookRepository.findByAuthorName(authorName);
		List<BookDTO> allList;
		if (options.size() > 0) {
			allList = new ArrayList<>();
			options.stream().forEach((data) -> {
				BookDTO temp = new BookDTO();
				temp.setBookId(data.getBookId());
				temp.setAuthorName(data.getAuthorName());
				temp.setIsbn(data.getIsbn());
				temp.setPrice(data.getPrice());
				temp.setPublishedYear(data.getPublishedYear());
				temp.setPublisher(data.getPublisher());
				temp.setTitle(data.getTitle());
				allList.add(temp);
			});
			return allList;
		}
		throw new BookException("Service.BOOKS_NOT_FOUND");
	}

	@Override
	public List<BookDTO> getBookGreaterThanEqualToPrice(Integer price) throws BookException {
		List<Book> options = bookRepository.findByPriceGreaterThanEqual(price);
		List<BookDTO> allList;
		if (options.size() > 0) {
			allList = new ArrayList<>();
			options.stream().forEach((data) -> {
				BookDTO temp = new BookDTO();
				temp.setBookId(data.getBookId());
				temp.setAuthorName(data.getAuthorName());
				temp.setIsbn(data.getIsbn());
				temp.setPrice(data.getPrice());
				temp.setPublishedYear(data.getPublishedYear());
				temp.setPublisher(data.getPublisher());
				temp.setTitle(data.getTitle());
				allList.add(temp);
			});
			return allList;
		}
		throw new BookException("Service.BOOKS_NOT_FOUND");
	}

	@Override
	public List<BookDTO> getBookLessThanPrice(Integer price) throws BookException {
		List<Book> options = bookRepository.findByPriceLessThan(price);
		List<BookDTO> allList;
		if (options.size() > 0) {
			allList = new ArrayList<>();
			options.stream().forEach((data) -> {
				BookDTO temp = new BookDTO();
				temp.setBookId(data.getBookId());
				temp.setAuthorName(data.getAuthorName());
				temp.setIsbn(data.getIsbn());
				temp.setPrice(data.getPrice());
				temp.setPublishedYear(data.getPublishedYear());
				temp.setPublisher(data.getPublisher());
				temp.setTitle(data.getTitle());
				allList.add(temp);
			});
			return allList;
		}
		throw new BookException("Service.BOOKS_NOT_FOUND");
	}

	@Override
	public List<BookDTO> bookPublishedBetweenYear(LocalDate startYear, LocalDate endYear) throws BookException {
		List<Book> options = bookRepository.findByPublishedYearBetween(startYear, endYear);
		List<BookDTO> allList;
		if (options.size() > 0) {
			allList = new ArrayList<>();
			options.stream().forEach((data) -> {
				BookDTO temp = new BookDTO();
				temp.setBookId(data.getBookId());
				temp.setAuthorName(data.getAuthorName());
				temp.setIsbn(data.getIsbn());
				temp.setPrice(data.getPrice());
				temp.setPublishedYear(data.getPublishedYear());
				temp.setPublisher(data.getPublisher());
				temp.setTitle(data.getTitle());
				allList.add(temp);
			});
			return allList;
		}
		throw new BookException("Service.BOOKS_NOT_FOUND");
	}

	@Override
	public List<BookDTO> bookPublishedAfterYear(LocalDate year) throws BookException {
		List<Book> options = bookRepository.findByPublishedYearAfter(year);
		List<BookDTO> allList;
		if (options.size() > 0) {
			allList = new ArrayList<>();
			options.stream().forEach((data) -> {
				BookDTO temp = new BookDTO();
				temp.setBookId(data.getBookId());
				temp.setAuthorName(data.getAuthorName());
				temp.setIsbn(data.getIsbn());
				temp.setPrice(data.getPrice());
				temp.setPublishedYear(data.getPublishedYear());
				temp.setPublisher(data.getPublisher());
				temp.setTitle(data.getTitle());
				allList.add(temp);
			});
			return allList;
		}
		throw new BookException("Service.BOOKS_NOT_FOUND");
	}

	@Override
	public List<BookDTO> getBookByAuthorNameAndPublisher(String authorName, String publisher) throws BookException {
		List<Book> options = bookRepository.findByAuthorNameAndPublisher(authorName, publisher);
		List<BookDTO> allList;
		if (options.size() > 0) {
			allList = new ArrayList<>();
			options.stream().forEach((data) -> {
				BookDTO temp = new BookDTO();
				temp.setBookId(data.getBookId());
				temp.setAuthorName(data.getAuthorName());
				temp.setIsbn(data.getIsbn());
				temp.setPrice(data.getPrice());
				temp.setPublishedYear(data.getPublishedYear());
				temp.setPublisher(data.getPublisher());
				temp.setTitle(data.getTitle());
				allList.add(temp);
			});
			return allList;
		}
		throw new BookException("Service.BOOKS_NOT_FOUND");
	}

	@Override
	public void updateBookPrice(Integer bookId, Integer price) throws BookException {
		Optional<Book> data = bookRepository.findById(bookId);
		Book book = data.orElseThrow(() -> new BookException("Service.BOOK_NOT_FOUND"));
		book.setPrice(price);
	}

	@Override
	public void deleteBook(Integer bookId) throws BookException {
		Optional<Book> data = bookRepository.findById(bookId);
		data.orElseThrow(() -> new BookException("Service.BOOK_NOT_FOUND"));
		bookRepository.deleteById(bookId);

	}

}
