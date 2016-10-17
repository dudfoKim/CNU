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

import java.util.Stack;

public abstract class AbstractBuilder implements OutputBuilder {
	static final protected String CANNOT_ADD_ABOVE_ROOT = "Cannot add node above the root node.";
	static final protected String CANNOT_ADD_BESIDE_ROOT = "Cannot add node beside the root node.";

	protected abstract void init(String rootName);

	protected abstract XmlNode createNode(String name);

	protected Stack<XmlNode> history = new Stack<XmlNode>();
	protected XmlNode root;
	protected XmlNode parent;
	protected XmlNode current;

	public void addAbove(String uncle) {
		if (current == root)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		boolean atRootNode = (history.size() == 1);
		if (atRootNode)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		current = history.peek();
		addBelow(uncle);
	}

	public void addAttribute(String name, String value) {
		current.addAttribute(name, value);
	}

	public void addBelow(String child) {
		XmlNode childNode = createNode(child);
		current.add(childNode);
		parent = current;
		current = childNode;
		history.push(current);
	}

	public void addBeside(String sibling) {
		if (current == root)
			throw new RuntimeException(CANNOT_ADD_BESIDE_ROOT);
		XmlNode siblingNode = createNode(sibling);
		parent.add(siblingNode);
		current = siblingNode;
		history.pop();
		history.push(current);
	}

	public void addValue(String value) {
		current.addValue(value);
	}

	public void startNewBuild(String rootName) {
		init(rootName);
	}
}