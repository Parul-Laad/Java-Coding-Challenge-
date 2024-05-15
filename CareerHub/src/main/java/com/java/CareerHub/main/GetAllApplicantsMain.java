package com.java.CareerHub.main;

import java.sql.SQLException;
import java.util.List;

import com.java.CareerHub.dao.ApplicantDAO;
import com.java.CareerHub.dao.ApplicantDaoImpl;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.model.Applicant;

public class GetAllApplicantsMain {
    public static void main(String[] args) throws DatabaseConnectionException {
        ApplicantDAO applicantDAO = new ApplicantDaoImpl();
        try {
            List<Applicant> applicants = applicantDAO.getApplicants();
            for (Applicant applicant : applicants) {
                System.out.println(applicant);
            }

        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
            
    }
}