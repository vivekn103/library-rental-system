package com.te.brs.bookRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.brs.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByBookName(String bookName);

	boolean existsByBookName(String string);
	
}
