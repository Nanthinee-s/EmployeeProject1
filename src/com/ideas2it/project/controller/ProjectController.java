package com.ideas2it.project.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
 
import com.ideas2it.project.model.Project;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.service.impl.ProjectServiceImpl;
import com.ideas2it.project.service.ProjectService;

/* Java program to register the Projects profile
 * In this we can create the Project profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of collections
 * @author NANTHINEE S 
 * @version 11.0.3
 * since 11-12-2020
 */

public class ProjectController {
    static Scanner input = new Scanner(System.in);
    ProjectService projectService = new ProjectServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    public void projectManagement() {
        ProjectController projectController = new ProjectController();
        System.out.println();
        int option;
        try {
            do {
                System.out.println("----------MENU----------");
                System.out.println("please Select the option");
		        System.out.println("1: RegisterProject \n2: Update Project \n3: Delete Project\n"
		        +"4: Display All Projects \n" + "5: Exit\n");
		        System.out.println("Enter your option");
		        option = input.nextInt(); 
		        switch(option) {
		        case 1:
		            /* This method is used to call the method to update the Project*/ 
		            registerProject();
		            break;
		        case 2:
		            /* This method is used to update the project details*/
		            updateProjectDetails();
		            break;
		        case 3:
		            /* Here is used to call the method to delete the project */
		            deleteProjectDetails();
		            break;
		        case 4:
		            /* Here it used to call the method to display all project */
		            displayAllProjects(); 
		            break;		   		            
		        case 5:
		            break;
		        default :
		            System.out.println("----------Invalid Choice---------- ");
		            break;
		        }
		    } while (option != 5);
        } catch(InputMismatchException exception) {
            System.out.println("please enter the value in numbers");
        }
    }
    
    /**
     * Create a new project with the details
     */
    private void registerProject() {
        boolean wantMoreProject = true; 
        try {
            do {
                Project projectDetail = new Project();		
                //System.out.println("Enter projectID :");
                //employeeDetail.setEmployeeId(inputReader.next());
                String projectId = projectService.generateId();
                projectDetail.setProjectId(projectId);
                System.out.println("Enter project name :");
                projectDetail.setProjectName(input.next());
                System.out.println("Enter project budget :");
                projectDetail.setProjectBudget(input.next());
                System.out.println("Enter project time frame:");
                projectDetail.setProjectTimeFrame(input.next());
                System.out.println("Enter project des:");
                projectDetail.setProjectDescription(input.next());
                projectDetail.setStatus(true); 
                System.out.println();		
                projectService.addProject(projectDetail); 
                System.out.println("-------Created Successfully--------");  
                System.out.println("If you want create another Project : yes/no");
                if (input.next().equals("no")) {
                    wantMoreProject = false;
                }     
            } while (wantMoreProject);
        } catch(EmployeeException exception) {  
            System.out.println(exception.getMessage());
        }
    }
    
   /**
     * Method is used to update the project details
     */
    private void updateProjectDetails() {
        Project projectDetail = new Project();
        try {
            System.out.println("Enter the ProjectId :");
            String projectId = input.next();
            if(employeeService.checkId(projectId)) {
                projectDetail.setProjectId(projectId);
                System.out.println("Enter the project Name");
                projectDetail.setProjectName(input.next());
                System.out.println("Enter the project budget");
                projectDetail.setProjectBudget(input.next());
                System.out.println("Enter the project time frame");
                projectDetail.setProjectTimeFrame(input.next());
                System.out.println("Enter project description ");
                projectDetail.setProjectDescription(input.next());           
                projectDetail.setStatus(true);                                
                projectService.updateDetails(projectId,projectDetail);
               	
                System.out.println("Updated Successfully");
            } else {
                System.out.println("The project Id is not registered ");
            }
        } catch(EmployeeException exception) {
            System.out.println(exception.getMessage());
        }
    }
        
    /**
     * Display All projects and its details in the database 
     */
    private void displayAllProjects() {
        System.out.println("projectId" + "\t" + "projectName" + "\t" + "projectBudget" + "\t" 
                           +"projectDescription" + "status" + "\t");
                          
        System.out.println
            ("--------------------------------------------------" + 
            "----------------------------------------------------------------");
        try {
            for(Project project : projectService.entireProject()) {
                System.out.println(project +"\t");    
            }
        } catch(EmployeeException exception) {
            System.out.println(exception.getMessage());
        }
    }
       
    /**
     * Method is used to delete(soft delete) the employees
     */ 
    private void deleteProjectDetails() {
        boolean wantMoreDeletion = true;
        while(wantMoreDeletion) {
            try {
                System.out.println("Enter projectId");
                if(projectService.deleteProject(input.next())) {
                    System.out.println("Successfully Removed");
                } else {
                    System.out.println("the ProjectId is not registered");
                }
            } catch(EmployeeException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("If you want further deletion : yes/no");
            if(input.next().equals("no")) {
                wantMoreDeletion = false;
            }
        }
    }  
} 
    
    
