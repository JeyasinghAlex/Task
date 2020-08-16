package com.applicaton.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Product> products;

	public Cart() {
		this.products = new ArrayList<>();
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public void removeProduct(int id) {

	}

	public List<Product> getProducts() {
		return products;
	}
}