package com.java.CareerHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
import com.java.CareerHub.model.JobApplication;
import com.java.CareerHub.util.DBConnUtil;
import com.java.CareerHub.util.DBPropertyUtil;

public class ApplicationDAOImpl implements ApplicationDAO{

	String connectionString;
	Connection connection;
	PreparedStatement ps;
	
	@Override
	public String insertJobApplication(JobApplication jobApplication) throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "INSERT INTO Applications (ApplicationID, JobID, ApplicantID, "
				+ "ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?, ?)";
		
		ps = connection.prepareStatement(command);
		
		ps.setInt(1, jobApplication.getApplicationId());
		ps.setInt(2, jobApplication.getJobId());
		ps.setInt(3, jobApplication.getApplicantId());
		ps.setTimestamp(4, Timestamp.valueOf(jobApplication.getApplicationDate()));
		ps.setString(5, jobApplication.getCoverLetter());
		
		int rowAffected = ps.executeUpdate();
		
		if(rowAffected>0) {
			return "Job Application inserted successfully.";
		}
			
		return "Failed to insert Job Application";
	}

	@Override
	public List<JobApplication> getApplicationsForJob(int jobId) throws ClassNotFoundException, SQLException {
		
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT * FROM Applications WHERE JobID = ?";
		ps = connection.prepareStatement(command);
		
		ps.setInt(1, jobId);
		
		ResultSet rs = ps.executeQuery();
		
		List<JobApplication> jobApplicationList = new ArrayList<JobApplication>();
		JobApplication jobApplication = null;
		
		while(rs.next())
		{
			jobApplication = new JobApplication();
			
			jobApplication.setApplicationId(rs.getInt("ApplicationID"));
			jobApplication.setJobId(rs.getInt("JobID"));
			jobApplication.setApplicantId(rs.getInt("ApplicantID"));
			jobApplication.setApplicationDate(rs.getTimestamp("ApplicationDate").toLocalDateTime());
			jobApplication.setCoverLetter(rs.getString("CoverLetter"));
			
			jobApplicationList.add(jobApplication);
		}
		return jobApplicationList;
	}

}