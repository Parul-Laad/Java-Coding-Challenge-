package com.java.CareerHub.main;

import java.sql.SQLException;
import java.util.List;

import com.java.CareerHub.dao.JobListingDAO;
import com.java.CareerHub.dao.JobListingDAOImpl;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.model.JobListing;

public class GetAllJobListingsMain {
    public static void main(String[] args) throws DatabaseConnectionException {
        JobListingDAO jobListingDAO = new JobListingDAOImpl();
        try {
            List<JobListing> jobListings = jobListingDAO.getJobListings();
            for (JobListing jobListing : jobListings) {
                System.out.println(jobListing);
            }
        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
    }
}
