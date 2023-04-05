package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.WishListDto;
import com.bridgelabz.bookstore.model.WishListModel;

public interface IWishListService {

	WishListModel addToWishlist(WishListDto wishlistdto);

	List<WishListModel> getAllWishlists();

	WishListModel deleteWishlistRecordById(int id);

}
