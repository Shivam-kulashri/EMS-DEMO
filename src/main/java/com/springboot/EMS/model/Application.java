package com.springboot.EMS.model;

import jakarta.persistence.*;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String applicationStatus;

	private String dateApplied;

	@ManyToOne
	private Job job;

	@ManyToOne
	private JobSeeker jobSeeker;

	@ManyToOne
	private InterviewSchedular interviewSchedular;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public InterviewSchedular getInterviewSchedular() {
		return interviewSchedular;
	}

	public void setInterviewSchedular(InterviewSchedular interviewSchedular) {
		this.interviewSchedular = interviewSchedular;
	}

}
