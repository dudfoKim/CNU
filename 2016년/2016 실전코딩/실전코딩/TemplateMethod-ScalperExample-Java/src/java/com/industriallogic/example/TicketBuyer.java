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

public abstract class TicketBuyer extends TicketHolder {
	private TicketHolder connection;

	public TicketBuyer(TicketHolder connection) {
		this.connection = connection;
	}

	public void stockUp(String concert) {
		Ticket ticket = connection.scoreTicketFor(concert);
		if (ticket == Ticket.soldOut())
			return;
		addToStock(ticket);
	}

	@Override
	protected Ticket findTicketFor(String concert) {
		return connection.scoreTicketFor(concert);
	}
}