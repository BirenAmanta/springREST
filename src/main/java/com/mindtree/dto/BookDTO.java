package com.mindtree.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings({"all"})
public class BookDTO {
	private Integer bookId;
	private String title;
	private String authorName;
	private LocalDate publishedYear;
	private String publisher;
	private Long isbn;
	private Integer price;
}
