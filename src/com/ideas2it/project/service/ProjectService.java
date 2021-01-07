package com.ideas2it.project.service;

import java.util.List;

import com.ideas2it.exception.EmployeeException;
import com.ideas2it.project.model.Project;

public interface ProjectService{
    
    /**  
     * Method to add projects 
     */
    public void addProject(Project projectDetails) throws EmployeeException;
    
    /**
     * Method to check employeeId is exists or not
     */
    boolean checkEmployeeId(String employeeId) throws EmployeeException;
    
    /**
     * Method to get the existing projects 
     */
    List<Project> entireProject() throws EmployeeException;
    
    /**
     * Method to generate project Id
     */
    String generateId() throws EmployeeException;
  
    /**
     * Method to check the project Id is existing or not
     */
    boolean checkId(String projectId) throws EmployeeException; 
    
    /**
     * Method to delete project 
     */
    boolean deleteProject(String projectId) throws EmployeeException;

    /**
     * Method to update the status of project 
     */
    int updateProjectStatus(String projectId) throws EmployeeException;    
    
    /**
     * Method to updateDetails of the given Project
     */
    void updateDetails(String projectId, Project projectDetail) throws EmployeeException;     
}
