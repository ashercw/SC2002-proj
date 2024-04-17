package Controller.Account.Password;

import Entity.User.EmployeeType;
import Entity.User.User;

/**
 * Checks the user credentials for login purposes.
 * @author Saffron Lim
 */
public class CredentialsValidator {

    private static String MIN_LENGTH = "12";
    private static String MAX_LENGTH = "20";
    private static boolean SPECIAL_CHAR_NEEDED = false;

    private static String ONE_DIGIT = "(?=.*[0-9])";
    private static String LOWER_CASE = "(?=.*[a-z])";
    private static String UPPER_CASE = "(?=.*[A-Z])";
    private static String SPECIAL_CHAR = SPECIAL_CHAR_NEEDED ? "(?=.*[@#$%^&+=])" : "";
    private static String NO_SPACE = "(?=\\S+$)";

    private static String MIN_MAX_CHAR = ".{" + MIN_LENGTH + "," + MAX_LENGTH + "}";
    private static String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;

    private static String DEFAULT = "password";

    /**
     * 
     * @param emp User object containing data of user who wants to login
     * @param empType EmployeeType (S, A, M ) of user who wants to login
     * @param inputUserID String, is the user's userID.
     * @return true or false
     * 
     */
    public static boolean checkUserValidity(User emp, EmployeeType empType, String inputUserID)
    {
        boolean isIDCorrect = false;
		boolean isUserTypeCorrect = false;

        isIDCorrect = emp.getLoginID().equals(inputUserID);
		isUserTypeCorrect = emp.getEmployeeType() == empType;
        if(isIDCorrect && isUserTypeCorrect) return true;
        else return false;
    }

    public static boolean checkPassW(User emp, String inputPassw)
    {
        return emp.getPassword().equals(inputPassw);
    }

    public static boolean isDefaultPassW(String inputPassw)
    {
        if(inputPassw.equals(DEFAULT)) return true;
        else return false;
    }

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
