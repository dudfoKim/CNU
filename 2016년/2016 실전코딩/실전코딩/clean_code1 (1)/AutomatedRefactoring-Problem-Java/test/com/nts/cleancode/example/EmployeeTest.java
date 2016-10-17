package com.nts.cleancode.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.nts.cleancode.example.Job;
import com.nts.cleancode.example.Manager;
import com.nts.cleancode.example.Programmer;

public class EmployeeTest {

	@Test
	public void noJobsPerformed() {
		Manager deadBeat = new Manager();
		assertEquals(0, deadBeat.jobsCompletedCount());
		assertEquals(0, deadBeat.jobsSkippedCount());
	}
	
	@Test
	public void threeJobsPerformedNoneSkippedByManager() {
		Manager productiveManager = new Manager();
		productiveManager.performJob(Job.MANAGE);
		productiveManager.performJob(Job.MARKET);
		productiveManager.performJob(Job.SELL);
		assertEquals(3, productiveManager.jobsCompletedCount());
		assertEquals(0, productiveManager.jobsSkippedCount());
	}
	
	@Test
	public void oneJobDoneOneJobSkippedByManager() {
		Manager unbusyManager = new Manager();
		unbusyManager.performJob(Job.TEST);
		unbusyManager.performJob(Job.SELL);
		assertEquals(1, unbusyManager.jobsCompletedCount());
		assertEquals(1, unbusyManager.jobsSkippedCount());
	}
	
	@Test
	public void noJobsDone() {
		Programmer deadBeat = new Programmer();
		assertEquals(0, deadBeat.jobsDoneCount());
		assertEquals(0, deadBeat.jobsSkippedCount());
	}
	
	@Test
	public void threeJobsPerformedNoneSkippedByProgrammer() {
		Programmer productiveProgrammer = new Programmer();
		productiveProgrammer.performJob(Job.TEST);
		productiveProgrammer.performJob(Job.PROGRAM);
		productiveProgrammer.performJob(Job.INTEGRATE);
		assertEquals(3, productiveProgrammer.jobsDoneCount());
		assertEquals(0, productiveProgrammer.jobsSkippedCount());
	}
	
	@Test
	public void oneJobDoneOneJobSkippedByProgrammer() {
		Programmer unbusyProgrammer = new Programmer();
		unbusyProgrammer.performJob(Job.TEST);
		unbusyProgrammer.performJob(Job.SELL);
		assertEquals(1, unbusyProgrammer.jobsDoneCount());
		assertEquals(1, unbusyProgrammer.jobsSkippedCount());
	}	
}
