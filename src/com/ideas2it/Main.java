package com.ideas2it;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.project.controller.ProjectController;

/* Java program to choose to register  the  employee or 
 * project managament . In this we can create the  profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of collections
 * @author NANTHINEE S
 * @version 11.0.3
 * since 11-12-2020
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();	
        /*To create the object for Employeecontroller class */
        EmployeeController employeeController = new EmployeeController();         
        /* To create object for ProjectController class */  
        ProjectController projectController = new ProjectController();
        int optionOfEmployee;
        System.out.println(); 
        try {
            do {
                System.out.println("----------------Welcome----------------");
                System.out.println("Which Management you want to choose ");
                System.out.println("1:Project Management");               
                System.out.println("2:Employee Management");
                System.out.println("3:EmployeeProjectId");
                System.out.println("Enter your option ");
                Scanner inputReader = new Scanner(System.in);
                optionOfEmployee = inputReader.nextInt();
                switch(optionOfEmployee) {
                case 1:
                    /* Call the method projectManagement in class ProjectController */
                    projectController.projectManagement();             
                    break;                   
                case 2:
                    /* Call the method employeeManagement in class EmployeeController */
                    employeeController.employeeManagement();   
                    break;		    
                case 3:
                    break;
                default:
                    System.out.println("Enter the valid option");
                    break;
                }
            } while(optionOfEmployee != 3);
        } catch(InputMismatchException e) {
            System.out.println("Please enter the value in Number");
        }
    }
}
