package Controller.Account;

import Entity.User.*;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDBStaff;
import Boundary.AttributeGetter;
import Boundary.LogOutUI;
import Boundary.StaffMainPage;
import Controller.Request.BranchController;
import Controller.Account.Password.*;;

@SuppressWarnings({"rawtypes", "unchecked"})
public class AccountController {
	// CHANGE DEPENDING ON YOUR SYSTEM C:\\Users\\Saffron
	// Lim\\Downloads\\staff_listCSV.csv
	private final static String FILEPATH = "FOMS\\src\\Others\\staff_listCSV.csv";

	public AccountController() {
		// TODO - implement AccountController.AccountController
	}

	/**
	 * Loads employee list from staff list CSV file. Additionally initialises the
	 * respective User entity objects
	 */


	public static void loadEmployees() {
		List al = new ArrayList();

		List<List<String>> empList = IO.readCSV(FILEPATH);

		for (List<String> str : empList) {

			String role = str.get(2);
			al.add(createEmployeeObj(str, role));

		}
		addToEmployeeRepo("EmployeeRepo.txt", al);
		BranchController.createStaffListbyBranch(empList);

	}

	/**
	 * Creates a User object.
	 * 
	 * @param str  List of Strings containing information about a single employee
	 *             entity (staff, manager, admin).
	 * @param role is a String representing the role of an employee (S, A, M).
	 * @return a User object.
	 * 
	 */
	public static User createEmployeeObj(List<String> str, String role) {
		String name = str.get(0);
		String userID = str.get(1);
		String gender = str.get(3);
		String age = str.get(4);
		String branch = "";
		User uObj = new User();

		if (role.equals("S")) {
			branch = str.get(5);
			uObj = new Staff(name, userID, EmployeeType.S, gender, age, branch, "password");

		} else if (role.equals("M")) {
			branch = str.get(5);
			uObj = new Manager(name, userID, EmployeeType.M, gender, age, branch, "password");

		} else if (role.equals("A")) {
			uObj = new Admin(name, userID, EmployeeType.A, gender, age, "password");
		}
		return uObj;
	}

	/**
	 * This function writes employee data to a txt file for data storage purposes.
	 * 
	 * @param FILEPATH is the String directory of the file to write to.
	 * @param al       is a List of various Entity objects (i.e., Staff objects,
	 *                 Admin objects, and Manager objects) whose content will be
	 *                 saved in the txt file.
	 * 
	 */
	public static void addToEmployeeRepo(String FILEPATH, List al) {
		try {

			TextDBStaff.saveEmployee(FILEPATH, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
	 * This function allows the employee user to login by calling other functions. 
	 * The user has a limit of 3 login attempts, after which the user will be automatically logged out of FOMS. 
	 * @param employeeType EmployeeType of the user trying to login (STAFF, ADMIN, MANAGER).
	 * 
	 */

	public static void login(EmployeeType employeeType) {
		// TODO - CHANGE PASSWORD OPTION

		int loginResult = -100;
		int numTries = 0;
		String userID = AttributeGetter.getUserID();
		String password = AttributeGetter.getPassword();

		while (loginResult != 1 && numTries < 2) {
			//check existence of user, and if password and userID can be found in EmployeeRepo
			loginResult = PasswordController.checkCredentials(employeeType, password, userID);
			// login successful
			if (loginResult == 1) 
			{
				if(password.equals("password"))
				{
					//call change password

					//once successful, return back to main page.
					return;
				}
				System.out.println("Success! Welcome " + userID + "!");
				//GO TO STAFF MAIN PAGE
				if (employeeType == EmployeeType.S) { 
					new StaffMainPage(userID).displayStaffMainPage();
					//return to FOMS
					return;
				}
				//GO TO MANAGER MAIN PAGE
				else if (employeeType == EmployeeType.M) {
					//call manager main page

					//return to FOMS
					return;
				}
				//GO TO ADMIN MAIN PAGE
				else if (employeeType == EmployeeType.A) {
					//call admin main page
					
					//return to FOMS
					return;
				}
			} 
			// wrong password
			else if (loginResult == 0) 
			{
				System.out.println("Wrong password! Try again.");
				numTries++;
			} 
			// user not found
			else if (loginResult == -1) 
			{
				System.out.println("User does not exist! Try again.");
				numTries++;
			}
			userID = AttributeGetter.getUserID();
			password = AttributeGetter.getPassword();
		}

		if(numTries >= 2)
		{
			System.out.println("\n\n\nYou have exceeded the number of login attempts. Goodbye.\n\n\n");
			LogOutUI.LogOut();
			
		}

	}

}