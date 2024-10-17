package com.te.brs.bookServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.te.brs.bookRepository.BookRepository;
import com.te.brs.bookService.BookServices;
import com.te.brs.customerRepository.CustomerRepository;
import com.te.brs.dto.BookDTO;
import com.te.brs.dto.GetBookDTO;
import com.te.brs.dto.RentBookDTO;
import com.te.brs.entity.Book;
import com.te.brs.entity.Customer;
import com.te.brs.exception.BookNotFoundException;
import com.te.brs.exception.BookOutOfStock;
import com.te.brs.exception.CustomerNotFoundException;
import com.te.brs.exception.DuplicateBookException;
import com.te.brs.exception.NoBooksAvailable;
import com.te.brs.responseEntity.ResponseStructure;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServicesIMPL implements BookServices {
	private final BookRepository bookRepository;
	private final CustomerRepository customerRepository;
	

	@Override
	public ResponseEntity<ResponseStructure<BookDTO>> saveBook(BookDTO bookDTOS) {
		if(bookRepository.existsByBookName(bookDTOS.getBookName())) {
			Book byBookName = bookRepository.findByBookName(bookDTOS.getBookName());
			byBookName.setQuantity(byBookName.getQuantity()+1);
			bookRepository.save(byBookName);
			
			
//			BeanUtils.copyProperties(byBookName,bookDTOS);
			
			BookDTO dto = BookDTO.builder().bookId(byBookName.getBookId())
					.bookName(byBookName.getBookName())
					.author(byBookName.getAuthor())
					.bookDiscription(byBookName.getBookDiscription())
					.publishedBy(byBookName.getPublishedBy())
					.publishedDate(byBookName.getPublishedDate())
					.ratePerHour(byBookName.getRatePerHour())
					.quantity(byBookName.getQuantity())
					.isAvailable(byBookName.getIsAvailable()) 
					.build();
					
					ResponseStructure<BookDTO> responseStructure = new ResponseStructure().setData(dto).setError(false).setMessage("Saved Book Successfully").setStatusCode(HttpStatus.CREATED.value());
					return ResponseEntity.ok(responseStructure);
			
		}else {
			Book book = new Book();
			BeanUtils.copyProperties(bookDTOS, book);
			Book savedBook = bookRepository.save(book);
			
			
			BookDTO dto = BookDTO.builder().bookId(savedBook.getBookId())
			.bookName(savedBook.getBookName())
			.author(savedBook.getAuthor())
			.bookDiscription(savedBook.getBookDiscription())
			.publishedBy(savedBook.getPublishedBy())
			.publishedDate(savedBook.getPublishedDate())
			.ratePerHour(savedBook.getRatePerHour())
			.quantity(savedBook.getQuantity())
			.isAvailable(savedBook.getIsAvailable()) 
			.build();
			
			ResponseStructure<BookDTO> responseStructure = new ResponseStructure().setData(dto).setError(false).setMessage("Saved Book Successfully").setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.ok(responseStructure);
		}
		
	}

	@Override
	public ResponseEntity<List<BookDTO>> saveAllBooks(List<BookDTO> bookDTOs) {
		
		List<Book> books = new ArrayList();
		bookDTOs.stream().forEach(t -> {
			Book book =new Book();
			BeanUtils.copyProperties(t, book);
			books.add(book);
		});
		
		List<Book> saveAll = bookRepository.saveAll(books);
		
		List<BookDTO> booksdto = new ArrayList();
		saveAll.stream().forEach(t ->{
			BookDTO bookDTO = new BookDTO();
			BeanUtils.copyProperties(t, bookDTO);
			booksdto.add(bookDTO);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(booksdto);
	}
	
	
	@Override
	public ResponseEntity<List<GetBookDTO>> getAllBooks(int pageNumber) throws NoBooksAvailable{
		Page<Book> all = bookRepository.findAll(PageRequest.of(pageNumber, 6));
		
		if(all.isEmpty()) {
			throw new NoBooksAvailable("There is no books at all in library");
		}
		
		return ResponseEntity.status(HttpStatus.FOUND).body(all.stream().map(data-> GetBookDTO.builder()
				.author(data.getAuthor()).bookId(data.getBookId())
				.bookName(data.getBookName()).publishedBy(data.getPublishedBy())
				.publishedDate(data.getPublishedDate()).build()).collect(Collectors.toList()));
	}

	@Override
	public List<GetBookDTO> getAllBooksByName(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<RentBookDTO> getBook(String bookName) throws BookNotFoundException {
			Book byBookName = bookRepository.findByBookName(bookName);
			
			if(byBookName==null) {
				throw new BookNotFoundException("Book with the name"+ bookName+" is not present");
			}
			
			
			return ResponseEntity.status(HttpStatus.FOUND).body(RentBookDTO.builder()
					.bookId(byBookName.getBookId())
					.bookName(byBookName.getBookName())
					.author(byBookName.getAuthor())
					.publishedBy(byBookName.getPublishedBy())
					.publishedDate(byBookName.getPublishedDate())
					.bookDiscription(byBookName.getBookDiscription())
					.build());
			
	}
	
	@Transactional
	@Override 
	public ResponseEntity<ResponseStructure<RentBookDTO>> rentBook(int bookId,String email)
			throws CustomerNotFoundException, DuplicateBookException, BookOutOfStock {
		
		Customer byCustomerEmail =customerRepository.findByCustomerEmail(email);;
		if(byCustomerEmail==null){
			throw new CustomerNotFoundException("Not a valid email");
		}
		Book book = bookRepository.findById(bookId).get();
		
		
		
//		if(byCustomerEmail==null) {
//			throw new CustomerNotFoundException("Customer with the email: "+email+" does not exist");
//		}
		
		
		
		boolean anyMatch = byCustomerEmail.getOwnedBooks().stream().anyMatch(t -> t.getBookId().equals(bookId));
		if(anyMatch) {
			throw new DuplicateBookException("The Book you are trying to rent already exists in your list");
		}else {
			byCustomerEmail.getOwnedBooks().add(book);
			customerRepository.save(byCustomerEmail);
		}
		
		
		
		
		if(book.getQuantity()<=0) {
			throw new BookOutOfStock(book.getBookName()+" is not availabe currently");
		}else {
			book.setQuantity(book.getQuantity()-1);
			bookRepository.save(book);
		}
		
		RentBookDTO rentBookDTO = RentBookDTO.builder()
	            .bookId(book.getBookId())
	            .bookName(book.getBookName())
	            .author(book.getAuthor())
	            .publishedBy(book.getPublishedBy())
	            .publishedDate(book.getPublishedDate())
	            .bookDiscription(book.getBookDiscription())
	            .build();
		
		ResponseStructure<RentBookDTO> responseStructure = new ResponseStructure<RentBookDTO>()
	            .setError(false)
	            .setMessage("You rented the book successfully")
	            .setStatusCode(HttpStatus.OK.value())
	            .setData(rentBookDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
	}

}
