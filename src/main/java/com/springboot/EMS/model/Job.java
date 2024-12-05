package com.springboot.EMS.model;

import jakarta.persistence.*;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String title;

	private String description;

	private String location;

	private String qualifications;

	private String experienceRequired;

	private String jobType;

	private String salaryRange;

	private String datePosted;

	private String applicationDeadline;

	@ManyToOne
	private Hr hr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(String experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public String getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(String applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	public Hr getHr() {
		return hr;
	}

	public void setHr(Hr hr) {
		this.hr = hr;
	}

}
