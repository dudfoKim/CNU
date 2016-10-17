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

public class BoxOffice extends TicketHolder {
	
	@Override
	protected Ticket findTicketFor(String concert) {
		return Ticket.soldOut();
	}

	@Override
	protected void jackUpPriceOf(Ticket ticket) {
		ticket.jackUpPriceBy(5.00);
	}
}
