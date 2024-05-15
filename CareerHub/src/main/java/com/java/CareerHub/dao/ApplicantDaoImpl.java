package com.java.CareerHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.CareerHub.model.Applicant;
import com.java.CareerHub.util.DBConnUtil;
import com.java.CareerHub.util.DBPropertyUtil;

public class ApplicantDaoImpl implements ApplicantDAO{
	
	String connectionString;
	Connection connection;
	PreparedStatement ps;

	@Override
	public String insertApplicant(Applicant applicant) throws SQLException, ClassNotFoundException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "INSERT INTO Applicants (ApplicantID, FirstName, LastName,"
				+ " Email, Phone, Resume) VALUES (?, ?, ?, ?, ?, ?)";
		
        ps = connection.prepareStatement(command);
        
        ps.setInt(1, applicant.getApplicantId());
        ps.setString(2, applicant.getFirstName());
        ps.setString(3, applicant.getLastName());
        ps.setString(4, applicant.getEmail());
        ps.setString(5, applicant.getPhone());
        ps.setString(6, applicant.getResume());
        
        int rowsAffected = ps.executeUpdate();
        
        if(rowsAffected>0) {
        	return "Applicant Record inserted successfully.";
        }
        return "Failed to insert Applicant Record.";
	}

	@Override
	public List<Applicant> getApplicants() throws ClassNotFoundException, SQLException {
		
		connectionString = DBPropertyUtil.getConnectionString("db");
		connection = DBConnUtil.getConnection(connectionString);
		
		String command = "SELECT * FROM Applicants";
		ps = connection.prepareStatement(command);
		
		List<Applicant> applicantsList = new ArrayList<Applicant>();
		ResultSet rs = ps.executeQuery();
		
		Applicant applicant = null;
		while(rs.next())
		{
			applicant = new Applicant();
			
	        applicant.setApplicantId(rs.getInt("ApplicantID"));
	        applicant.setFirstName(rs.getString("FirstName"));
	        applicant.setLastName(rs.getString("LastName"));
	        applicant.setEmail(rs.getString("Email"));
	        applicant.setPhone(rs.getString("Phone"));
	        applicant.setResume(rs.getString("Resume"));
	        
	        applicantsList.add(applicant);
		}
		
		return applicantsList;
	}

}
