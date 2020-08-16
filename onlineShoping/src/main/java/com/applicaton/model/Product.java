package com.applicaton.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	
	private int id;

	private String name;

	private String brand;

	private String description;

	private int price;

	private int quantity;


//	private int categoryId;
//
//	private int purchases;
	
	@JsonProperty("id")
	public int getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("brand")
	public String getBrand() {
		return brand;
	}

	@JsonProperty("brand")
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("price")
	public int getPrice() {
		return price;
	}

	@JsonProperty("price")
	public void setPrice(int price) {
		this.price = price;
	}

	@JsonProperty("quantity")
	public int getQuantity() {
		return quantity;
	}

	@JsonProperty("quantity")
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
