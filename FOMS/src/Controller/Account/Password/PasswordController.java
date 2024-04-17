package Controller.Account.Password;

import java.io.IOException;
import java.util.ArrayList;

import Boundary.Account.AttributeGetter;
import Boundary.Account.ChangePassword;
import Others.IO;
import Others.TextDBStaff;
import Entity.User.EmployeeType;
import Entity.User.User;

/**
 * This class manages functions related to passwords and login credentials.
 * @author Christian Asher, Saffron Lim
 */

@SuppressWarnings("rawtypes")
public class PasswordController {



	/**
	 * This function checks the login credentials of the user such as whether information like 
	 * the EmployeeType, userID, and password corresponds to data stored in the EmployeeRepo.
	 * @param empType EmployeeType (STAFF, ADMIN, MANAGER).
	 * @param inputPassw String representing the user's password.
	 * @param inputUserID String representing the user's userID.
	 * @return int with a value of -1, 0, or 1 (-1: User does not exist, 0: password entered is wrong, 
	 * 1: login is successful).
	 */
	public static int checkCredentials(EmployeeType empType, String inputPassw, String inputUserID) {
		boolean isUserValid = false;

		try {
			ArrayList employeeList = TextDBStaff.readEmployee("EmployeeRepo.txt");
			User emp = new User();

			for (int i = 0; i < employeeList.size(); i++) {
				emp = (User)employeeList.get(i);
				isUserValid = CredentialsValidator.checkUserValidity(emp, empType, inputUserID);
				if(isUserValid)
				{
					if(CredentialsValidator.checkPassW(emp, inputPassw)) return 1; //successful login
					else return 0; //wrong password
				}
			}
			return -1; //user not found
			

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return -1; //user not found
	}

	/**
	 * 
	 * @param User
	 * @param String
	 */
	public static void changePassword(EmployeeType employeeType, String userID) {
		boolean isValid = false;
		IO.printNewLine(5);

		while(isValid == false)
		{	
			// display menu to change password
			ChangePassword.dispChangePassword();
			String newPassword = AttributeGetter.getPassword();
			// check if newPassword is DEFAULT password
			if(CredentialsValidator.isDefaultPassW(newPassword))
			{
				// show error msg
				ChangePassword.dispDefaultPasswordErr();
			}
			else
			{
				if(CredentialsValidator.newPasswordValidator(newPassword))
				{
					ChangePassword.dispSuccess();
					//write to Staff List repos
					return;
				}
				
			}
		}
	}

}