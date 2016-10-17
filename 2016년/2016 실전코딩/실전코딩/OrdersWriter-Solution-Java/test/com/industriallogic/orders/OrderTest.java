// ***************************************************************************
// Copyright (c) 2014, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.orders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	private Product product;
	private Order order;

	@Before 
	public void createProduct() {
		product = new Product("productID", "firstProduct", Color.white, 9.99F, 33);
		order = new Order("0");
	}
	
	@Test
	public void addProductIncrementsCount() {
		assertEquals(0, order.getProductCount());
		order.addProduct(product);
		assertEquals(1, order.getProductCount());
	}
	
	@Test
	public void addProductsRetainsProducts() {
		order.addProduct(product);
		Product secondProduct = new Product("productID", "secondProduct", Color.white, 9.99F, 33);
		order.addProduct(secondProduct);
		assertEquals("firstProduct", order.getProduct(0).getName());
		assertEquals("secondProduct", order.getProduct(1).getName());
	}
}
