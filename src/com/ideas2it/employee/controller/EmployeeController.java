package com.ideas2it.employee.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.exception.EmployeeException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl; 

/** 
 * Java program to register the Employee profile
 * In this we can create the employee profile and display the profile
 * Update the profile and Delete the profile
 * by using CRUD operation with the help of collections
 * @author NANTHINEE S 
 * @version 11.0.3
 * since 11-12-2020
 */

public class EmployeeController { 

    EmployeeService employeeService = new EmployeeServiceImpl();
    static Scanner inputReader = new Scanner(System.in);
    public void employeeManagement() {
        int optionOfEmployee;        
        try {
            System.out.println("**********Employee MANAGEMENT SERVICE **********");
            do {
                System.out.println("");
                System.out.println("----------MENU----------");
                System.out.println();
                System.out.println("1.Register Employee\n 2.Update Employee details\n"
                + "3.Delete employee details\n" + "4.display All employees \n" + "5.Exit");
                System.out.println("");
                System.out.println("Enter your choice ");
                optionOfEmployee = inputReader.nextInt();
                switch (optionOfEmployee) {
                case 1:
                    /* Here this call the method registerEmployee */
                    registerEmployee();                     
                    break;
                case 2:
                    /* Here this call the method to updateEmployeeDetails */
                    updateEmployeeDetails();              
                    break;
                case 3:  
                    /* Method to delete(soft delete) employee detail */    
                    deleteEmployeeDetails();               
                    break;              
                case 4:
                    /* Here it display the entire employee */
                    displayAllEmployees();                
                    break;                
                case 5:
                    break;
                default:
                    System.out.println("*************** INVALID_CHOICE **" +
                                           "************");
                    break;
                }
            } while(optionOfEmployee != 5);
        } catch(InputMismatchException exception) {
            System.out.println("Please enter the value in Number ");
        }
    } 

