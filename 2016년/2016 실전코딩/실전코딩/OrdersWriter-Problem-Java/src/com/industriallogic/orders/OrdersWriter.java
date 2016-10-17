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

public class OrdersWriter {
	private Orders orders;
	
	public OrdersWriter(Orders orders) {
		this.orders = orders;
	}

	public String getContents() {
		StringBuffer contents = new StringBuffer();
		contents.append("<orders>");
		for (int i = 0; i < orders.getOrderCount(); i++) {
			Order order = orders.getOrder(i);
			contents.append("<order");
			contents.append(" id='");
			contents.append(order.getOrderId());
			contents.append("'>");
			for (int j=0; j < order.getProductCount(); j++) {
				Product product = order.getProduct(j);
				contents.append("<product");
				contents.append(" id='");
				contents.append(product.getID());
				contents.append("'");
				contents.append(" color='");
				contents.append(getColorFor(product));
				contents.append("'");
				if (product.getSize() != ProductSize.NOT_APPLICABLE) {
					contents.append(" size='");
					contents.append(getSizeFor(product));
					contents.append("'");
				}
				contents.append(">");
				contents.append("<price");
				contents.append(" currency='");
				contents.append(getCurrencyFor(product));
				contents.append("'>");
				contents.append(product.getPrice());
				contents.append("</price>");
				contents.append(product.getName());
				contents.append("</product>");
			}
			contents.append("</order>");
		}
		contents.append("</orders>");
		return contents.toString();
	}

    private String getCurrencyFor(Product product) {
        return "USD";
    }

    private String getColorFor(Product product) {
		if (product.getColor().equals(Color.red))
			return "red";
		if (product.getColor().equals(Color.pink))
			return "pink";
		if (product.getColor().equals(Color.white))
			return "white";
		if (product.getColor().equals(Color.yellow))
			return "yellow";
		return "";
	}

	private String getSizeFor(Product product) {
		if (product.getSize() == ProductSize.SMALL)
			return "small";
		if (product.getSize() == ProductSize.MEDIUM)
			return "medium";
		if (product.getSize() == ProductSize.LARGE)
			return "large";
		return "";
	}
}
