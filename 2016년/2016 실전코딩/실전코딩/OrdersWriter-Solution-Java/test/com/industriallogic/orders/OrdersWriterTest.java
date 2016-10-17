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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrdersWriterTest {
	
	private Product fireTruck = 
		new Product("f1234", "Fire Truck", 
			Color.red, 8.95f, ProductSize.MEDIUM);
			
	private Product toyConvertible = 
		new Product("p1112", "Toy Porshe Convertible", 
			Color.pink, 230.00f, ProductSize.NOT_APPLICABLE);

	@Test
	public void noOrders() throws Exception {
		OrdersWriter writer = new OrdersWriter(new Orders());
	
		String expectedResult = 
		"<orders>" +
		"</orders>";
		
		assertEquals(expectedResult, writer.getContents());
	}
	
	@Test
	public void oneOrderNoProduct() {
		Orders orders = new Orders();
		String orderId = "321";
		Order order = new Order(orderId);
		orders.addOrder(order);
		
		OrdersWriter writer = new OrdersWriter(orders);
	
		String expectedResult = 
		"<orders>" +
			"<order id='321'>" +
			"</order>" +
		"</orders>";
		
		assertEquals(expectedResult, writer.getContents());
	}

	@Test
	public void oneOrderOneProduct() {
		Orders orders = new Orders();
		String orderId = "321";
		Order order = new Order(orderId);
		order.addProduct(fireTruck);
		orders.addOrder(order);
		
		assertEquals(orders.getOrderCount(), 1);
		assertEquals(order.getProductCount(), 1);
		
		OrdersWriter writer = new OrdersWriter(orders);
	
		String expectedResult = 
		"<orders>" +
			"<order id='321'>" +
				"<product id='f1234' color='red' size='medium'>" +
					"<price currency='USD'>" +
						"8.95" +
					"</price>" +
					"Fire Truck" + 
				"</product>" +
			"</order>" +
		"</orders>";
		
		assertEquals(expectedResult, writer.getContents());
	}

	@Test
	public void oneOrderTwoProducts() {
		Orders orders = new Orders();
		String orderId = "321";
		Order order = new Order(orderId);
		order.addProduct(fireTruck);
		order.addProduct(toyConvertible);
		orders.addOrder(order);
		
		assertEquals(orders.getOrderCount(), 1);
		assertEquals(order.getProductCount(), 2);
		
		OrdersWriter writer = new OrdersWriter(orders);
	
		String expectedResult = 
		"<orders>" +
			"<order id='321'>" +
				"<product id='f1234' color='red' size='medium'>" +
					"<price currency='USD'>" +
						"8.95" +
					"</price>" +
					"Fire Truck" + 
				"</product>" +
				"<product id='p1112' color='pink'>" +
					"<price currency='USD'>" +
						"230.0" +
					"</price>" +
					"Toy Porshe Convertible" + 
				"</product>" +
			"</order>" +
		"</orders>";
		
		assertEquals(expectedResult, writer.getContents());
	}
}
