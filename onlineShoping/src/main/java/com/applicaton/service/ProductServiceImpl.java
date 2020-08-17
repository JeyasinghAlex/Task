package com.applicaton.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.applicaton.dao.ProductDao;
import com.applicaton.dao.ProductDaoImpl;
import com.applicaton.model.Product;

public class ProductServiceImpl implements ProductService {

	private static ProductServiceImpl instance;

	private ProductServiceImpl() {

	}

	public static ProductServiceImpl getInstance() {
		if (instance == null) {
			instance = new ProductServiceImpl();
		}
		return instance;
	}

	@Override
	public boolean saveProduct(Product product) {
		ProductDao dao = ProductDaoImpl.getInstance();
		try {
			int isAdd = dao.add(product);
			if (isAdd != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<>();
		ProductDao dao = ProductDaoImpl.getInstance();
		try {
			ResultSet rs = dao.getAll();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setBrand(rs.getString(5));
				if (rs.getInt(6) <= 5) {
					product.setQuantity(rs.getInt(6));
				}
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> findAllProductsForAdmin() {
		List<Product> products = new ArrayList<>();
		ProductDao dao = ProductDaoImpl.getInstance();
		try {
			ResultSet rs = dao.getAllAdmin();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setBrand(rs.getString(5));
				product.setQuantity(rs.getInt(6));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product findProductById(int productId) {
		Product product = null;
		ProductDao dao = ProductDaoImpl.getInstance();
		try {
			ResultSet rs = dao.get(productId);
			if (rs.next()) {
				product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setBrand(rs.getString(5));
				product.setQuantity(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean deleteProduct(int productId) {
		ProductDao dao = ProductDaoImpl.getInstance();
		try {
			int isDeleted = dao.delete(productId);
			if (isDeleted != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product updateProduct(Product product) {
		ProductDao dao = ProductDaoImpl.getInstance();
		try {
			int isUpdate = dao.update(product);
			if (isUpdate != 0) {
				return findProductById(product.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product update(Product product) {
		ProductDao dao = ProductDaoImpl.getInstance();
		try {
			int isUpdate = dao.updateProduct(product);
			if (isUpdate != 0) {
				return findProductById(product.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
