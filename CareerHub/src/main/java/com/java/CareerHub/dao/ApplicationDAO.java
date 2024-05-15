package com.java.CareerHub.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.CareerHub.model.JobApplication;

public interface ApplicationDAO {
	
	String insertJobApplication(JobApplication jobApplication) throws ClassNotFoundException, SQLException;
    List<JobApplication> getApplicationsForJob(int jobId) throws ClassNotFoundException, SQLException;
}