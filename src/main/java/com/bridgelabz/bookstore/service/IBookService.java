package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.BookModel;


public interface IBookService {

	String insertBook(BookDto bookDto);

	List<BookModel> getAllBooks();

	BookModel getBookById(int id);

	List<BookModel> findBookByName(String bname);

	BookModel updateBookById(int id, BookDto bookDto);

	String deleteBookByToken(String token);

	String updateQuantity(String bname, int qty);

	List<BookModel> getBookInAscending();

	List<BookModel> getBookInDescending();

	

}
