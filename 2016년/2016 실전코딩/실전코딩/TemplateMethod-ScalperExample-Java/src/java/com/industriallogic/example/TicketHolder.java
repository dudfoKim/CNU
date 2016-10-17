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

import java.util.ArrayList;
import java.util.List;

public abstract class TicketHolder {
	private List<Ticket> stock = new ArrayList<Ticket>();

	public Ticket scoreTicketFor(String concert) {
		Ticket ticket;

		if (hasTicketInStockFor(concert))
			ticket = deliverTicketFor(concert);
		else
			ticket = findTicketFor(concert);

		jackUpPriceOf(ticket);
		return ticket;
	}

	protected boolean hasTicketInStockFor(String concert) {
		for (Ticket ticket : stock)
			if (ticket.getConcert().equals(concert))
				return true;
		return false;
	}

	private Ticket deliverTicketFor(String concert) {
		return removeFromStock(concert);
	}

	protected abstract Ticket findTicketFor(String concert);

	protected void jackUpPriceOf(Ticket ticket) {
	}

	public void addToStock(Ticket ticket) {
		stock.add(ticket);
	}

	public Ticket removeFromStock(String concert) {
		for (Ticket ticket : stock)
			if (ticket.getConcert().equals(concert)) {
				stock.remove(ticket);
				return ticket;
			}
		return null;
	}
}