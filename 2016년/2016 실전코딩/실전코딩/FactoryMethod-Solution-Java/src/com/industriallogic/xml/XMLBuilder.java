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

import java.util.*;

public class XMLBuilder extends AbstractBuilder {
	public XMLBuilder(String rootName) {
		init(rootName);
	}
	
	@Override
	protected XmlNode createNode(String name) {
		return new TagNode(name);
	}
	
	@Override
	protected void init(String rootName) {
		root = createNode(rootName);
		current = root;
		parent = root;
		history = new Stack<XmlNode>();
		history.push(current);
	}
	
	public String toString() {
		return root.toString();
	}
}