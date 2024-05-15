package com.java.CareerHub.main;

import java.sql.SQLException;
import java.util.List;

import com.java.CareerHub.dao.CompanyDAO;
import com.java.CareerHub.dao.CompanyDAOImpl;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.model.Company;

public class GetAllCompanies {
    public static void main(String[] args) throws DatabaseConnectionException {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        try {
            List<Company> companies = companyDAO.getCompanies();
            for (Company company : companies) {
                System.out.println(company);
            }
        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
    }
}
