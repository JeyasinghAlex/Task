package com.applicaton.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.applicaton.dao.CartDao;
import com.applicaton.dao.CartDaoImpl;
import com.applicaton.dao.ProductDao;
import com.applicaton.dao.ProductDaoImpl;
import com.applicaton.model.Cart;
import com.applicaton.model.Product;

public class CartServiceImpl implements CartService {

	private static CartServiceImpl instance;

	private CartServiceImpl() {

	}

	public static CartServiceImpl getInstance() {
		if (instance == null) {
			instance = new CartServiceImpl();
		}
		return instance;
	}

	@Override
	public boolean saveCart(int userId, Product product) {
		CartDao dao = CartDaoImpl.getInstance();
		ProductService service = ProductServiceImpl.getInstance();
		Product product1 = service.findProductById(product.getId());
		try {
			int isAdd = dao.add(userId, product);
			if (isAdd != 0) {
				int quantity = product1.getQuantity() - product.getQuantity();
				System.out.println("------------  "+quantity);
				product1.setQuantity(quantity);
				service.updateProduct(product1);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Cart getCart(int userId) {
		Cart cart = new Cart();
		try {
			ResultSet rs = CartDaoImpl.getInstance().getAll(userId);
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setBrand(rs.getString(3));
				product.setQuantity(rs.getInt(5));
				product.setPrice(rs.getInt(4) * rs.getInt(5));
				cart.addProduct(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}

	@Override
	public boolean updateCart(int userId, Product product) {
		System.out.println("I am updated cart");
		int cartQuantity = 0;
		CartDao cart = CartDaoImpl.getInstance();
		try {
			System.out.println(product.getId() + " " + product.getQuantity());
			ResultSet rs = cart.get(userId, product.getId());
			if (rs.next()) {
				cartQuantity = rs.getInt(3);
			}
			int quan = product.getQuantity() - cartQuantity;
			ProductService service = ProductServiceImpl.getInstance();
			Product product1 = service.findProductById(product.getId());
			if (quan > product1.getQuantity()) {
				return false;
			}
			if (product1.getQuantity() >= quan) {
				int newQuantity = product1.getQuantity() - quan;
				product1.setQuantity(newQuantity);
				cart.update(userId, product);
				ProductDaoImpl.getInstance().update(product1);
				return true;
			} else {
				quan = cartQuantity - product1.getQuantity();
				int newQuantity = product1.getQuantity() + quan;
				product1.setQuantity(newQuantity);
				cart.update(userId, product);
				ProductDaoImpl.getInstance().update(product1);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeCartProduct(int userId, Product product) {
		CartDao dao = CartDaoImpl.getInstance();
		ProductDao productDao = ProductDaoImpl.getInstance();
		try {
			ResultSet rs  = dao.get(userId, product.getId());
			if (rs.next()) {
				int quantity = rs.getInt(3);
				product.setQuantity(quantity);
			}
			int isUpdate = productDao.updateProduct(product);
			int isDeleted = dao.delete(userId, product);
			if (isDeleted != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
