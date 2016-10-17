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

public class OrdersTest {
	private Orders orders;

	@Before
	public void createOrders() {
		orders = new Orders();
	}
	
	@Test
	public void addOrderIncrementsCount() {
		assertEquals(0, orders.getOrderCount());
		orders.addOrder(new Order("0"));
		assertEquals(1, orders.getOrderCount());
	}
	
	@Test
	public void ordersRetainedInOrder() {
		orders.addOrder(new Order("0"));
		orders.addOrder(new Order("1"));
		assertEquals("0", orders.getOrder(0).getOrderId());
		assertEquals("1", orders.getOrder(1).getOrderId());
	}
}
