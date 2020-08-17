package com.applicaton.service;

import com.applicaton.model.Cart;
import com.applicaton.model.Product;

public interface CartService {
	
	Cart getCart();
	
	boolean saveCart(Product product);

	boolean updateCart(Product product);

	boolean removeCartProduct(Product product);
}
