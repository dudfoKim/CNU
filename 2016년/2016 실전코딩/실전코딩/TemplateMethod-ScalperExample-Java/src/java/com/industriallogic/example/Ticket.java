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

package com.industriallogic.example;

public class Ticket {
	private double faceValue;
	private double streetPrice;
	private String concert;

	private static Ticket soldOut = new Ticket("Sold Out", 0.00);

	public static Ticket soldOut() {
		return soldOut;
	}

	public Ticket(String concert, double faceValue) {
		this.concert = concert;
		this.faceValue = faceValue;
		this.streetPrice = faceValue;
	}

	public double getStreetPrice() {
		return streetPrice;
	}

	public String getConcert() {
		return concert;
	}

	public void jackUpPriceBy(double amount) {
		if (this != Ticket.soldOut())
			streetPrice += amount;
	}

	public double getFaceValue() {
		return faceValue;
	}
}
