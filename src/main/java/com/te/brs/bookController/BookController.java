package com.te.brs.bookController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.brs.bookService.BookServices;
import com.te.brs.responseEntity.ResponseStructure;
import com.te.brs.entity.BookDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/library-book-managment")
@RequiredArgsConstructor
public class BookController {
	
	private final BookServices bookServices;
	
	public ResponseEntity<ResponseStructure<BookDetails>> getBooks(){
		return bookServices.getBooks();
	}
}
