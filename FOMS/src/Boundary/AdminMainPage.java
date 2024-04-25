package Boundary;

import Entity.Order.Payment;
import Entity.User.Admin;
import Entity.User.EmployeeType;

import java.io.IOException;
import java.util.*;

import Boundary.Account.AttributeGetter;
import Controller.Request.AdminController;

/**
 * The AdminMainPage provides an interface for Admins to allow them do
 * administration tasks, such asa dding and removing staff, editing staff
 * details, assigning manager to branch, transfering staff, adding payment
 * method, opening and closing branch, displaying staff list with filter and
 * sort. It interacts with AdminController class to be able to do all this tasks
 * and AttributeGetter to get user input.
 * 
 * @author Reuben Farrel
 */

public class AdminMainPage {
	static Scanner scanner = new Scanner(System.in);
	private static AdminController adminController;
	private String userID;

	/**
	 * Constructs an AdminMainPage object with the specified user ID.
	 * 
	 * @param userID The user ID of the administrator.
	 */
	// Constructor
	public AdminMainPage(String userID) {
		this.userID = userID;
		AdminController adminController = new AdminController();
	}



	/**
	 * Displays the main menu for the admin user interface and handles user input.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	public void displayAdminMainPage() throws IOException {
		int choice;
		do {
			System.out.println("Welcome, Admin " + userID);
			System.out.println("Hello, Admin!\n");
			System.out.println("1. Add Staff.");
			System.out.println("2. Edit Staff Information.");
			System.out.println("3. Remove Staff.");
			System.out.println("4. Assign Manager to Branch.");
			System.out.println("5. Promote Staff to Manager.");
			System.out.println("6. Transfer User to Branch.");
			System.out.println("7. Add Payment Method.");
			System.out.println("8. Open Branch.");
			System.out.println("9. Close Branch.");
			System.out.println("10. Display Staff List.");
			System.out.println("0. Exit.");
			System.out.print("Please enter your choice (from 0 to 10): ");
			choice = scanner.nextInt();
			System.out.println("USER CHOICE " + choice);
			scanner.nextLine();

			switch (choice) {
				case 1:
					addStaff();
					break;
				case 2:
					editStaff();
					break;
				case 3:
					removeStaff();
					break;
				case 4:
					System.out.println("option 4");
					assignManagerToBranch();
					break;
				case 5:
					promoteToBranchManager();
					break;
				case 6:
					transferUserToBranch();
					break;
				case 7:
					addPaymentMethod();
					break;
				case 8:
					openBranch();
					break;
				case 9:
					closeBranch();
					break;
				case 10:
					displayStaffList();
					break;
				case 0:
					System.out.println("Exiting Admin Main Page...");
					break;
				default:
					System.out.println("Invalid choice. Please enter a number between 0 and 11.");
			}
		} while (choice != 0);
	}

	/**
	 * Adds a new staff member based on Admin's input.
	 */
	private static void addStaff() {
		EmployeeType role;
		System.out.print("Enter role (S for Staff, M for Manager, A for Admin): ");
		String roleStr = scanner.nextLine();
		role = EmployeeType.valueOf(roleStr.toUpperCase());

		String name = AttributeGetter.getName();
		String loginID = AttributeGetter.getUserID();
		String gender = AttributeGetter.getGender();
		String age = AttributeGetter.getAge();
		String branch = "";
		try {
			branch = AttributeGetter.getBranch();
		} catch (IOException e) {
			System.out.println("Error getting branch: " + e.getMessage());
			return;
		}
		String password = AttributeGetter.getPassword();

		boolean added = AdminController.addStaff(name, loginID, role, gender, age, branch, password);
		if (added) {
			System.out.println("Staff added successfully.");
		} else {
			System.out.println("Failed to add staff.");
		}
	}

	/**
	 * Edits the information of an existing staff member.
	 */
	private static void editStaff() {
		String loginID = scanner.nextLine();
		String newName = AttributeGetter.getName();
		String newAge = AttributeGetter.getAge();
		String newGender = AttributeGetter.getGender();
		EmployeeType newRole = AttributeGetter.getEmpType();
		String newBranch = "";
		try {
			newBranch = AttributeGetter.getBranch();
		} catch (IOException e) {
			System.out.println("Error getting branch: " + e.getMessage());
			return;
		}

		AdminController.editStaff(loginID, newName, newAge, newGender, newRole, newBranch);
	}

