package com.java.CareerHub.main;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.java.CareerHub.dao.JobListingDAO;
import com.java.CareerHub.dao.JobListingDAOImpl;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.exception.SalaryCalculationException;
import com.java.CareerHub.model.JobListing;

public class AddJobListingMain {
    public static void main(String[] args) throws DatabaseConnectionException {
        Scanner scanner = new Scanner(System.in);
        JobListingDAO jobListingDAO = new JobListingDAOImpl();

        JobListing jobListing = new JobListing();
        System.out.println("Enter Job Listing Details:");
        try {
            System.out.print("Job ID: ");
            jobListing.setJobId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Company ID: ");
            jobListing.setCompanyId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Job Title: ");
            jobListing.setJobTitle(scanner.nextLine());
            System.out.print("Job Description: ");
            jobListing.setJobDescription(scanner.nextLine());
            System.out.print("Job Location: ");
            jobListing.setJobLocation(scanner.nextLine());
            System.out.print("Salary: ");
            double salary = scanner.nextDouble();
            if (salary <= 0) {
                throw new SalaryCalculationException("Invalid salary amount");
            }
            jobListing.setSalary(salary);
            scanner.nextLine();
            
            System.out.print("Job Type: (Part-time/full-time/contract) ");
            jobListing.setJobType(scanner.nextLine());
            System.out.print("Posted Date:(in the format yyyy-MM-dd HH:mm:ss ) ");
            String postedDateString = scanner.nextLine();
            LocalDateTime postedDate = LocalDateTime.parse(postedDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            jobListing.setPostedDate(postedDate);

            String result = jobListingDAO.insertJobListing(jobListing);
            System.out.println(result);
        } catch (SalaryCalculationException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
    }
}
