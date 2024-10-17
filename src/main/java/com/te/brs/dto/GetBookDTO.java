package com.te.brs.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBookDTO {
	private Integer bookId;
	private String bookName;
	private String author;
	private String publishedBy;
	private LocalDate publishedDate;
}
