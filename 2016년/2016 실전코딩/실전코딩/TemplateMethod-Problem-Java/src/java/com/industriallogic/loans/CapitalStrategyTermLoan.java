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

public class CapitalStrategyTermLoan extends CapitalStrategy {
	
	public double capital(Loan loan) {
		return loan.getCommitment() * duration(loan) * riskFactorFor(loan);
	}

	public double duration(Loan loan) {
		return weightedAverageDuration(loan);
	}

	private double weightedAverageDuration(Loan loan) {
		double duration = 0.0;
		double weightedAverage = 0.0;
		double sumOfPayments = 0.0;
		Iterator loanPayments = loan.getPayments().iterator();
		while (loanPayments.hasNext()) {
			Loan.Payment payment = (Loan.Payment)loanPayments.next();
			sumOfPayments += payment.amount();
			weightedAverage += yearsTo(payment.date(), loan) * payment.amount();
		}
		if (loan.getCommitment() != 0.0)
			duration = weightedAverage / sumOfPayments;
		return duration;
	}
	
}
