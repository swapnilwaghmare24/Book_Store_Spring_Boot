package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.WishListModel;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel, Integer> {

}
