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
		List<Product> foundProducts = new ArrayList<Product>();
		Iterator<Product> products = repository.iterator();
		
		while (products.hasNext()) {
			Product product = (Product)products.next();
			if (product.getColor().equals(colorOfProduct))
				foundProducts.add(product);
		}
		
		return foundProducts;
	}

	public List<Product> byPrice(double priceOfProduct) {
		List<Product> foundProducts = new ArrayList<Product>();
		Iterator<Product> products = repository.iterator();
		
		while (products.hasNext()) {
			Product product = (Product)products.next();
			if (product.getPrice() == priceOfProduct)
				foundProducts.add(product);
		}
		
		return foundProducts;
	}
}
