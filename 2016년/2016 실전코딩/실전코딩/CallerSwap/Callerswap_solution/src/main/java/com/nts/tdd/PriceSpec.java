package com.nts.tdd;

public class PriceSpec extends Spec {

	private double price;

	public PriceSpec(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	@Override
	protected boolean isSatisfiedBy(Product product) {
		return product.getPrice() == getPrice();
	}

}
