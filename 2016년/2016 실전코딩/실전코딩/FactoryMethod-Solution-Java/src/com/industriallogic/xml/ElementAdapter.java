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

import org.w3c.dom.*;

public class ElementAdapter implements XmlNode {
	private Element element;
	
	public ElementAdapter(Element element) {
		this.element = element;	
	}
	
	private Element getElement() {
		return element;
	}

	public void add(XmlNode childNode) {
		element.appendChild(((ElementAdapter)childNode).getElement());
	}

	public void addValue(String textNode) {
		element.appendChild(
			element.getOwnerDocument().createTextNode(textNode));
	}
	
	public void addAttribute(String attribute, String value) {
		element.setAttribute(attribute, value);
	}
}
