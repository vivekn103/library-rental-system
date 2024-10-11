package com.te.brs.bookServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.te.brs.bookRepository.BookRepository;
import com.te.brs.bookService.BookServices;
import com.te.brs.entity.Book;
import com.te.brs.entity.BookDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServicesIMPL implements BookServices {
	private final BookRepository bookRepository;
	
	@Override
	public ResponseEntity<BookDetails> getBooks() {
		Page<Book> page = bookRepository.findAll(PageRequest.of(0, 10));
		return ResponseEntity.ok(null);
	}

}
