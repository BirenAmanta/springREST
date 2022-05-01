package com.mindtree.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings({"all"})
public class Book {
	@Id
	private Integer bookId;
	@Column(name="book_name")
	private String title;
	private String authorName;
	@Column(name="year")
	private LocalDate publishedYear;
	private String publisher;
	private Long isbn;
	private Integer price;
}
