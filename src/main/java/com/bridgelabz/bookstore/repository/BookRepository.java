package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Integer> {

	
	//List<Book> findByBookNameContaining(String bookName);
	@Query(value = "select * from book_model where book_Name LIKE %:bname%",nativeQuery = true)
	BookModel findBookByName(String bname);
	

	@Query(value = "select * from book_model order by price", nativeQuery = true)
	List<BookModel> booksInAsc();

	@Query(value = "select * from book_model order by price desc", nativeQuery = true)
	List<BookModel> booksInDesc();


	List<BookModel> findByBookNameContaining(String bname);

}
