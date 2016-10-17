package com.nts.tdd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductFinder {
	private List<Product> repository;
	
	public ProductFinder(List<Product> products) {
		repository = products;
	}

	public List<Product> byColor(Color colorOfProduct) {
		return findBy(new ColorSpec(colorOfProduct));
	}

	public List<Product> byPrice(double priceOfProduct) {
		return findBy(new PriceSpec(priceOfProduct));
	}

	protected List<Product> findBy(Spec spec) {
		List<Product> foundProducts = new ArrayList<Product>();
		Iterator<Product> products = repository.iterator();
		
		while (products.hasNext()) {
			Product product = (Product)products.next();
			if (spec.isSatisfiedBy(product))
				foundProducts.add(product);
		}
		
		return foundProducts;
	}
}
