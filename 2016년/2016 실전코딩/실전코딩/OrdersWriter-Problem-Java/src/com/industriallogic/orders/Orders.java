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

import java.util.List;
import java.util.ArrayList;

public class Orders {
	private List<Order> orders = new ArrayList<Order>();

	public void addOrder(Order order) {
		orders.add(order);
	}

	public int getOrderCount() {
		return orders.size();
	}

	public Order getOrder(int index) {
		return (Order)orders.get(index);
	}
}
