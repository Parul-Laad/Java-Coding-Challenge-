package com.java.CareerHub.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.java.CareerHub.dao.ApplicationDAO;
import com.java.CareerHub.dao.ApplicationDAOImpl;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.model.JobApplication;

public class GetApplicationsForJob {
    public static void main(String[] args) throws DatabaseConnectionException {
        Scanner scanner = new Scanner(System.in);
        ApplicationDAO applicationDAO = new ApplicationDAOImpl();

        System.out.print("Enter Job ID: ");
        int jobId = scanner.nextInt();

        try {
            List<JobApplication> applications = applicationDAO.getApplicationsForJob(jobId);
            for (JobApplication application : applications) {
                System.out.println(application);
            }
        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
    }
}
