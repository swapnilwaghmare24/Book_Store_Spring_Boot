package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.UserModel;


public interface IUserService {

	String registerUser(UserDto userDto);

	List<UserModel> getAllUsers();

	UserModel getUserById(int id);

	UserModel getUserByEmail(String email);

	UserModel updateByEmail(String email, UserDto userDto);

	UserModel getUserByToken(String token);

	String deleteUserById(int id);

	String deleteUserByToken(String token);

	String changePassword(String oldp, String newp);

	String forgotPassword(String email, String newp);

	String login(LoginDto loginDto);

	String adminLogin(LoginDto loginDto);

	String login(String email, String password);

	Object getIdByToken(String token);

	

	

}
