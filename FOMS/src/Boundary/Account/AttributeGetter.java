package Boundary.Account;

import java.io.IOException;
import java.util.ArrayList;

import Entity.User.EmployeeType;
import Others.IO;
import Others.TextDBBranch;

/**
 * Accepts user input to retrieve respective attributes.
 */
public class AttributeGetter {

	/**
	 * Retrieves the type of employee corresponding to the user input:
	 * 1: Staff (S)
	 * 2: Admin (A)
	 * 3: Manager (M)
	 * 
	 * @return EmployeeType (S, A, M)
	 * 
	 */
	public static EmployeeType getEmployeeType() {
		while (true) {
			System.out.println("\nPlease select your domain (1-3)");
			int userInput = IO.userInputInt();
			if (userInput == 1) {
				System.out.println("Logging in as Staff:");
				return EmployeeType.S;
			} else if (userInput == 2) {
				System.out.println("Logging in as Manager:");
				return EmployeeType.M;
			} else if (userInput == 3) {
				System.out.println("Logging in as Admin:");
				return EmployeeType.A;
			} else if (userInput == 4) {
				System.out.println("\nExiting page...\n\n\n");
				return null;
			} else {
				System.out.println("\nPlease enter an integer from 1-4");
			}
		}
	}

	/**
	 * Gets and returns the employee's userID by calling IO.userInpuString();.
	 * 
	 * @return String (user's userID)
	 * 
	 */
	public static String getUserID() {
		// IO.userInpuString();

		System.out.println("Please enter your userID:");
		String userName = IO.userInpuString();
		return userName;
	}

	/**
	 * Gets and returns the employee's password by calling IO.userInpuString();.
	 * 
	 * @return String (user's password)
	 * 
	 */
	public static String getPassword() {
		System.out.println("Please enter your password:");
		String password = IO.userInpuString();
		return password;
	}

	/**
	 * Gets and returns the employee's branch by calling IO.userInpuString();.
	 * 
	 * @return String (user's password)
	 * @throws IOException
	 * 
	 */
	public static String getBranch() throws IOException {
		@SuppressWarnings("unchecked")
		// get full string of lists
		ArrayList<String> branchL = TextDBBranch.readBranch("BranchRepo.txt", false);
		while (true) {
			System.out.print("Please enter your branch (");
			for (String i : branchL) { // print existing branch codes
				System.out.print(i + ", ");
			}
			System.out.println("NA): "); // for admin
			String branch = IO.userInpuString();
			// check if branch exists
			if (branchL.contains(branch) || branch.equals("NA"))
				return branch;
			else
				System.out.println("Branch does not exist. Please try again");
		}
	}

	/**
     * Gets and returns the gender of the employee from user input.
     * 
     * @return String representing the user's gender.
     */

	public static String getGender() {
		System.out.println("Please enter gender:");
		String gender = IO.userInpuString();
		return gender;
	}

	/**
     * Gets and returns the age of the employee from user input.
     * 
     * @return String representing the user's age.
     */

	public static String getAge() {
		System.out.println("Please enter age:");
		String age = IO.userInpuString();
		return age;
	}

    /**
     * Gets and returns the name of the employee from user input.
     * 
     * @return String representing the user's name.
     */

	public static String getName() {
		System.out.println("Please enter name:");
		String name = IO.userInpuString();
		return name;
	}

	/**
     * Gets and returns the employee type (S, M, A) from user input, or null if the user presses Enter without inputting anything.
     * This allows the user to retain the current role if no new input is provided.
     * 
     * @return EmployeeType representing the employee type, or null to retain the current role.
     */
	
	public static EmployeeType getEmpType() {
		System.out.println(
				"Please enter role (S for Staff, M for Manager, A for Admin) (or press Enter to keep the current role): ");
		String roleStr = IO.userInpuString();
		if (roleStr.isEmpty()) {
			return null;
		} else {
			return EmployeeType.valueOf(roleStr.toUpperCase());
		}
	}

}