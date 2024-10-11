package com.te.brs.bookService;

import org.springframework.http.ResponseEntity;

import com.te.brs.entity.BookDetails;
import com.te.brs.responseEntity.ResponseStructure;

public interface BookServices {
	public ResponseEntity<ResponseStructure<BookDetails>> getBooks();
}
