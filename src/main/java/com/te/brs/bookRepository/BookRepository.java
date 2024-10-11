package com.te.brs.bookRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.brs.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
