package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.TokenUtil;

@Service
public class CartService implements ICartService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	TokenUtil tokenUtil;
	

	@Override
	public CartModel insertToCart(String token, CartDto cartdto) {
		
		System.out.println(token);
		int id=tokenUtil.decodeToken(token);
	
		Optional<BookModel> book = bookRepository.findById(cartdto.getBookId());
		Optional<UserModel> user= userRepository.findById(id);
			
			CartModel newCart = new CartModel(cartdto.getQuantity(), book.get(), user.get());
			cartRepository.save(newCart);
			return newCart;
		
		
	}
	
	

	@Override
	public List<CartModel> getAllCart(String token) {
		int id=tokenUtil.decodeToken(token);
		List<CartModel> cartModels=cartRepository.findCartItemsByUserId(id);
		return cartModels;
	}

	@Override
	public CartModel getCartById(int id) {
		CartModel cartModel=cartRepository.findById(id).get();
		if(cartModel!=null)
		{
			return cartModel;
		}
		
		return null;
	}

	/*@Override
    public void deleteCart(String userToken, Long book_id) {
        long user_id = util.decodeToken(userToken);
        repository.deleteCartItemByUserId(user_id, book_id);
        // CartData cart = repository.findById(bookId).get();
        // repository.delete(cart);
        
    }
 */
	@Override
	public String deleteCartById(int cartId) {
		
		//int user_id = tokenUtil.decodeToken(token);
        cartRepository.deleteById(cartId);;
		return "Book removed from cart";
	}

	@Override
	public CartModel updateBookById(int id, CartDto cartDto) {
		Optional<CartModel> cartModel=cartRepository.findById(id);
		Optional<BookModel> book=bookRepository.findById(cartDto.getBookId());
		Optional<UserModel> user=userRepository.findById(cartDto.getUserId());
		if(cartModel!=null)
		{
			CartModel updateCart = new CartModel(id ,cartDto.getQuantity(), book.get(), user.get());
			cartRepository.save(updateCart);
			return updateCart;
			
		}
		
		return null;
	}

	@Override
	public String updateQuantity(int id, int qty) {
		CartModel cart= cartRepository.findById(id).get();
		if(cart!=null)
		{
			cart.setQuantity(qty);
			cartRepository.save(cart);
			return "quantity modified";
		}
		
		return "cart not present";
	}

	@Override
	public CartModel insertToCart(int userId, int bookId, int quantity) {
		Optional<BookModel> book = bookRepository.findById(bookId);
		Optional<UserModel> user= userRepository.findById(userId);
		if (book.isPresent() || user.isPresent()) {
			CartModel newCart = new CartModel(quantity, book.get(), user.get());
			cartRepository.save(newCart);
			return newCart;
		}
			return null;
		
	}

	@Override
	public CartModel getBooksById(int userid) {
		CartModel cartModel=cartRepository.findByUserId(userid);
		if(cartModel!=null)
		{
			return cartModel;
		}
		
		return null;
	}
	
	

}
