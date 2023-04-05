package com.bridgelabz.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class WishListDto {
	
	private int userId;
	private int bookId;

}
