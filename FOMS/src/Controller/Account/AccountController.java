package Controller.Account;

import Entity.User.*;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDB;
import Boundary.AttributeGetter;

public class AccountController {
	// CHANGE DEPENDING ON YOUR SYSTEM  C:\\Users\\Saffron Lim\\Downloads\\staff_listCSV.csv
	private final static String FILEPATH = "FOMS\\src\\Others\\staff_listCSV.csv";

	public AccountController() {
		// TODO - implement AccountController.AccountController
	}

	/**
	 * 
	 * @param EmployeeType
	 * @param String
	 */
	public void changePassword(int EmployeeType, int String) {
		// TODO - implement AccountController.changePassword
	}

	/**
	 * 
	 * @param String
	 */
	public String getID(int String) {
		// TODO - implement AccountController.getID
		String temp = "temp"; // placehodler
		return temp;
	}

	/**
	 * Loads employee list from staff list CSV file. Additionally initialises the
	 * respective User entity objects
	 */

	public static void loadEmployees() {
		List al = new ArrayList();
        
		List<List<String>> empList = IO.readCSV(FILEPATH);

		for (List<String> str : empList) {
			String name = str.get(0);
			String userID = str.get(1);
			String role = str.get(2);
			String gender = str.get(3);
			String age = str.get(4);
			String branch = "";

			if (role.equals("S")) {
				branch = str.get(5);
				User staffObj = new Staff(name, userID, EmployeeType.S, gender, age, branch, "password");
				// add to repo
				System.out.println("writing to repo");
				al.add(staffObj);

			} else if (role.equals("M")) {
				branch = str.get(5);
				User managerObj = new Manager(name, userID, EmployeeType.M, gender, age, branch, "password");
				// add to repo
				System.out.println("writing to repo");
				al.add(managerObj);
				
			} else if (role.equals("A")) {
				User adminObj = new Admin(name, userID, EmployeeType.A, gender, age, "password");
				// add to repo
				System.out.println("writing to repo");
				al.add(adminObj);

			}

		}
		addToEmployeeRepo("EmployeeRepo.txt", al);

	}

	/**
	 * This function writes employee data to a txt file for data storage purposes.
	 * @param FILEPATH is the String directory of the file to write to.
	 * @param al is a List of various Entity objects (i.e., Staff objects, 
     * Admin objects, and Manager objects) whose content will be saved in the txt file.
	 * 
	 */
	public static void addToEmployeeRepo(String FILEPATH, List al) {
		TextDB txtDB = new TextDB();
		try {
			
			TextDB.saveEmployee(FILEPATH, al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
	 * Handles the login of employees.
	 * 
	 * @param EmployeeType
	 */

	// public STAFF login(int EmployeeType, int String), deleted staff coz throwing
	// error.
	public static void login(EmployeeType EmployeeType) {
		// TODO - implement AccountController.login

		String userID = AttributeGetter.getUserID();
		String password = AttributeGetter.getPassword();
		System.out.println("Success!"); // temp

		// FIND USER
		// CHECK PASSWORD
		// CHANGE PASSWORD IF NECESSARY

	}

}