    /**
     * Method to Register the Employee and its details 
     */
    private void registerEmployee() {
        boolean wantMoreEmployee = true; 
        try {
            do {
                Employee employeeDetail = new Employee();
		Address address = new Address();
		List<Address> addresses = new ArrayList<Address>();
                employeeDetail.setPhoneNumber(putMobileNumber());
                //System.out.println("Enter employeeID :");
                //employeeDetail.setEmployeeId(inputReader.next());
                String employeeId = employeeService.generateId();
                employeeDetail.setEmployeeId(employeeId);
                System.out.println("Enter First name :");
                employeeDetail.setFirstName(inputReader.next());
                System.out.println("Enter Last name :");
                employeeDetail.setLastName(inputReader.next());
                employeeDetail.setEmailId(putMailId());
                employeeDetail.setStatus(true); 
                System.out.println();
                addresses = putAddress(employeeDetail);
		employeeDetail.setAddress(addresses);
                employeeService.addEmployee(employeeDetail); 
                System.out.println("-------Created Successfully--------");  
                System.out.println("If you want create another Employee : yes/no");
                if (inputReader.next().equals("no")) {
                    wantMoreEmployee = false;
                }     
            } while (wantMoreEmployee);
        } catch(EmployeeException exception) {  
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Method is used to update the employee details
     */
    private void updateEmployeeDetails() {
        Employee employeeDetail = new Employee();
        try {
            System.out.println("Enter the EmployeeId :");
            String employeeId = inputReader.next();
            if(employeeService.checkId(employeeId)) {
                employeeDetail.setEmployeeId(employeeId);
                employeeDetail.setPhoneNumber(putMobileNumber());
                System.out.println("Enter new First name :");
                employeeDetail.setFirstName(inputReader.next());
                System.out.println("Enter new Last name :");
                employeeDetail.setLastName(inputReader.next());
                employeeDetail.setEmailId(putMailId()); 
                employeeDetail.setStatus(true); 
                System.out.println();
                System.out.println("The address of the given employee is display" +
                                   " below use the id to change the address");
                System.out.println();
                System.out.println("Address of the employee ");
                //displayAddress(employeeId);
                employeeService.updateDetails(employeeId,employeeDetail);
		updateEmployeeAddress(employeeDetail);
                System.out.println("Updated Successfully");
            } else {
                System.out.println("The employee Id is not registered ");
            }
        } catch(EmployeeException exception) {
            System.out.println(exception.getMessage());
        }
    }
   

    /**
     * Method is used to print the entire employees 
     */
    private void displayAllEmployees() {
        System.out.println("PhoneNumber" + "\t" + "employeeId" + "\t" + "FirstName" + "\t" + "LastName" +
                           "\t" + "EmailId" + "\t" + "\t" + "Status");
        System.out.println
            ("-----------------------------------------------------------");
        try {
            for(Employee employee : employeeService.entireEmployee()) {
                System.out.println(employee +"\t");    
            } 
        } catch(EmployeeException exception) {
            System.out.println(exception.getMessage());
        }
    }
    

    /**
     * Method to display the employee Address detail at time of updating 
     * @param get the employeeId for update address
     */
    private void displayAddress(String employeeId) {
        System.out.println("id" + "\t" + "StreetName" + "\t" + "AreaName" +
                           "\t" +"cityName" + "\t" + "pincode" + "\t");
        System.out.println("-----------------------------------------------" +
                           "-----------------------");
        try {
            for(Address address : employeeService.entireAddress(employeeId)) {
                if(employeeService.checkId(employeeId)) {
                    System.out.println(address+ "\t");
                }
            }
        } catch(EmployeeException exception) {
            System.out.println(exception.getMessage());
        }
    }    

    /**
     * Method is used to delete(soft delete) the employee
     */ 
    private void deleteEmployeeDetails() {
        boolean wantMoreDeletion = true;
        while(wantMoreDeletion) {
            try {
                System.out.println("Enter EmployeeId");
                if(employeeService.deleteEmployee(inputReader.next())) {
                    System.out.println("Successfully Removed");
                } else {
                    System.out.println("the employeeId is not registered");
                }
            } catch(EmployeeException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("If you want further deletion : yes/no");
            if(inputReader.next().equals("no")) {
                wantMoreDeletion = false;
            }
        }
    }  
  
    /** 
     *  method used to update the phoneNumber value in the employee details 
     *  @return phoneNumber
     */
    private String putMobileNumber() {
        String phoneNumber;
        boolean validPhoneNumber = false;
        do{
            System.out.println("Enter the phone number");
            phoneNumber = inputReader.next();
            try {
                validPhoneNumber = (employeeService.checkValidatePhoneNumber
                                   (phoneNumber));
                if(!validPhoneNumber) {
                    System.out.println("Please entert the valid phoeNumber"); 
                } else {
                    validPhoneNumber = (employeeService.checkExisitingNumber
                                       (phoneNumber)); 
                    if(!validPhoneNumber) {
                        System.out.println("Number Already exists");
                    }
                } 
            } catch(EmployeeException exception) {
                System.out.println(exception.getMessage());
            }
        } while(!validPhoneNumber);
        return phoneNumber;
    }
    
    /**
     * Method to update the emailID in the employee details 
     * @return emailId
     */
    private String putMailId() {
        String emailId;
        boolean  validEmailId = false;
        do {
            System.out.println("Enter EmailId");
            emailId = inputReader.next();
            validEmailId = employeeService.isEmailIdValid(emailId);
            if (validEmailId != true) {
                System.out.println("Please Enter Valid Email Id");
            }
        } while (validEmailId != true);
        return emailId;
    }
    
    /** 
     * method is used to set the pincode
     * @return pincode
     */
    private String putPincode() {
        boolean validPinCode = false;
        String pinCode;
        do {
            System.out.println("Enter pincode");
            pinCode = inputReader.next();
            validPinCode = employeeService.checkValidPinCode(pinCode);
            if(!validPinCode) {
                System.out.println("please enter the valid pincode"); 
            }
        } while(!validPinCode);
        return pinCode;
    }
    
    /**
     * Method used to set the address of employee
     * @param get the employeeDetail
     */
    private List<Address> putAddress(Employee employeeDetail) {
	List<Address> addresses = new ArrayList<Address>();
        System.out.println("Address details");
        String wantMoreAddress;   
        do {
            Address address = new Address();
            System.out.println("Enter Street Name");
            address.setStreetName(inputReader.next());
            System.out.println("Enter Area Name");
            address.setAreaName(inputReader.next());
            System.out.println("Enter city name");
            address.setCityName(inputReader.next());
            address.setPinCode(putPincode()); 
            addresses.add(address);
            System.out.println("If you want to add another address : yes/no");
            wantMoreAddress = inputReader.next();   
        } while(wantMoreAddress.equals("yes"));
	return addresses;
    } 
    
    /**
     * Method used to update the address of employee
     * @param get the updating detail of employee
     */
    private void updateEmployeeAddress(Employee employeeDetail) {
        System.out.println("Address details");
        String wantMoreAddress;   
        do {
            Address address = new Address();
            System.out.println("Enter the id");
            address.setId(inputReader.next());
            System.out.println("Enter Street Name");
            address.setStreetName(inputReader.next());
            System.out.println("Enter Area Name");
            address.setAreaName(inputReader.next());
            System.out.println("Enter city name");
            address.setCityName(inputReader.next());
            address.setPinCode(putPincode()); 
            employeeService.addAddress(employeeDetail,address);
            System.out.println("If you want to add another address : yes/no");
            wantMoreAddress = inputReader.next();   
        } while(wantMoreAddress.equals("yes"));
    } 
}
   
