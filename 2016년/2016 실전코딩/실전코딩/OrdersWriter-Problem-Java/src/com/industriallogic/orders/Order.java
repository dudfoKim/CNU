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

import com.industriallogic.orders.Product;

public class Order {
	private String id;
	private List<Product> products = new ArrayList<Product>();

	public Order(String orderId) {
		id = orderId;
	}

	public void addProduct(Product productToAdd) {
		products.add(productToAdd);
	}

	public Object getOrderId() {
		return id;
	}

	public int getProductCount() {
		return products.size();
	}

	public Product getProduct(int index) {
		return (Product)products.get(index);
	}
}
