package com.te.brs.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
	private Integer bookId;
	@NotBlank(message = "enter proper book name")
	private String bookName;
	@NotBlank(message = "Pleas enter Author Name")
	private String author;
	private String bookDiscription;
	@NotBlank(message = "Pleas enter Publisher Name")
	private String publishedBy;
//	@NotBlank(message = "Pleas enter Publish Date")
	private LocalDate publishedDate;
	private Double ratePerHour;
	@Min(value = 1,message = "Add atleast One Book")
	private Integer quantity;
	private Boolean isAvailable;
}
