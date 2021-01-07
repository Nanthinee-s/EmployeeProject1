package com.ideas2it.employee.model;

/**
 * This POJO class is to set the employee address in Address type
 * and also gets the address in Address type 
 */
public class Address {
    private String streetName;
    private String areaName;
    private String cityName;
    private String pinCode;
    private String id;
    
    /* Using getter and setter method */ 
  
    public String getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }  
    
    public String getAreaName() {
        return areaName;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public String getPinCode() {
        return pinCode;
    }
  
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;    
    } 
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String toString() {
        return (getId() + "\t " + getStreetName() + "\t" + getAreaName() + "\t"
               + getCityName() + "\t" + getPinCode() + "\t");
    }
}
