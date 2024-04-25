package Boundary;

import Controller.Request.AdminController;
import Entity.Order.Payment;
import Entity.User.EmployeeType;
import java.util.*;

/**
 * @author Reuben Farrel
 */

public class AdminMainPage {
	static Scanner scanner = new Scanner(System.in);
	private static AdminController adminController;

	// Constructor
	public AdminMainPage(AdminController adminController) {
		AdminMainPage.adminController = adminController;
	}

	public void displayAdminMainPage() {
		int choice;
		do {
			System.out.println("Welcome to Admin Main Page");
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

	private static void addStaff() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter age: ");
		String age = scanner.nextLine();
		System.out.print("Enter gender: ");
		String gender = scanner.nextLine();
		System.out.print("Enter branch: ");
		String branch = scanner.nextLine();
		System.out.print("Enter role: ");
		String roleStr = scanner.nextLine();
		EmployeeType role = EmployeeType.valueOf(roleStr.toUpperCase());

		boolean added = adminController.addStaff(username, password, name, age, gender, branch, role);
		if (added) {
			System.out.println("Staff added successfully.");
		} else {
			System.out.println("Failed to add staff.");
		}
	}

	private static void editStaff() {
		System.out.print("Enter user ID: ");
		String userId = scanner.nextLine();
		System.out.print("Enter new name: ");
		String newName = scanner.nextLine();
		System.out.print("Enter new age: ");
		String newAge = scanner.nextLine();
		System.out.print("Enter new gender: ");
		String newGender = scanner.nextLine();
		System.out.print("Enter new role: ");
		String newRoleStr = scanner.nextLine();
		EmployeeType newRole = EmployeeType.valueOf(newRoleStr.toUpperCase());
		System.out.print("Enter new branch: ");
		String newBranch = scanner.nextLine();

		adminController.editStaff(userId, newName, newAge, newGender, newRole, newBranch);
	}

	private static void removeStaff() {
		System.out.print("Enter user ID: ");
		String userId = scanner.nextLine();

		adminController.removeStaff(userId);
	}

	private void assignManagerToBranch() {
		System.out.println("Enter the ID of the manager:");
		String managerId = scanner.next();
		System.out.println("Enter the name of the branch:");
		String branchName = scanner.next();
		System.out.println("Enter the type of the branch:");
		String branchType = scanner.next();

		adminController.assignManagerToBranch(managerId, branchName, branchType);
	}

	private void promoteToBranchManager() {
		System.out.println("Enter the ID of the staff member to promote:");
		String staffId = scanner.next();
		System.out.println("Enter the name of the branch:");
		String branchName = scanner.next();
		System.out.println("Enter the role of the promoted manager (STAFF/MANAGER/ADMIN):");
		String managerRoleStr = scanner.next();
		EmployeeType managerRole = EmployeeType.valueOf(managerRoleStr.toUpperCase());

		adminController.promoteStaffToManager(staffId, branchName, managerRole);
	}

	private void transferUserToBranch() {
		System.out.println("Enter the ID of the user to transfer:");
		String userId = scanner.next();

		System.out.println("Enter the name of the new branch:");
		String newBranchName = scanner.next();

		System.out.println("Enter the name of the current branch:");
		String currentBranchName = scanner.next();

		adminController.transferUserToBranch(userId, newBranchName, currentBranchName);
	}

	private static void addPaymentMethod() {
		System.out.print("Enter the payment method name: ");
		String paymentMethod = scanner.nextLine();

		System.out.print("Enter the fully qualified class name of the Payment implementation: ");
		String paymentClassName = scanner.nextLine();

		try {
			@SuppressWarnings("unchecked")
			Class<? extends Payment> paymentClass = (Class<? extends Payment>) Class.forName(paymentClassName);
			adminController.addPaymentMethod(paymentMethod, paymentClass);
			System.out.println("Payment method added successfully.");
		} catch (ClassNotFoundException e) {
			System.out.println("Invalid class name. Payment method not added.");
		}
	}

	private void openBranch() {
		System.out.println("Enter the name of the branch to open:");
		String branchName = scanner.next();
		adminController.openBranch(branchName);
	}

	private void closeBranch() {
		System.out.println("Enter the name of the branch to close:");
		String branchName = scanner.next();
		adminController.closeBranch(branchName);
	}

	private void displayStaffList() {
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

		adminController.displayStaffList(filterChoice, filterValue, sortByAge);
	}
}
