package com.te.brs.bookService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.te.brs.dto.BookDTO;
import com.te.brs.dto.GetBookDTO;
import com.te.brs.dto.RentBookDTO;
import com.te.brs.exception.BookNotFoundException;
import com.te.brs.exception.BookOutOfStock;
import com.te.brs.exception.CustomerNotFoundException;
import com.te.brs.exception.DuplicateBookException;
import com.te.brs.exception.NoBooksAvailable;
import com.te.brs.responseEntity.ResponseStructure;

public interface BookServices {
	public ResponseEntity<ResponseStructure<BookDTO>> saveBook(BookDTO bookDTOS);
	
	public ResponseEntity<List<BookDTO>> saveAllBooks(List<BookDTO> bookDTOs);
	
	public ResponseEntity<List<GetBookDTO>>  getAllBooks(int pageNumber) throws NoBooksAvailable;
	
	public List<GetBookDTO> getAllBooksByName(String bookName) throws BookNotFoundException;
	
	public ResponseEntity<RentBookDTO> getBook(String bookName) throws BookNotFoundException;
	
	public ResponseEntity<ResponseStructure<RentBookDTO>> rentBook(int bookId,String email) throws CustomerNotFoundException, DuplicateBookException,BookOutOfStock;
}
