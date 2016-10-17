package com.nts.cleancode.example;

import java.util.EnumSet;

public class Manager extends Employee {
	private int jobsCompleted = 0;
	private int jobsSkipped = 0;

	public int jobsCompletedCount() {
		return jobsCompleted;
	}
	
	public int jobsSkippedCount() {
		return jobsSkipped;
	}

	public void performJob(Job job) {
		if (responsibilities().contains(job))
			jobsCompleted++;
		else
			jobsSkipped++;
	}
	
	private EnumSet<Job> responsibilities() {
		return EnumSet.of(Job.MANAGE, Job.MARKET, Job.SELL);
	}
}
