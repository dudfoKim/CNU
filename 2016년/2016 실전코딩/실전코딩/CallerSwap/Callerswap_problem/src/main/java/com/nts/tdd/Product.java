package com.nts.tdd;

public class Product {

	private Color color;
	private double price;

	public Product(Color color, double price) {
		this.color = color;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public Color getColor() {
		return color;
	}
}