	/**
	 * Removes a staff member based on Admin's input.
	 */
	private static void removeStaff() {
		String userId = AttributeGetter.getUserID();

		AdminController.removeStaff(userId);
	}

	/**
	 * Assigns a manager to a branch based on Admin's input.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	private void assignManagerToBranch() throws IOException {

		String managerId = AttributeGetter.getUserID();
		System.out.println("In admin main page " +managerId);
		String branchName = AttributeGetter.getBranch();

		System.out.println("In admin main page " +branchName);
		AdminController.assignManagerToBranch(managerId, branchName);
	}

	/**
	 * Promotes a staff member to a branch manager based on Admin's input.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	private void promoteToBranchManager() throws IOException {
		scanner.nextLine();

		System.out.println("Enter the ID of the staff member to promote:");
		String staffId = AttributeGetter.getUserID();
		System.out.println("Enter the name of the branch:");
		String branchName = AttributeGetter.getBranch();
		System.out.println("Enter the role of the promoted manager (STAFF/MANAGER/ADMIN):");
		EmployeeType managerRole = AttributeGetter.getEmpType();

		AdminController.promoteStaffToManager(staffId, branchName, managerRole);
	}

	/**
	 * Transfers a user to a different branch based on Admin's input.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	private void transferUserToBranch() throws IOException {
		System.out.println("Enter the ID of the user to transfer:");
		String userId = AttributeGetter.getUserID();

		System.out.println("Enter the name of the new branch:");
		String newBranchName = AttributeGetter.getBranch();

		System.out.println("Enter the name of the current branch:");
		String currentBranchName = AttributeGetter.getBranch();

		AdminController.transferUserToBranch(userId, newBranchName, currentBranchName);
	}

	/**
	 * Adds a new payment method based on Admin's input.
	 */
	private static void addPaymentMethod() {
		scanner.nextLine();

		System.out.print("Enter the payment method name: ");
		String paymentMethod = scanner.nextLine();

		System.out.print("Enter the fully qualified class name of the Payment implementation: ");
		String paymentClassName = scanner.nextLine();

		try {
			@SuppressWarnings("unchecked")
			Class<? extends Payment> paymentClass = (Class<? extends Payment>) Class.forName(paymentClassName);
			AdminController.addPaymentMethod(paymentMethod, paymentClass);
			System.out.println("Payment method added successfully.");
		} catch (ClassNotFoundException e) {
			System.out.println("Invalid class name. Payment method not added.");
		}
	}

	/**
	 * Opens a new branch based on Admin's input.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	private void openBranch() throws IOException {
		scanner.nextLine(); // Clear the newline character from the buffer

		System.out.println("Enter the name of the branch to open:");
		String branchName = AttributeGetter.getBranch();

		AdminController.openBranch(branchName);
	}

	/**
	 * Closes an existing branch based on Admin's input.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	private void closeBranch() throws IOException {
		scanner.nextLine();

		System.out.println("Enter the name of the branch to close:");
		String branchName = AttributeGetter.getBranch();
		AdminController.closeBranch(branchName);
	}

	/**
	 * Displays the list of staff members based on Admin-defined criteria.
	 */
	private void displayStaffList() {
		scanner.nextLine();

		System.out.println("===== Display Staff List =====");
		System.out.println("Choose filter criterion:");
		System.out.println("1. Gender");
		System.out.println("2. Branch");
		System.out.println("3. Role");
		System.out.print("Enter your choice: ");
		int filterChoice = scanner.nextInt();
		scanner.nextLine();
		String filterValue = "";
		switch (filterChoice) {
			case 1:
				System.out.print("Enter gender (M/F/All): ");
				filterValue = scanner.nextLine().toUpperCase();
				break;
			case 2:
				System.out.print("Enter branch: ");
				filterValue = scanner.nextLine();
				break;
			case 3:
				System.out.print("Enter role (STAFF/MANAGER/ADMIN/All): ");
				filterValue = scanner.nextLine().toUpperCase();
				break;
			default:
				System.out.println("Invalid choice.");
				return;
		}

		System.out.print("Sort by age? (true/false): ");
		boolean sortByAge = scanner.nextBoolean();

		AdminController.displayStaffList(filterChoice, filterValue, sortByAge);
	}
}
