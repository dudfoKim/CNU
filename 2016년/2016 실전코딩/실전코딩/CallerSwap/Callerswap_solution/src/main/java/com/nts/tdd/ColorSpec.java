package com.nts.tdd;

public class ColorSpec extends Spec {

	private Color color;

	public Color getColor() {
		return color;
	}

	protected boolean isSatisfiedBy(Product product) {
		return product.getColor().equals(getColor());
	}

	public ColorSpec(Color color) {
		this.color = color;
	}

}
