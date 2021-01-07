package com.ideas2it.employee.service.impl;

import java.util.List;

import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.exception.EmployeeException;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.service.EmployeeService;
import util.Util;

/*
 *  This class will come under the package of ideas2it
 *  this class contains the business logic
 *  In this class, it access EmployeeDao and EmployeeUtil class
 */
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao = new EmployeeDao();
    Util util = new Util();
    static int autoGenerateId = 1;
    
    /**
     * Method to automatically generate the employeeid 
     * @return employeeId
     */      
    public String generateId() throws EmployeeException {
        return "EMP -" + (employeeDao.employeeCount()+1); 
    }
    
    /**
     * Method to Add the detail of the Employee
     * @param get the detail of Employee from Employee pojo class
     */
    public void addEmployee(Employee person) throws EmployeeException {
        employeeDao.createEmployee(person);
    }
    
    /**
     * Method to set the address of the Employee
     * @param get the EmployeeDetails and Also Address pojo class to set 
     */
    public void addAddress(Employee person, Address address) {
        List<Address> addresses = person.getAddress();
        addresses.add(address);
        person.setAddress(addresses);
    } 
 
    /**
     * Method to check the phone number is valid or not 
     * @param it get the phonenumber
     * @return boolean value for the given input
     */    
    public boolean checkValidatePhoneNumber(String phoneNumber) {
        return util.validateNumber(phoneNumber);
    }
   
    /**
     * Method to check the pincode is valid or not 
     * @param it get the pincode from Controller
     * @return boolan value for the given input
     */
    public boolean checkValidPinCode(String pinCode) {
        return util.validatePinCode(pinCode);
    }
 
    /**
     * Method to check the phone number is already Existing or not
     * @param get the phonenumber from controller
     * @return boolean for the given input
     */
    public boolean checkExisitingNumber(String checkingNumber) throws EmployeeException {
        for(Employee employeeDetails : entireEmployee()) {
            if(employeeDetails.getPhoneNumber().equals(checkingNumber)) {
                return false;
            } 
        }
        return true;
    }

    /**
     * Method to validate the emailId
     * @param it get the emailId from Controller
     * @return boolean value for the given input
     */
    public boolean isEmailIdValid(String emailId) {
        return util.isEmailIdValid(emailId);
    }
    
    /**
     * Method to check the Employee Id Existing or not
     * @param it get the Employeeid 
     * @return the boolean value for The given input
     */
    public boolean checkId(String employeeId) throws EmployeeException {
        return employeeDao.checkId("EMP -"+employeeId);
    }
     
     
    /**
     * Method to delete the employee by using EmployeeId
     * @param get the employee id
     */
    public boolean deleteEmployee(String employeeId) throws EmployeeException {
        if(employeeDao.deleteEmployeeById("EMP -"+employeeId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /* Method used to update the status of employee */
    public int updateEmployeeStatus(String employeeId) throws EmployeeException {
        return (employeeDao.updateEmployeeStatus(employeeId));
    }
    
    /* Method used to update the detail of the employee by calling the dao method */
    public void updateDetails(String employeeId,Employee employeeDetail) throws EmployeeException {
        employeeDao.updateDetails("EMP -"+employeeId,employeeDetail);
    }
    public void updateAddress(String employeeId,Employee employeeDetail) throws EmployeeException {
        employeeDao.updateAddress("EMP -"+employeeId,employeeDetail);
    }
   
    /**
     * @return all Employee in the form of list
     */
    public List<Employee> entireEmployee() throws EmployeeException {
        return (employeeDao.employeeDetail());
    }
    
    /**
     * Method to get The entire Address of given Employee using employeeId
     * @param get the employeeId
     * @return the list of Employee
     */
    public List<Address> entireAddress(String employeeId) throws EmployeeException {
        return(employeeDao.addressDetail(employeeId));
    }
}
   
  
