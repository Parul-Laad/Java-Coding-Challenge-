package com.java.CareerHub.main;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.java.CareerHub.dao.ApplicationDAO;
import com.java.CareerHub.dao.ApplicationDAOImpl;
import com.java.CareerHub.exception.ApplicationDeadlineException;
//import com.java.CareerHub.exception.ApplicationDeadlineException;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.model.JobApplication;

public class AddJobApplication {
    public static void main(String[] args) throws DatabaseConnectionException {
        Scanner scanner = new Scanner(System.in);
        ApplicationDAO applicationDAO = new ApplicationDAOImpl();

        JobApplication jobApplication = new JobApplication();
        System.out.println("Enter Job Application Details:");
        try {
            System.out.print("Application ID: ");
            jobApplication.setApplicationId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Job ID: ");
            jobApplication.setJobId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Applicant ID: ");
            jobApplication.setApplicantId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Application Date: (in the format yyyy-MM-dd HH:mm:ss ) ");
            
            String applicationDateString = scanner.nextLine();
            LocalDateTime postedDate = LocalDateTime.parse(applicationDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            jobApplication.setApplicationDate(postedDate);


          
            System.out.print("Cover Letter: ");
            jobApplication.setCoverLetter(scanner.nextLine());

            String result = applicationDAO.insertJobApplication(jobApplication);
            System.out.println(result);

        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
    }
}
