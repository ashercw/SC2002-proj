package Controller.Account.Password;

import Entity.User.EmployeeType;
import Entity.User.User;

/**
 * Checks the user credentials for login purposes.
 * @author Saffron Lim
 */
public class CredentialsValidator {

    /**
     * System requirements for new password.
     */
    private static String MIN_LENGTH = "8";
    private static String MAX_LENGTH = "20";
    private static boolean SPECIAL_CHAR_NEEDED = false;

    private static String ONE_DIGIT = "(?=.*[0-9])";
    private static String LOWER_CASE = "(?=.*[a-z])";
    private static String UPPER_CASE = "(?=.*[A-Z])";
    private static String SPECIAL_CHAR = SPECIAL_CHAR_NEEDED ? "(?=.*[@#$%^&+=])" : "";
    private static String NO_SPACE = "(?=\\S+$)";

    private static String MIN_MAX_CHAR = ".{" + MIN_LENGTH + "," + MAX_LENGTH + "}";
    private static String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;

    //default password
    private static String DEFAULT = "password";

    /**
     * Checks whether the employee credentials (EmployeeType and LoginID) matches the content of the database.
     * @param emp User object containing data of employee who wants to login
     * @param empType EmployeeType (S, A, M ) of employee who wants to login
     * @param inputUserID String representing the employee's userID.
     * @return boolean (true if credentials matches, false otherwise)
     * 
     */
    public static boolean checkUserValidity(User emp, EmployeeType empType, String inputUserID)
    {
        boolean isIDCorrect = false;
		boolean isUserTypeCorrect = false;
        //System.out.println("in credentials validator, " + emp.getLoginID() + emp.getEmployeeType() + inputUserID + empType);
        isIDCorrect = emp.getLoginID().equals(inputUserID);
		isUserTypeCorrect = emp.getEmployeeType() == empType;
        if(isIDCorrect && isUserTypeCorrect) return true;
        else return false;
    }

    /**
     * Checks whether the password entered by the employee matches the one in the
     * database.
     * @param emp User object of the employee trying to login.
     * @param inputPassw String password entered by the employee trying to login.
     * @return boolean (true if password matches, false otherwise).
     * 
     */
    public static boolean checkPassW(User emp, String inputPassw)
    {
        return emp.getPassword().equals(inputPassw);
    }

    /**
     * Checks if the password entered by the employee is the default password.
     * @param inputPassw String password entered by the employee trying to login.
     * @return boolean (true if password is default, false otherwise).
     * 
     */
    public static boolean isDefaultPassW(String inputPassw)
    {
        if(inputPassw.equals(DEFAULT)) return true;
        else return false;
    }

    /**
     * Checks if the new password entered by the employee matches the system requirements (PATTERN).
     * @param inputPassw String new password entered by the employee trying to login.
     * @return boolean (true if new password is valid, false otherwise).
     * 
     */
    public static boolean newPasswordValidator(String inputPassw)
    {
        try{
            if(inputPassw != null && inputPassw != "")
            {
                if(inputPassw.matches(PATTERN)) return true;
                else
                {
                    System.out.println("Password does not match the requirements! Try again.");
                }
                return false;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

}
