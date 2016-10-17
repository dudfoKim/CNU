package com.nts.tdd;

public abstract class Spec {

	public Spec() {
		super();
	}

	protected abstract boolean isSatisfiedBy(Product product);

}