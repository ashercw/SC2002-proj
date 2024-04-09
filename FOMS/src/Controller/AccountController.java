package Controller;

import Entity.User.*;
import java.util.List;
import java.util.ArrayList;
import Others.IO;
import Boundary.AttributeGetter;

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
	 * Loads employee list from staff list CSV file. Additionally initialises the
	 * respective User entity objects
	 */

	public static void loadEmployees() {
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
				addToEmployeeRepo(staffObj);

			} else if (role.equals("M")) {
				branch = str.get(5);
				User managerObj = new Manager(name, userID, EmployeeType.S, gender, age, branch, "password");
				// add to repo
				System.out.println("writing to repo");
				addToEmployeeRepo(managerObj);
				
			} else if (role.equals("A")) {
				User adminObj = new Admin(name, userID, EmployeeType.S, gender, age, "password");
				// add to repo
				System.out.println("writing to repo");
				addToEmployeeRepo(adminObj);
			}

		}

	}

	/**
	 * Adds the user object to the EmployeeRepository for data persistence purposes
	 * 
	 * @param user Object of User type (Staff, Admin, Manager)
	 * 
	 */
	// TO-DO: this.
	public static void addToEmployeeRepo(User user) {
		List list = new ArrayList();;
		try {

			// write to serialized file - update/insert/delete
			// add to list
			list.add(user);
			// list.remove(p); // remove if p equals object in the list

			IO.writeSerializedObject("Employee.txt", list);

		} catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
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