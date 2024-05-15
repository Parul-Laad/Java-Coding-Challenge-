package com.java.CareerHub.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.CareerHub.model.Company;

public interface CompanyDAO {

	String insertCompany(Company company) throws ClassNotFoundException, SQLException;
    List<Company> getCompanies() throws ClassNotFoundException, SQLException;

}