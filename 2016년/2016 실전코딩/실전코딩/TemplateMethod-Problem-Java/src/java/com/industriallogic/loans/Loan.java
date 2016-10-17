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


//$CopyrightHeader()$

package com.industriallogic.loans;

import java.util.*;

public class Loan {
	private double commitment;
	private double outstanding;
	private Date start;
	private Date maturity;
	private Date expiry;
	private Date today;
	private int riskRating;
	private double unusedPercentage;
	private List payments = new ArrayList();
	private CapitalStrategy capitalStrategy;
	
	class Payment {
		private double amount;
		private Date date;
		
		Payment(double amount, Date date) {
			this.amount = amount;
			this.date = date;
		}
			
		double amount() {
			return amount;
		}
		
		Date date() {
			return date;
		}
	};

	private Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating, CapitalStrategy capitalStrategy) {
		this.commitment = commitment;
		this.outstanding = outstanding;
		this.start = start;
		this.expiry = expiry;
		this.maturity = maturity;
		this.riskRating = riskRating;
		this.unusedPercentage = 1.0;
		this.capitalStrategy =  capitalStrategy;
	}

	public static Loan newTermLoan(
		double commitment, Date start, Date maturity, int riskRating) {
		return new Loan(commitment, commitment, start, null, maturity, riskRating, new CapitalStrategyTermLoan());
	}

	public static Loan newRevolver(
		double commitment, Date start, Date expiry, int riskRating) {
		return new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyRevolver());
	}
	
	public static Loan newAdvisedLine(
		double commitment, Date start, Date expiry, int riskRating) {
		if (riskRating > 3) return null;
		Loan advisedLine = new Loan(commitment, 0, start, expiry, null, riskRating, new CapitalStrategyAdvisedLine());
		advisedLine.setUnusedPercentage(0.1);
		return advisedLine;
	}	

	public double capital() {
		return capitalStrategy.capital(this);
	}

	double outstandingRiskAmount() {
		return outstanding;
	}

	double unusedRiskAmount() {
	   return (commitment - outstanding);
	}

	public void setOutstanding(double newOutstanding) {
		this.outstanding = newOutstanding;
	}
	
	public double duration() {
		return capitalStrategy.duration(this);
	}
	
	public void payment(double paymentAmount, Date paymentDate) {
		Payment payment = new Payment(paymentAmount, paymentDate);
		outstanding = outstanding - payment.amount(); 
		payments.add(payment);
	}

	public double getUnusedPercentage() {
		return unusedPercentage;
	}

	public void setUnusedPercentage(double unusedPercentage) {
		this.unusedPercentage = unusedPercentage;
	}

	Date getExpiry() {
		return expiry;
	}

	Date getMaturity() {
		return maturity;
	}

	double getCommitment() {
		return commitment;
	}

	int getRiskRating() {
		return riskRating;
	}

	Date getStart() {
		return start;
	}

	Date getToday() {
		return today;
	}

	List getPayments() {
		return payments;
	}

}
