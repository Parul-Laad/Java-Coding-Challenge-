package com.java.CareerHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.java.CareerHub.model.Company;
import com.java.CareerHub.model.JobListing;
import com.java.CareerHub.util.DBConnUtil;
import com.java.CareerHub.util.DBPropertyUtil;

public class JobListingDAOImpl implements JobListingDAO{
	
	Connection connection;
	PreparedStatement ps;
	
	@Override
	public String insertJobListing(JobListing jobListing) throws ClassNotFoundException, SQLException {

		    
		    String connectionString = DBPropertyUtil.getConnectionString("db");
		    connection = DBConnUtil.getConnection(connectionString);
		    
		    String command = "INSERT INTO Jobs (JobID, CompanyID, JobTitle, JobDescription, "
		    		+ "JobLocation, Salary, JobType, PostedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		    
		    ps = connection.prepareStatement(command);
		    
		    ps.setInt(1, jobListing.getJobId());
            ps.setInt(2, jobListing.getCompanyId());
            ps.setString(3, jobListing.getJobTitle());
            ps.setString(4, jobListing.getJobDescription());
            ps.setString(5, jobListing.getJobLocation());
            ps.setDouble(6, jobListing.getSalary());
            ps.setString(7, jobListing.getJobType());
            ps.setTimestamp(8, Timestamp.valueOf(jobListing.getPostedDate()));
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return "Job record added successfully.";
            } else {
                return "Failed to add Job record.";
            }

	}

	@Override
	public List<JobListing> getJobListings() throws ClassNotFoundException, SQLException {
		
		String connectionString = DBPropertyUtil.getConnectionString("db");
        connection = DBConnUtil.getConnection(connectionString);
        
        String command = "SELECT * FROM Jobs ";
        
        ps = connection.prepareStatement(command);
        
        ResultSet rs = ps.executeQuery();
        List<JobListing> jobListing = new ArrayList<JobListing>();
        
        while(rs.next()) {
        	JobListing job = new JobListing();
            job.setJobId(rs.getInt("JobID"));
            job.setCompanyId(rs.getInt("CompanyID"));
            job.setJobTitle(rs.getString("JobTitle"));
            job.setJobDescription(rs.getString("JobDescription"));
            job.setJobLocation(rs.getString("JobLocation"));
            job.setSalary(rs.getDouble("Salary"));
            job.setJobType(rs.getString("JobType"));
            job.setPostedDate(rs.getTimestamp("PostedDate").toLocalDateTime());
			jobListing.add(job);
        }
        return jobListing;

	}

}
