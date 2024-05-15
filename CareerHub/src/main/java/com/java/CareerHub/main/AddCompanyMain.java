package com.java.CareerHub.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CareerHub.dao.CompanyDAO;
import com.java.CareerHub.dao.CompanyDAOImpl;
import com.java.CareerHub.exception.DatabaseConnectionException;
import com.java.CareerHub.model.Company;

public class AddCompanyMain {
    public static void main(String[] args) throws DatabaseConnectionException {
        Scanner scanner = new Scanner(System.in);
        CompanyDAO companyDAO = new CompanyDAOImpl();

        Company company = new Company();
        System.out.println("Enter Company Details:");
        try {
            System.out.print("Company ID: ");
            company.setCompanyId(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Company Name: ");
            company.setCompanyName(scanner.nextLine());
            System.out.print("Company Location: ");
            company.setLocation(scanner.nextLine());

            String result = companyDAO.insertCompany(company);
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException e) {
        	throw new DatabaseConnectionException("Error connecting to database");
        }
    }
}
