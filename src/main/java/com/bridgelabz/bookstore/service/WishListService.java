package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.WishListDto;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.model.WishListModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.repository.WishListRepository;

@Service
public class WishListService implements IWishListService {
	
	@Autowired
	WishListRepository wishlistRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	BookRepository bookRepo;

	@Override
	public WishListModel addToWishlist(WishListDto wishlistdto) {
		 	Optional<UserModel> user = userRepo.findById(wishlistdto.getUserId());
	        Optional<BookModel> book = bookRepo.findById(wishlistdto.getBookId());
	        if(user.isPresent() && book.isPresent()) {
	            WishListModel newWishList = new WishListModel(user.get(),book.get());
	            wishlistRepo.save(newWishList);
	            return newWishList;
	        }
	        else {
	            return null;//book dosent exist
	        }
	}

	@Override
	public List<WishListModel> getAllWishlists() {
		 List<WishListModel> list = wishlistRepo.findAll();
	     return list;
	}

	@Override
	public WishListModel deleteWishlistRecordById(int id) {
		  Optional<WishListModel> list = wishlistRepo.findById(id);
	        wishlistRepo.deleteById(id);
	        return list.get();
	}

}
