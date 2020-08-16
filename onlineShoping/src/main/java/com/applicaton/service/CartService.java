package com.applicaton.service;

import com.applicaton.model.Cart;
import com.applicaton.model.Product;

public interface CartService {
	
	Cart getCart(int studentId);
	
	boolean saveCart(int userId, Product product);

	boolean updateCart(int userId, Product product);

	boolean removeCartProduct(int userId, Product product);
}
