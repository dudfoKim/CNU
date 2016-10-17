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


public abstract class CapitalStrategy {
	private static final int MILLIS_PER_DAY = 86400000;
	private static final int DAYS_PER_YEAR = 365;
		
	public abstract double capital(Loan loan);

	protected double riskFactorFor(Loan loan) {
		return RiskFactor.getFactors().forRating(loan.getRiskRating());
	}

	protected double yearsTo(Date endDate, Loan loan) {
		Date beginDate = (loan.getToday() == null ? loan.getStart(): loan.getToday());
		return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR; 
	}

	public double duration(Loan loan) {
		return yearsTo(loan.getExpiry(), loan);
	}

}
