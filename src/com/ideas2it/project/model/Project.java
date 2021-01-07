package com.ideas2it.project.model;

import java.util.List;
import java.util.ArrayList;

/**
 * This class Project is used to set and get the project detail and its value
 */
public class Project {  
    private String projectId;
    private String projectName;
    private String projectBudget;
    private String projectTimeFrame;
    private String projectDescription;
    private boolean status;

    /* Using getter and setter method */ 
      
    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectBudget() {
        return projectBudget;
    }

    public String getProjectTimeFrame() {
        return projectTimeFrame;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public boolean getStatus() {
        return status;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectBudget(String projectBudget) {
        this.projectBudget = projectBudget;
    }

    public void setProjectTimeFrame(String projectTimeFrame) {
        this.projectTimeFrame = projectTimeFrame;
    }
    
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
   
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
       return (getProjectId() + "\t" + getProjectName() + "\t"
        + getProjectBudget() + "\t" + getProjectTimeFrame() + "\t" 
        + getProjectDescription() + "\t" + getStatus() + "\t");     
    }
}
