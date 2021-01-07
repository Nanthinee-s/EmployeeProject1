package com.ideas2it.employee.dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dbconnection.DataBaseConnection;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

/*
 * This class contains the list of Employees and the details of Employees
 * all CRUD operations are done in this class
 * This class will be accessed by services
 */

public class EmployeeDao {

    private static List<Employee> employees = new ArrayList<Employee>();
    Address addressDetails = new Address(); 
    DataBaseConnection dbConnection = DataBaseConnection.getInstance();
    
    /*
     * Method used to create the employee in the database 
     * @param it gets the parameter of Employees type to create the person
     */   
    public void createEmployee(Employee employeeDetail) throws EmployeeException {
	System.out.println("DAO reached"+employeeDetail);
        Connection connection = null;
        try{
            connection = dbConnection.getConnection(connection);
            String sql; 
            sql = "INSERT INTO Employee(employee_id, first_name, last_name, phone_number, emailId," +
                   "status) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employeeDetail.getEmployeeId());
            statement.setString(2, employeeDetail.getFirstName());
            statement.setString(3, employeeDetail.getLastName());
            statement.setString(4, employeeDetail.getPhoneNumber());
            statement.setString(5, employeeDetail.getEmailId()); 
            statement.setBoolean(6, employeeDetail.getStatus());
            statement.executeUpdate();
	    registerAddress(employeeDetail,connection);
        } catch(SQLException ex) {
            throw new EmployeeException ("Unable to create Employee" + ex);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
     
    /* 
     * Method used to register the address employee in the database 
     * @Param it get the parameter of Employees type to create the person 
     */
    private void registerAddress(Employee employeeDetail, Connection connection) throws EmployeeException {
        List<Address> addresses = employeeDetail.getAddress();
        try {
            for(Address address : addresses) {
                String sql = "INSERT INTO Address(parent_id, streetName, areaName, cityName, pincode) values (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, address.getId());
                statement.setString(2, address.getStreetName());
                statement.setString(3, address.getAreaName());
                statement.setString(4, address.getCityName());
                statement.setString(5, address.getPinCode());
                statement.executeUpdate();
            }
        } catch(SQLException ex) {
            throw new EmployeeException("Unable to add Address" + ex);
        } 
    }     
   
    /*
     * Method used to count the employee in the database 
     * @return the count of employees in the dataBase
     */
    public int employeeCount() throws EmployeeException {
        Connection connection = null;
        try {
            connection = dbConnection.getConnection(connection);
            String count = "SELECT COUNT(employee_id) from Employee ";
            PreparedStatement statement = connection.prepareStatement(count);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1);        
        } catch(SQLException e) {
            throw new EmployeeException("unable to count employee" + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    /* 
     * Method is used to check the given ID is present in the database or not
     */
    public boolean checkId(String employeeId) throws EmployeeException {
        Connection connection = null;
        int count = 0;
        ResultSet result = null;
        try {
            connection = dbConnection.getConnection(connection);
            String checkId = "SELECT COUNT(employee_id) from Employee where employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(checkId);
            statement.setString(1, employeeId);
            result = statement.executeQuery();
            result.next();
            count = result.getInt(1);    
        } catch(SQLException exception) {
            throw new EmployeeException("unable to fetch the employee id");
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
     * Method used to delete the employee in the database 
     */
    public int deleteEmployeeById(String employeeId) throws EmployeeException {
        Connection connection = null;
        int noOfRowsAffected;
        try { 
            connection = dbConnection.getConnection(connection);
            String delete = "UPDATE Employee SET status = 0 WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(delete);  
            statement.setString(1, employeeId);     
            noOfRowsAffected = statement.executeUpdate();    
        } catch(SQLException e) {
            throw new EmployeeException("Unable to delete " + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return noOfRowsAffected; 
    }
     
    /* 
     * Method is used to update the status of the employee
     */
    public int updateEmployeeStatus(String employeeId) throws EmployeeException {
        Connection connection = null;
        int noOfRowAffected;
        try {
            connection = dbConnection.getConnection(connection);
            String update = "UPDATE Employee SET status = 1,"
                            +"WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(2, employeeId);
            noOfRowAffected = statement.executeUpdate();
        } catch(SQLException ex) {
            throw new EmployeeException("Exception :" + ex);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return noOfRowAffected;
    }
    
    /*
     * Method used to update the detail of the employee 
     */
    public int updateDetails(String employeeId,Employee employeeDetail) throws EmployeeException {
        Connection connection = null;
	int noOfRowsAffected = 0;
        try {
            connection = dbConnection.getConnection(connection);
            String insert = "UPDATE Employee SET first_name = ?, last_name = ?,"
                                 + "phone_number = ?, emailId = ?, status = ? WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, employeeDetail.getFirstName());
            statement.setString(2, employeeDetail.getLastName());
            statement.setString(3, employeeDetail.getPhoneNumber());
            statement.setString(4, employeeDetail.getEmailId());
            statement.setBoolean(5, employeeDetail.getStatus());
            statement.setString(6, employeeId);
            noOfRowsAffected = statement.executeUpdate();
            updateAddress(employeeId, employeeDetail);
        } catch(SQLException exception) {
            throw new EmployeeException("Unable to update employee Details");
        } finally {
            dbConnection.closeConnection(connection);
	    return noOfRowsAffected;
        }
    }

    /* 
     * Method is used to update the addresss detail of employee
     */ 
    public int updateAddress(String employeeId, Employee employeeDetail) throws EmployeeException {
       Connection connection = null;
	int noOfRowsAffected = 0;
        List<Address> addresses = employeeDetail.getAddress();
        try {
            connection = dbConnection.getConnection(connection);
            for(Address address : addresses) {
                String updateQuery = "UPDATE Address SET street_name = ?, area_name = ?,"
                             +" city = ?, pincode = ? WHERE employee_id = ?";
                PreparedStatement statement = connection.prepareStatement(updateQuery);
                statement.setString(1, address.getStreetName());
                statement.setString(2, address.getAreaName());
                statement.setString(3, address.getCityName());
                statement.setString(4, address.getPinCode());                             
                noOfRowsAffected = statement.executeUpdate();
		updateAddress(employeeId, employeeDetail);
            }
        } catch(SQLException e) {
            throw new EmployeeException("Unable to update Address" + e);
        } finally {
            dbConnection.closeConnection(connection);
	  return noOfRowsAffected;
        }
    }
   
     
    /* 
     * This return all employees in the form of list 
     */
    public List<Employee> employeeDetail() throws EmployeeException {
        Connection connection = null;
        List<Employee> employees = new ArrayList<Employee>();
        ResultSet result = null;   
        try{  
            connection = dbConnection.getConnection(connection);
            String count = "SELECT * FROM Employee"; 
            PreparedStatement statement = connection.prepareStatement(count);
            result = statement.executeQuery();
            while(result.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(result.getString(1));
                employee.setFirstName(result.getString(2));
                employee.setLastName(result.getString(3));
                employee.setPhoneNumber(result.getString(4)); 
                employee.setEmailId(result.getString(5));
                employee.setStatus(result.getBoolean("status"));
                employees.add(employee);
            }
        } catch(Exception e) {
            throw new EmployeeException("Unable to display employee " + e);        
        } finally {
            dbConnection.closeConnection(connection);
        }
                return employees;
    }
    
    public List<Address> addressDetail(String employeeId) throws EmployeeException {
        Connection connection = null;
        List<Address> addresses = new ArrayList<Address>();
        ResultSet result = null;
        try {
            connection = dbConnection.getConnection(connection);
            String noOfAddress = "SELECT * FROM Address WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(noOfAddress);
            statement.setString(1, employeeId);
            result = statement.executeQuery();
            while(result.next()) {
                Address address = new Address();
                address.setId(result.getString(1));
                address.setStreetName(result.getString(4));
                address.setAreaName(result.getString(5));
                address.setCityName(result.getString(6));
                address.setPinCode(result.getString(7));
                addresses.add(address);
            }
        } catch(SQLException e) {
            throw new EmployeeException("Unable to display address detail" + e);
        } finally {
            dbConnection.closeConnection(connection);
        }
        return addresses;
    }
}
