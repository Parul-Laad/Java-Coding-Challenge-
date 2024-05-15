package com.java.CareerHub.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.CareerHub.model.Applicant;

public interface ApplicantDAO {
	
	String insertApplicant(Applicant applicant) throws SQLException, ClassNotFoundException;
	List<Applicant> getApplicants() throws ClassNotFoundException, SQLException;
	
}