package com.nts.cleancode.example;

import java.util.EnumSet;

public class Programmer extends Employee {
	private int jobsDone = 0;
	private int jobsSkipped = 0;

	public int jobsDoneCount() {
		return jobsDone;
	}
	
	public int jobsSkippedCount() {
		return jobsSkipped;
	}

	public void performJob(Job job) {
		EnumSet<Job> acceptableWork = EnumSet.of(Job.TEST, Job.PROGRAM, Job.INTEGRATE, Job.DESIGN);
		if (acceptableWork.contains(job))
			jobsDone++;
		else
			jobsSkipped++;
	}
}
