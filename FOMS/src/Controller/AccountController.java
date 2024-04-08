package Controller;

import java.util.List;
import Others.IO;

public class AccountController {
	// CHANGE DEPENDING ON YOUR SYSTEM
	private final static String FILEPATH = "C:\\Users\\Saffron Lim\\Downloads\\staff_listCSV.csv";

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
	 * Loads employee list from staff list CSV file. Additionally calls the XX function to initialise the
	 * respective entity objects
	 */
	
	public static void loadEmployees() {
		// TODO - implement AccountController.loadUsers
		List<List<String>> empList = IO.readCSV(FILEPATH);

		for (List<String> str : empList) {
			String name = str.get(0);
			String userID = str.get(1);
			String role = str.get(2);
			String gender = str.get(3);
			String age = str.get(4);
			String branch = "";

			System.out.println(str.size());

			if (role.equals("S")) {
				System.out.print("STAFF ");
				branch = str.get(5);
				System.out.println(name + " " + userID + " " + role + " " + gender + " " + age + " " + branch);
			} else if (role.equals("M")) {
				System.out.print("MANAGER ");
				branch = str.get(5);
				System.out.println(name + " " + userID + " " + role + " " + gender + " " + age + " " + branch);
			} else if (role.equals("A")) {
				System.out.print("ADMIN ");
				System.out.println(name + " " + userID + " " + role + " " + gender + " " + age + " " + branch);
			}

		}

	}

	

	/**
	 * 
	 * @param EmployeeType
	 * @param String
	 */

	// public STAFF login(int EmployeeType, int String), deleted staff coz throwing
	// error.
	public void login(int EmployeeType, int String) {
		// TODO - implement AccountController.login
		throw new UnsupportedOperationException();
	}

}