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

import com.applicaton.model.Product;
import com.applicaton.model.Secured;
import com.applicaton.service.ProductService;
import com.applicaton.service.ProductServiceImpl;
import com.applicaton.util.RestError;
import com.applicaton.util.RestSuccess;

@Secured
@Path("/v1/product")
public class ProductController {

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("id") int productId) {
		ProductService service = ProductServiceImpl.getInstance();
		Product product = service.findProductById(productId);
		if (product == null) {
			return RestError.errorResponse("status", "invalid productId - " + productId, 400);
		}
		return RestSuccess.successResponse(product);
	}

	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		ProductService service = ProductServiceImpl.getInstance();
		List<Product> products = service.findAllProductsForAdmin();
		if (products.isEmpty()) {
			return RestError.errorResponse("status", "No Records Found", 200);
		}
		return RestSuccess.successResponse(products);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) {
		ProductService service = ProductServiceImpl.getInstance();
		boolean isAdd = service.saveProduct(product);
		if (!isAdd) {
			return RestError.errorResponse();
		}
		return RestSuccess.successResponse();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("id") int productId, Product product) {
		product.setId(productId);
		ProductService service = ProductServiceImpl.getInstance();
		Product updatedProduct = service.update(product);
		if (updatedProduct == null) {
			return RestError.errorResponse("status", "invalid productId - " + productId, 400);
		}
		return RestSuccess.successResponse(updatedProduct);
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProduct(@PathParam("id") int productId) {
		ProductService service = ProductServiceImpl.getInstance();
		boolean isDelete = service.deleteProduct(productId);
		if (!isDelete) {
			return RestError.errorResponse("status", "invalid product - " + productId, 400);
		}
		return RestSuccess.successResponse();
	}
}