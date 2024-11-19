package com.te.brs.bookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.te.brs.entity.Book;
import com.te.brs.entity.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.RequiredArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    private Book b;

    @AfterEach
    void tearDown(){
        bookRepository.deleteAll();
        b=null;
    }

    @BeforeEach
    void setup(){
        ArrayList<Customer> arrayList = new ArrayList<Customer>();
        arrayList.add(Customer.builder()
        .customerEmail("DUMMY EMAIL")
        .customerName("DUMMY NAME")
        .build());

        b=Book.builder()
        .author("Dummy Author")
        .bookName("DUMMY BOOK")
        .bookDiscription("SOME DUMMY DATA DISCRIPTION")
        .publishedBy("DUMMY PUBLISHER")
        .rentPerHour(0.0d)
        .publishedDate(LocalDate.now())
        .quantity(10)
        .customers(arrayList)
        .build();
    }

    @Test
    void testingFindByBookNameRepoMethod(){
        bookRepository.save(b);

        Book byBookName = bookRepository.findByBookName(b.getBookName());
        assertThat(byBookName).isEqualTo(b);
    }

    @Test
    void testingExistByBookNameRepoMethod(){
        bookRepository.save(b);

        assertThat(bookRepository.existsByBookName(b.getBookName())).isTrue();
    }
}
