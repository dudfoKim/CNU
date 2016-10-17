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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class ScoreTicketTest {
  private static final int shorelineAmphitheatreCapactity = 22000;
  private static final int o2ArenaCapactity = 20000;
  private static final int levitraCentreCapactity = 45000;
  private static final String ledZeppelinConcert = "Led Zeppelin - O2 Arena - 11/12/2008";
  private static final String eaglesConcert = "The Eagles - Levitra Centre - 12/31/2008";
  private static final String celineDionConcert = "Celine Dion - Shoreline Amphitheatre - 09/24/2008";

  private BoxOffice boxOffice;
  private TicketBuyer chris;
  private TicketBuyer joshua;
  private TicketBuyer mike;
  private TicketBuyer kim;

  @Before
  public void setUp() {
    createAndConnectBuyersAndSellers();
    stockTheBoxOffice();
    makeSureChrisHasSomeTicketsInStock();
  }

  private void createAndConnectBuyersAndSellers() {
    boxOffice = new BoxOffice();
    chris = new Scalper(boxOffice);
    joshua = new Scalper(chris);
    mike = new Scalper(joshua);
    kim = new Fan(mike);
  }

  private void stockTheBoxOffice() {
    for (int i = 0; i < shorelineAmphitheatreCapactity; i++)
      boxOffice.addToStock(new Ticket(celineDionConcert, 100.00));
  }

  private void makeSureChrisHasSomeTicketsInStock() {
    chris.stockUp(celineDionConcert);
  }

  @Test
  public void scoreTickets() {
    Ticket ticket = kim.scoreTicketFor(celineDionConcert);
    assertEquals(celineDionConcert, ticket.getConcert());
    assertEquals(100.00, ticket.getFaceValue());
    assertEquals(165.00, ticket.getStreetPrice());
  }


  @Test
  public void buyFromTheBoxOffice() {
    Ticket ticket = boxOffice.scoreTicketFor(celineDionConcert);
    assertEquals(celineDionConcert, ticket.getConcert());
    assertEquals(100.00, ticket.getFaceValue());
    assertEquals(105.00, ticket.getStreetPrice());
  }

  @Test
  public void testInventory() {
    Ticket ledZeppelinTicket = new Ticket(ledZeppelinConcert, 220.00);
    mike.addToStock(ledZeppelinTicket);
    Ticket ticket = mike.removeFromStock(ledZeppelinConcert);
    assertEquals(ledZeppelinConcert, ticket.getConcert());
    assertFalse(mike.hasTicketInStockFor(ledZeppelinConcert));
  }

  @Test
  public void testSoldOut() {
    Ticket ticket = mike.scoreTicketFor(eaglesConcert);
    assertSame(Ticket.soldOut(), ticket);
  }

  @Test
  public void testRemoval() {
    BoxOffice localDealer = new BoxOffice();
    localDealer.addToStock(new Ticket(celineDionConcert, 100.00));
    localDealer.addToStock(new Ticket(celineDionConcert, 100.00));
    Scalper keith = new Scalper(localDealer);
    keith.stockUp(celineDionConcert);
    Ticket ticket = keith.scoreTicketFor(celineDionConcert);
    assertEquals(celineDionConcert, ticket.getConcert());
    ticket = keith.scoreTicketFor(celineDionConcert);
    assertEquals(celineDionConcert, ticket.getConcert());
    ticket = keith.scoreTicketFor(celineDionConcert);
    assertSame(Ticket.soldOut(), ticket);
  }
}
