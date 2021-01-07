package com.ideas2it.project.service.impl;

import java.util.List;

import com.ideas2it.project.dao.ProjectDao;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.project.model.Project;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.project.service.ProjectService;
import util.Util;

/*
 * This class will come under the package of ideas2it
 * This class contains the business logic
 * In this class, it access projectDao and Util class
 */
public class ProjectServiceImpl implements ProjectService {

    ProjectDao projectDao = new ProjectDao();
    Util util = new Util();
    EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * Method to add the detail of the project 
     * @param it get the value from the Project pojo
     */
    public void addProject(Project projectDetails) throws EmployeeException {
        projectDao.registerProject(projectDetails);
    }
    
    /**
     * Method to generate project Id
     * return projectId
     */
    public String generateId() throws EmployeeException {
         return "PRO -" + (projectDao.projectCount()+1); 
    }

    /**
     * Method to check the given employeeId in the employee table
     * @param get the value of employee Id
     * @return boolean value 
     */
    public boolean checkEmployeeId(String employeeId) throws EmployeeException {
        return employeeService.checkId(employeeId);
    } 
    
    /**
     * Method to check the projectId in the Project Table
     * @param it get the projectid
     * @return boolean for the given input
     */
    public boolean checkId(String projectId) throws EmployeeException {
        return projectDao.checkId(projectId);
    } 
 
    /**
     * @return all projects in form of list
     */
    public List<Project> entireProject() throws EmployeeException {
        return (projectDao.projectDetail()); 
    }
      
    /**
     * Method to delete the project by using EmployeeId
     * @param get the project id
     */
    public boolean deleteProject(String projectId) throws EmployeeException {
        if(projectDao.deleteProjectById("PRO -"+projectId) == 1) {
            return true;
        } else {
            return false;
        }
    }

     /* Method used to update the status of project */
    public int updateProjectStatus(String projectId) throws EmployeeException {
        return (projectDao.updateProjectStatus(projectId));
    }
    
    /**
     * Method to update the detail of project
     * @params it get the Project pojo class and also get the projectId
     * Method used to update the detail of the employee by calling the dao method
     */ 
    public void updateDetails(String projectId,Project projectDetail) throws EmployeeException {
        projectDao.updateDetails("PRO -"+projectId,projectDetail);
    } 
}
