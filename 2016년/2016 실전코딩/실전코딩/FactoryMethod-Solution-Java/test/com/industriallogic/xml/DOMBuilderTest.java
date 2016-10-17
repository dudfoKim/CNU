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

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.*;

public class DOMBuilderTest extends AbstractBuilderTest {
	protected OutputBuilder createOutputBuilder(String rootName) {
		return new DOMBuilder(rootName);
	}
	
	@Test
	public void document() {
		String expectedRootName = "orders";
		DOMBuilder builder = new DOMBuilder(expectedRootName);
		builder.addBelow("order");
		builder.addAttribute("num", "123");
			builder.addBelow("item");
			builder.addValue("bird feeder");
		Document doc = builder.getDocument();
		Node root = doc.getFirstChild();
		String rootName = root.getNodeName();
		assertEquals("root name", expectedRootName, rootName);
		String firstChildName = root.getFirstChild().getNodeName();
		assertEquals("first child", "order", firstChildName);
	}	
}
