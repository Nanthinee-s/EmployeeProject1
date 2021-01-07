package com.ideas2it.employee.service;

import java.util.List;

import com.ideas2it.exception.EmployeeException;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;

public interface EmployeeService {
  
    /**
     * Method to generate Employeeid
     */    
    String generateId() throws EmployeeException;
    
    /**
     * Method to add details of Employee 
     */  
    void addEmployee(Employee person) throws EmployeeException ;
    
    /**
     * Method to add Address of the employee
     */
    void addAddress(Employee person,Address address);

    /**
     * Method to validate phonenumber
     */
    boolean checkValidatePhoneNumber(String phoneNumber);
   
    /**
     * Method to validate pincode
     */
    boolean checkValidPinCode(String pinCode);
    
    /**
     * method to check the mobile number is already exisiting or Not
     */  
    boolean checkExisitingNumber(String checkingNumber) throws EmployeeException;
    
    /**
     * Method to check the mailId is valid or not
     */
    boolean isEmailIdValid(String emailId);  

    /**
     * Method to check The EmployeeId is exist or not
     */
    boolean checkId(String employeeId) throws EmployeeException ;    
    
    /**    
     * Method to delete the Employee and its details by employeeId
     */
    boolean deleteEmployee(String employeeId) throws EmployeeException ;

    /**
     * Method to update The EmployeeStatus by EmployeeId
     */   
    int updateEmployeeStatus(String employeeId) throws EmployeeException ;
    
    /**
     * Method to update the details of the Employee using employeeId 
     */
    void updateDetails(String employeeId,Employee employeeDetail) throws EmployeeException ;
    
    /**
     * Method to get the entire Employee details in the list
     */
    List<Employee> entireEmployee() throws EmployeeException;
     
    /**
     * Method to get the entire Address of Employee details in the list
     */
    List<Address> entireAddress(String employeeId) throws EmployeeException;
}
