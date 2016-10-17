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

import java.util.*;

public class TagNode  {
	private String name = "";
	private StringBuffer attributes;
	private List<TagNode> children;
	private String value = "";
	
	public TagNode(String name) {
		this.name = name;
		attributes = new StringBuffer("");
	}
	
	public void add(TagNode childNode) {
		children().add(childNode);
	}
	
	public void addAttribute(String attribute, String value) {
		attributes.append(" ");
		attributes.append(attribute);
		attributes.append("='");
		attributes.append(value);
		attributes.append("'");
	}
	
	public void addValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public List<TagNode> children() {
		if (children == null)
			children = new ArrayList<TagNode>();
		return children;	
	}
	
	public String toString() {
		return toStringHelper(new StringBuffer(""));
	}
	
	protected String toStringHelper(StringBuffer result) {
		writeOpenTagTo(result);
		writeChildrenTo(result);
		writeValueTo(result);
		writeEndTagTo(result);
		return result.toString();
	}
	
	protected void writeChildrenTo(StringBuffer result) {
		for (TagNode node : children()) 
			node.toStringHelper(result);
	}
	
	private void writeValueTo(StringBuffer result) {
		if (!value.equals(""))
			result.append(value);
	}
	
	protected void writeEndTagTo(StringBuffer result) {
		result.append("</");
		result.append(name);
		result.append(">");
	}
	
	protected void writeOpenTagTo(StringBuffer result) {
		result.append("<");
		result.append(name);
		result.append(attributes.toString());
		result.append(">");
	}
}
