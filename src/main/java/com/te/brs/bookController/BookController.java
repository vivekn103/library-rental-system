package com.te.brs.bookController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.brs.bookService.BookServices;
import com.te.brs.dto.BookDTO;
import com.te.brs.dto.GetBookDTO;
import com.te.brs.dto.RentBookDTO;
import com.te.brs.exception.BookNotFoundException;
import com.te.brs.exception.BookOutOfStock;
import com.te.brs.exception.CustomerNotFoundException;
import com.te.brs.exception.DuplicateBookException;
import com.te.brs.exception.NoBooksAvailable;
import com.te.brs.responseEntity.ResponseStructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/library-book-managment")
@RequiredArgsConstructor
public class BookController {
	
	private final BookServices bookServices;
	
	@GetMapping(path = "/get-books/{pageNumber}")
	public ResponseEntity<List<GetBookDTO>> getAllBooks(@PathVariable int pageNumber) throws NoBooksAvailable{
		return bookServices.getAllBooks(pageNumber);
	}
	
	@GetMapping(path="/get-book/{bookName}")
	public ResponseEntity<RentBookDTO> getBook(@PathVariable String bookName) throws BookNotFoundException{
		return bookServices.getBook(bookName);
	}
	
	@GetMapping(path="/purchase-book/{bookId}/{email}")
	public ResponseEntity<ResponseStructure<RentBookDTO>> rentBook(@PathVariable int bookId,@PathVariable String email) throws CustomerNotFoundException, DuplicateBookException, BookOutOfStock{
		return bookServices.rentBook(bookId, email);
	}
	
	@PostMapping(path="/saveBook")
	public ResponseEntity<ResponseStructure<BookDTO>> saveBook(@Valid @RequestBody BookDTO bookDTO) throws DuplicateBookException{
		return bookServices.saveBook(bookDTO);
	}
	
	public ResponseEntity<List<BookDTO>> saveAllBooks(@Valid @RequestBody List<BookDTO> bookDTOs){
		return bookServices.saveAllBooks(bookDTOs);
	}
	
}
