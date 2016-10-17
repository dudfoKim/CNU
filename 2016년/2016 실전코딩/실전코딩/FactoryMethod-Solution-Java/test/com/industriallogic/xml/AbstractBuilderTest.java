/// ***************************************************************************
/// Copyright (c) 2008, Industrial Logic, Inc., All Rights Reserved.
///
/// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
/// used by students during Industrial Logic's workshops or by individuals
/// who are being coached by Industrial Logic on a project.
///
/// This code may NOT be copied or used for any other purpose without the prior
/// written consent of Industrial Logic, Inc.
/// ****************************************************************************

package com.industriallogic.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractBuilderTest {
	protected PrettyPrinter prettyPrint;
	protected OutputBuilder builder;
	protected abstract OutputBuilder createOutputBuilder(String rootName);
	
	@Before
	public void setUp() {
		prettyPrint = new PrettyPrinter();
	}
	
	@Test
	public void addAboveRoot() {
		builder = createOutputBuilder("orders");
		builder.addBelow("order");
		try {
			builder.addAbove("customer");
			fail("expecting java.lang.RuntimeException");
		} catch (RuntimeException re) {}
	}

	@Test
	public void addBelow() {
		String expected =
		"<orders>" +
			"<order>" +
			"</order>" +
		"</orders>";
		builder = createOutputBuilder("orders");
		builder.addBelow("order");
		assertXMLEquals("built xml", expected, builder.toString());
	}
		
	@Test
	public void addBelowWithAttribute() {
		String expected =
		"<orders>" +
			"<order number='123'>" +
			"</order>" +
		"</orders>";
		builder = createOutputBuilder("orders");
		builder.addBelow("order");
		builder.addAttribute("number", "123");
		assertXMLEquals("built xml", expected, builder.toString());
	}

	@Test
	public void addBesideAndAbove() {
		String expected =
		"<orders>" +
			"<order>" +
				"<item/>" +
				"<item/>" +
				"<item/>" +
			"</order>" +
			"<order/>" +
		"</orders>";
		builder = createOutputBuilder("orders");
		builder.addBelow("order");
		builder.addBelow("item");
		builder.addBeside("item");
		builder.addBeside("item");
		builder.addAbove("order");
		assertXMLEquals("beside & above", expected, builder.toString());
	}
	
	@Test
	public void addBesideRoot() {
		builder = createOutputBuilder("orders");
		try {
			builder.addBeside("customer");
			fail("expecting java.lang.RuntimeException");
		} catch (RuntimeException re) {}
	}
	
	@Test
	public void addValue() {
		String expected =
		"<orders>" +
			"<order>" +
			"123" +
			"</order>" +
		"</orders>";
		builder = createOutputBuilder("orders");
		builder.addBelow("order");
		builder.addValue("123");
		assertXMLEquals("built xml", expected, builder.toString());
	}
	
	@Test
	public void oneElementTree() {
		String expected =
		"<orders>" +
		"</orders>";
		builder = createOutputBuilder("orders");
		assertXMLEquals("one element tree", expected, builder.toString());
	}
	
	@Test
	public void startNewBuild() {
		String expected =
		"<fruits>" +
			"<apple>" +
			"</apple>" +
		"</fruits>";
		builder = createOutputBuilder("orders");
		builder.addBelow("order");
		builder.startNewBuild("fruits");
		builder.addBelow("apple");
		assertXMLEquals("start new build",expected,builder.toString());
	}

	@Test
	public void valueAndChild() {
		String expected =
		"<flavor>" +
			"this flavor is really good" +		
			"<grape>" +
			"</grape>" +
		"</flavor>";
		builder = createOutputBuilder("flavor");
		builder.addValue("this flavor is really good");
		builder.addBelow("grape");
		assertXMLEquals("value and child",expected,builder.toString());
	}

	public void assertXMLEquals(String testName, String expected, String result) {
		assertEquals(testName, prettyPrint.format(expected), prettyPrint.format(result));
	}
}