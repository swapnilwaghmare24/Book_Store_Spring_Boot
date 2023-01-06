package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookModel;

import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.util.TokenUtil;

@Service
public class BookService implements IBookService {
	
	@Autowired
	BookRepository repo;
	@Autowired
	TokenUtil tokenUtil;

	@Override
	public String insertBook(BookDto bookDto) {
		BookModel bookModel = new BookModel(bookDto);
		repo.save(bookModel);
		String token= tokenUtil.createToken(bookModel.getBookId());
		return token;
	}

	@Override
	public List<BookModel> getAllBooks() {
		return repo.findAll();
	}

	@Override
	public BookModel getBookById(int id) {
		return repo.findById(id)
                .orElseThrow(() -> new BookStoreException("Invalid ID"));
	}

	@Override
	public BookModel findBookByName(String bname) {
		return repo.findBookByName(bname);
	}

	@Override
	public BookModel updateBookById(int id, BookDto bookDto) {
		BookModel newBookModel=repo.findById(id).get();
		
		if(newBookModel==null)
		return null;
		
		newBookModel.updateBookModel(bookDto);
		return repo.save(newBookModel);
		
	}

	@Override
	public String deleteBookByToken(String token) {
		int id=tokenUtil.decodeToken(token);
		Optional<BookModel> bookModel=repo.findById(id);
		if(bookModel!=null)
		{
			repo.deleteById(id);
			return "Book deleted";
		}
		
		return "Book not present so not deleted";
	}

	@Override
	public String updateQuantity(String bname, int qty) {
		BookModel bookModel=repo.findBookByName(bname);
		if(bookModel!=null)
		{
			bookModel.setQuantity(qty);
			repo.save(bookModel);
			return "Book quantity updated successfully";
		
		}
		return "Invalid book name";
	}

	@Override
	public List<BookModel> getBookInAscending() {
		
		List<BookModel> sorted = repo.booksInAsc();
		return sorted;
	}

	@Override
	public List<BookModel> getBookInDescending() {
		List<BookModel> sorted = repo.booksInDesc();
		return sorted;
	}

	
}
