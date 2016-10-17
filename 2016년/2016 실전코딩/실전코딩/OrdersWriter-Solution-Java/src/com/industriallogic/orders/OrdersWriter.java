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
		TagNode ordersTag = new TagNode("orders");
		writeOrdersTo(ordersTag);
		return ordersTag.toString();
	}

	private void writeOrdersTo(TagNode ordersTag) {
		for (int i = 0; i < orders.getOrderCount(); i++) {
			Order order = orders.getOrder(i);
			TagNode orderTag = new TagNode("order");
			orderTag.addAttribute("id", order.getOrderId().toString());
			for (int j = 0; j < order.getProductCount(); j++) {
				Product product = order.getProduct(j);
				writeProductTo(product, orderTag);
			}
			ordersTag.add(orderTag);
		}
	}

	private void writeProductTo(Product product, TagNode orderTag) {
		TagNode productTag = new TagNode("product");
		productTag.addAttribute("id", product.getID());
		productTag.addAttribute("color", getColorFor(product));
		if (product.getSize() != ProductSize.NOT_APPLICABLE)
			productTag.addAttribute("size", getSizeFor(product));
		writePriceTo(product, productTag);
		productTag.addValue(product.getName());
		orderTag.add(productTag);
	}

	private void writePriceTo(Product product, TagNode productTag) {
		TagNode priceTag = new TagNode("price");
		priceTag.addAttribute("currency", getCurrencyFor(product));
		priceTag.addValue(product.getPrice() + "");
		productTag.add(priceTag);
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
