package com.nts.tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CallerSwap {

	private List<Product> products;
	private ProductFinder productFinder;

	@Before
	public void setUp() throws Exception {
		products = new ArrayList<Product>();
		products.add(new Product(new Color("White"), 2));
		products.add(new Product(new Color("Yellow"), 2));

		productFinder = new ProductFinder(products);
	}

	@Test
	public void priceIsSatisfy() {
		assertEquals(2, productFinder.byPrice(2).size());
	}

	@Test
	public void colorIsSatisfy() {
		assertEquals(1, productFinder.byColor(new Color("Yellow")).size());
	}
}
