package com.java.CareerHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.CareerHub.util.DBConnUtil;
import com.java.CareerHub.util.DBPropertyUtil;
import com.java.CareerHub.model.Company;

public class CompanyDAOImpl implements CompanyDAO{
	
	Connection connection;
	PreparedStatement ps;

	@Override
	public String insertCompany(Company company) throws ClassNotFoundException, SQLException {
		String connectionString = DBPropertyUtil.getConnectionString("db");
        connection = DBConnUtil.getConnection(connectionString);
        
        String command = "INSERT INTO Companies (CompanyID, CompanyName, Location) VALUES (?, ?, ?)";
        
        ps = connection.prepareStatement(command);
        
        ps.setInt(1, company.getCompanyId());
        ps.setString(2, company.getCompanyName());
        ps.setString(3, company.getLocation());
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            return "Company record added successfully.";
        } else {
            return "Failed to add company record.";
        }
	}

	@Override
	public List<Company> getCompanies() throws ClassNotFoundException, SQLException {
		
		String connectionString = DBPropertyUtil.getConnectionString("db");
        connection = DBConnUtil.getConnection(connectionString);
        
        String command = "SELECT * FROM Companies ";
        
        ps = connection.prepareStatement(command);
        
        ResultSet rs = ps.executeQuery();
        List<Company> companies = new ArrayList<Company>();
        
        while(rs.next()) {
        	Company company = new Company();
        	company.setCompanyId(rs.getInt("CompanyID"));
            company.setCompanyName(rs.getString("CompanyName"));
            company.setLocation(rs.getString("Location"));
            companies.add(company);
            
        }
        return companies;
	}
	

}
