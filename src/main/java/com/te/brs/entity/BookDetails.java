package com.te.brs.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BookDetails {
	
	private String bookName;
	private String author;
	private String publishedBy;
	private LocalDate publishedDate;
}
