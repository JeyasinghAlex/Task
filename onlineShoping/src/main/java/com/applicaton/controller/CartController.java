package com.applicaton.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.applicaton.model.Cart;
import com.applicaton.model.Product;
import com.applicaton.model.Secured;
import com.applicaton.model.User;
import com.applicaton.service.CartService;
import com.applicaton.service.CartServiceImpl;
import com.applicaton.service.ProductService;
import com.applicaton.service.ProductServiceImpl;
import com.applicaton.service.UserService;
import com.applicaton.service.UserServiceImpl;
import com.applicaton.util.RestError;
import com.applicaton.util.RestSuccess;

@Secured
@Path("/v1/user")
public class CartController {

	@GET
	@Path("/products")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		ProductService service = ProductServiceImpl.getInstance();
		List<Product> products = service.findAllProducts();
		if (products.isEmpty()) {
			return RestError.errorResponse("status", "No Product Found", 200);
		}
		return RestSuccess.successResponse(products);
	}

	@GET
	@Path("/{id}/products")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response showCart(@PathParam("id") int userId) {
		CartService service = CartServiceImpl.getInstance();
		Cart cart = service.getCart(userId);
		if (cart.getProducts().isEmpty()) {
			return RestError.errorResponse("status", "No Product Found", 200);
		}
		return RestSuccess.successResponse(cart.getProducts());
	}

	@POST
	@Path("/{id}/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCart(@PathParam("id") int userId, Product product) {
		CartService service = CartServiceImpl.getInstance();
		Product product1 = ProductServiceImpl.getInstance().findProductById(product.getId());
		System.out.println(product1.getQuantity() + " " + product.getQuantity());
		if (product.getQuantity() > product1.getQuantity()) {
			return RestError.errorResponse("status", "Product is insufficient", 200);
		}
		boolean isAdded = service.saveCart(userId, product);
		if (!isAdded) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@PUT
	@Path("/{id}/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCart(@PathParam("id") int userId, Product product) {
		System.out.println("i am update");
		CartService service = CartServiceImpl.getInstance();
		boolean isUpdated = service.updateCart(userId, product);
		if (!isUpdated) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@DELETE
	@Path("/{id}/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCart(@PathParam("id") int userId, Product product) {
		CartService service = CartServiceImpl.getInstance();
		boolean isDeleted = service.removeCartProduct(userId, product);
		if (!isDeleted) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}
}