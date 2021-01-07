package com.ideas2it.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.exception.EmployeeException;
import com.ideas2it.dbconnection.DataBaseConnection;
import com.ideas2it.project.model.Project;

/**
 * This class contains the list of Projects and the details of projects
 * all CRUD operations are done in this class
 * This class will be accessed by services
 */
public class ProjectDao {

    private static List<Project> projects = new ArrayList<Project>();
    DataBaseConnection dbConnection = DataBaseConnection.getInstance(); 
   
    /**
     * Method to register the project and its details
     * @param it get the detail of project from the Project pojo class
     */        
    public void registerProject(Project projectDetail) throws EmployeeException {    
        Connection connection = null;
        try{
            connection = dbConnection.getConnection(connection);
            String sql; 
            sql = "INSERT INTO Project (project_id, project_name, project_budget," +
                                      "project_time_frame , project_description, " +                           
                                      "status) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);           
            statement.setString(1, projectDetail.getProjectId());
            statement.setString(2, projectDetail.getProjectName());
            statement.setString(3, projectDetail.getProjectBudget());
            statement.setString(4, projectDetail.getProjectTimeFrame()); 
            statement.setString(5, projectDetail.getProjectDescription());
            statement.setBoolean(6, projectDetail.getStatus());   
            statement.executeUpdate();
        } catch(Exception ex) {
            throw new EmployeeException("Unable to register projects" + ex);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
    
    /**
     * Method to get the entire detail of project from the database
     * @return it returns the entire project in form of list
     */
    public List<Project> projectDetail() throws EmployeeException {
        Connection connection = null;
        List<Project> projects = new ArrayList<Project>();
        ResultSet result = null;
        try {       
            connection = dbConnection.getConnection(connection);
            String count = "SELECT * FROM Project"; 
            PreparedStatement statement = connection.prepareStatement(count);
            result = statement.executeQuery();
            while(result.next()) {
                Project project = new Project();                
                project.setProjectId(result.getString(1));
                project.setProjectName(result.getString(2));
                project.setProjectBudget(result.getString(3));
                project.setProjectTimeFrame(result.getString(4)); 
                project.setProjectDescription(result.getString(5));               
                project.setStatus(result.getBoolean("status"));
                projects.add(project);
            } 
        } catch (SQLException e) {
            throw new EmployeeException("Unable to display project" + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return projects;
    }
    
    /**
     * Method to count the ProjectsId for generate projectId
     * @return the int count value  
     */
    public int projectCount() throws EmployeeException {
        Connection connection = null;
        try {
            connection = dbConnection.getConnection(connection);
            String count = "SELECT COUNT(project_id) FROM Project";
            PreparedStatement statement = connection.prepareStatement(count);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1);
        } catch(Exception e) {
            throw new EmployeeException("Unable to count projects" + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }   
    
    /**
     * Method to update the status of project in the database
     * @param get the project Id for update
     */   
    public int updateProjectStatus(String projectId) throws EmployeeException {
        Connection connection = null;
        int noOfRowAffected;
        try {
            connection = dbConnection.getConnection(connection);
            String update = "UPDATE Project SET status = 1," +
                            "WHERE project_id = ?";
            PreparedStatement statement = connection.prepareStatement(update);
         
            statement.setString(1, projectId);
            noOfRowAffected = statement.executeUpdate();
        } catch(SQLException ex) {
            throw new EmployeeException("unable to update status" + ex);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return noOfRowAffected;
    }
    
    /**
     * Method used to check the given project id in the existing projects
     * @param get the projectId
     * @return the equvialent boolean value for given input
     */
    public boolean checkId(String projectId) throws EmployeeException {
        Connection connection = null;
        int count = 0;
        ResultSet result = null;
        try {
            connection = dbConnection.getConnection(connection);
            String checkId = "SELECT COUNT(Project_id) from Project where project_id = ?";
            PreparedStatement statement = connection.prepareStatement(checkId);
            statement.setString(1, projectId);
            result = statement.executeQuery();
            result.next();
            count = result.getInt(1);    
        } catch(SQLException e) {
            throw new EmployeeException("Unable to check the Project Id" + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        if(count != 0) {
            return true;
        } else {
            return false;
        }
    }     
    
    /* 
     * Method used to delete the project in the database 
     */
    public int deleteProjectById(String projectId) throws EmployeeException {
        Connection connection = null;
        int noOfRowsAffected;
        try { 
            connection = dbConnection.getConnection(connection);
            String delete = "UPDATE Project SET status = 0 WHERE project_id = ?";
            PreparedStatement statement = connection.prepareStatement(delete);  
            statement.setString(1, projectId);     
            noOfRowsAffected = statement.executeUpdate();    
        } catch(SQLException e) {
            throw new EmployeeException("Unable to delete " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return noOfRowsAffected; 
    }
 
    /**
     * Method used to update the detail of the employee 
     */
    public int updateDetails(String projectId, Project projectDetail) throws EmployeeException {
        Connection connection = null;
	int noOfRowsAffected = 0;
        try {
            connection = dbConnection.getConnection(connection);
            String insert = "UPDATE Project SET project_name = ?, project_budget = ?," +
                            "project_time_frame = ?, project_description = ?,"+
                             "status = ? WHERE project_id = ?";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, projectDetail.getProjectName());
            statement.setString(2, projectDetail.getProjectBudget());
            statement.setString(3, projectDetail.getProjectTimeFrame());
            statement.setString(4, projectDetail.getProjectDescription());
            statement.setBoolean(5, projectDetail.getStatus());
            statement.setString(6, projectId);
            noOfRowsAffected = statement.executeUpdate();
            
        } catch(SQLException exception) {
            throw new EmployeeException("Unable to update project Details");
        } finally {
            dbConnection.closeConnection(connection);
	    return noOfRowsAffected;
        }
    }    
}  
