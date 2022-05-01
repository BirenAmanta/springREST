package com.mindtree.validator;

import java.time.LocalDate;

import com.mindtree.entity.Book;
import com.mindtree.exception.BookException;

public class Validator {

	public void validate(Book book) throws BookException {
		if (!validateYear(book.getPublishedYear())) {
			throw new BookException("Validator.INVALID_YEAR");
		}
	}

	private boolean validateYear(LocalDate publishedYear) {
		LocalDate now = LocalDate.now();
		if (now.compareTo(publishedYear) > 0) {
			return true;
		}
		return false;

	}

}
