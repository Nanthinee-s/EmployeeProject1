package com.ideas2it.employee.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.model.Address;

/*
 * This class is a POJO class, this class set all the details of Employee
 * and also gets the details of Employees
 */
public class Employee {
    private String phoneNumber;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String emailId;
    private boolean status;
    private List<Address> address = new ArrayList<Address>();
   
    /* Using getter and setter method */

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmployeeId() {
        return employeeId; 
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public boolean getStatus() {
        return status;
    }
    public List<Address> getAddress() {
        return address;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
     
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    
    public String toString() {
        return (getEmployeeId() + "\t" + getFirstName() + "\t"
               + getLastName() + "\t" + getPhoneNumber() + "\t" + getEmailId() + "\t" + getStatus());      
    }
}
    
