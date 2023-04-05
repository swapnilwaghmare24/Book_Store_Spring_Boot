package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class WishListModel {
	
	@Id
	@GeneratedValue
	private int wishListId;	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookModel book;

	    


	    public WishListModel(UserModel user, BookModel book) {
	       
	        this.user = user;
	        this.book = book;
	    }


}
