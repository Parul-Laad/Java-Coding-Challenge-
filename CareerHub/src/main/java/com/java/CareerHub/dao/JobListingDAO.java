package com.java.CareerHub.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.CareerHub.model.JobListing;

public interface JobListingDAO {
	
	String insertJobListing(JobListing jobListing) throws ClassNotFoundException, SQLException;
    List<JobListing> getJobListings() throws ClassNotFoundException, SQLException;
	
}