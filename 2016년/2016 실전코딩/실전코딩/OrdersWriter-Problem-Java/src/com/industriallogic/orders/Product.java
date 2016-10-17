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

public class Product {
	private String id, name;
	private Color color;
	private float price;
	private int size;
	
	public Product(String id, String name, Color color, float price, int size) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.price = price;
		this.size = size;
	}
	
	public String getID() {
		return id;
	}
	
	public Color getColor() {
		return color;
	}

	public float getPrice() {
		return price;
	}

	public int getSize() {
		return size;
	}

	public Object getCategory() {
		return "toys";
	}

	public String getName() {
		return name;
	}
}
