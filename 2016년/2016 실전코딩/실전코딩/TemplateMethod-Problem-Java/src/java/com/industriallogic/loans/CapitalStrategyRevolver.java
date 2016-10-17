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

public class CapitalStrategyRevolver extends CapitalStrategy {
	
	public double capital(Loan loan) {  
		return 
			  (loan.outstandingRiskAmount() * duration(loan) * riskFactorFor(loan))
			+ (loan.unusedRiskAmount() * duration(loan) * unusedRiskFactor(loan));
	}

	private double unusedRiskFactor(Loan loan) {
		return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
	}
}