
package com.java.CareerHub.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CareerHub.dao.ApplicantDAO;
import com.java.CareerHub.dao.ApplicantDaoImpl;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.exception.InvalidEmailFormatException;
import com.java.CareerHub.model.Applicant;

public class AddApplicantMain {
    public static void main(String[] args) throws DatabaseConnectionException {
        Scanner scanner = new Scanner(System.in);
        ApplicantDAO applicantDAO = new ApplicantDaoImpl();

        Applicant applicant = new Applicant();
        System.out.println("Enter Applicant Details:");
        
        try {
            System.out.print("Applicant ID: ");
            applicant.setApplicantId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("First Name: ");
            applicant.setFirstName(scanner.nextLine());
            System.out.print("Last Name: ");
            applicant.setLastName(scanner.nextLine());
            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (!email.contains("@")) {
                throw new InvalidEmailFormatException("Invalid email format");
            }
            applicant.setEmail(email);
            System.out.print("Phone: ");
            applicant.setPhone(scanner.nextLine());
            System.out.print("Resume: ");
            applicant.setResume(scanner.nextLine());

            String result = applicantDAO.insertApplicant(applicant);
            System.out.println(result);
        } catch (InvalidEmailFormatException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
            
    }
}

