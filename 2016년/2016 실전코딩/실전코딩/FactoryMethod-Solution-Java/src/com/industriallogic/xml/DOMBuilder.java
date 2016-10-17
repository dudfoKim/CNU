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

import java.io.IOException;
import java.io.StringWriter;
import java.util.Stack;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMBuilder extends AbstractBuilder {
	private Document doc;
	public DOMBuilder(String rootName) {
		init(rootName);
	}
	
	protected XmlNode createNode(String name) {
		return new ElementAdapter(doc.createElement(name));
	}
	
	public Document getDocument() {
		return doc;
	}
	
	protected void init(String rootName) {
		doc = new DocumentImpl();
		Element rootNode = doc.createElement(rootName);
		root = new ElementAdapter(rootNode);
		doc.appendChild(rootNode);
		current = root;
		parent = root;
		history = new Stack<XmlNode>();
		history.push(current);
	}
	
	public String toString() {
		OutputFormat format = new OutputFormat(doc);
		StringWriter stringOut = new StringWriter();
		XMLSerializer serial = new XMLSerializer(stringOut, format);
		try {
			serial.asDOMSerializer();
			serial.serialize(doc.getDocumentElement());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ioe.getMessage();
		}
		return stringOut.toString();
	}
}