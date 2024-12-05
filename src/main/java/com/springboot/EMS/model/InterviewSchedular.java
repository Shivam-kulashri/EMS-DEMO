package com.springboot.EMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class InterviewSchedular {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int interviewId;

	private String interviewDate;

	private String interviewTime;

	private String interviewMode;

	private String interviewStatus;

	private String interviewType;

	private String lastUpdated;

	@ManyToOne
	private Interviewer interviewer;

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getInterviewMode() {
		return interviewMode;
	}

	public void setInterviewMode(String interviewMode) {
		this.interviewMode = interviewMode;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Interviewer getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(Interviewer interviewer) {
		this.interviewer = interviewer;
	}

}
