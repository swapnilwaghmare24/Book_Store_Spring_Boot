package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ResponseDto;
import com.bridgelabz.bookstore.dto.WishListDto;
import com.bridgelabz.bookstore.model.WishListModel;
import com.bridgelabz.bookstore.service.IWishListService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/wishlist")
@RestController 
public class WishListController {
	
	 	@Autowired
	    private IWishListService wishlistService;

	    @PostMapping("/save")
	    public ResponseEntity<ResponseDto> addToWishlist(@RequestBody WishListDto wishlistdto){
	        WishListModel wishlist = wishlistService.addToWishlist(wishlistdto);
	        ResponseDto dto = new ResponseDto("Wishlist added successfully",wishlist);
	        return new ResponseEntity<>(dto, HttpStatus.OK);
	    }
	    @GetMapping("/retrieveAll")
	    public ResponseEntity<ResponseDto> getAllWishlists(){
	        List<WishListModel> wishlist = wishlistService.getAllWishlists();
	        ResponseDto dto = new ResponseDto("All Wishlist records retrieved successfully",wishlist);
	        return new ResponseEntity<>(dto,HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<ResponseDto> deleteWishlistRecordById(@PathVariable int id){
	        WishListModel wishlist = wishlistService.deleteWishlistRecordById(id);
	        ResponseDto dto = new ResponseDto("Wishlist record deleted successfully for given id",wishlist);
	        return new ResponseEntity<>(dto,HttpStatus.OK);
	    }

}
