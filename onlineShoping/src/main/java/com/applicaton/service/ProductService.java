package com.applicaton.service;

import java.util.List;

import com.applicaton.model.Product;

public interface ProductService {

	boolean saveProduct(Product product);

	List<Product> findAllProducts();

	List<Product> findAllProductsForAdmin();

	Product findProductById(int id);

	boolean deleteProduct(int id);

	Product updateProduct(Product product);

	Product update(Product product);
}
