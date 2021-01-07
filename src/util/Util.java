package util;

public class Util {
 
    /** 
     * Method is used to check the Mobile Number pattern 
     * is must be present in the given format or not 
     * @param it take a mobile number in string type
     * @return boolean value true and false
     */   
    public boolean validateNumber(String mobileNumber) {
        String pattern = "^[6-9]{1}[0-9]{9}$";
        return mobileNumber.matches(pattern);
    }
    
    /**
     * This boolean method is used to check the pincode pattern 
     * is must be present in the given format or not
     * @return this method return boolean value true and false
     */  
    public boolean validatePinCode(String pinCode) {
        String pattern = "^[0-9]{6}$";
        return pinCode.matches(pattern);
    }

    /**
     * This method is used to validate the Email_id 
     * is must be present in the following format or not 
     * @ return boolean value if its  valid it return true or else return false
     */     
    public boolean isEmailIdValid(String emailId) {
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
                         +"@[A-za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return emailId.matches(pattern);
    }      
}
