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

import org.junit.Test;

import com.industriallogic.orders.TagNode;

public class TagNodeTest {
	
	@Test
	public void oneElementTree() {
		String expected =
		"<orders>" +
		"</orders>";
		TagNode orders = new TagNode("orders");
		assertEquals("orders xml", expected, orders.toString());
	}
	
	@Test
	public void treeWithAttributes() {
		String expected =
		"<orders>" +
			"<order number='123'>" +
			"</order>" +
		"</orders>";
		TagNode orders = new TagNode("orders");
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		orders.add(order);
		assertEquals("orders xml", expected, orders.toString());
	}
	
	@Test
	public void treeWithValues() {
		String expected =
		"<orders>" +
			"<order number='123'>" +
			"carDoor" + 
			"</order>" +
		"</orders>";
		TagNode orders = new TagNode("orders");
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		order.addValue("carDoor");
		orders.add(order);
		assertEquals("orders xml", expected, orders.toString());
	}
	
	@Test
	public void obtainValue() {
		TagNode root = new TagNode("root");
		String EXPECTED_VALUE = "a value";
		root.addValue(EXPECTED_VALUE);
		assertEquals(EXPECTED_VALUE, root.getValue());
	}
}
