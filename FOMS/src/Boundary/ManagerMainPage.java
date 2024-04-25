package Boundary;

import java.util.Scanner;
import Entity.Food.ItemType;
import Controller.Request.AdminController;
import Controller.Menu.MenuControllerTemp;

public class ManagerMainPage {
	Scanner scanner = new Scanner(System.in);
	private MenuControllerTemp menuController;
	private String userID;

	public ManagerMainPage(String userID) {
		this.userID = userID;
		this.menuController = new MenuControllerTemp();
	}

	public void displayManagerMainPage() {
		int choice;
		do {
			System.out.println("Welcome, Manager " + userID);
			System.out.println("Hello, Manager!\n");
			System.out.println("1. Display Staff list Based on Branch.");
			System.out.println("2. Add Menu Items.");
			System.out.println("3. Display Orders.");
			System.out.print("Please enter your choice (from 0 to 3): ");
			choice = scanner.nextInt();
			System.out.println("USER CHOICE " + choice);
			scanner.nextLine();
			switch (choice) {
				case 1:
					displayStaffList();
					break;
				case 2:
					AddMenuItems();
					break;
				case 0:
					System.out.println("Exiting Admin Main Page...");
					break;
				default:
					System.out.println("Invalid choice. Please enter a number between 0 and 3.");
			}
		} while (choice != 0);
	}

	private void displayStaffList() {
		System.out.println("===== Display Staff List =====");
		System.out.print("Enter the name of the manager: ");
		@SuppressWarnings("unused")
		String managerName = scanner.nextLine();
		System.out.print("Enter the name of the branch: ");
		String branchName = scanner.nextLine();
		if (branchName == null) {
			System.out.println("No branch found for the specified manager.");
			return;
		}
		AdminController.displayStaffList(2, branchName, false);
	}

	private void AddMenuItems() {
		// Input the details of the new menu item
		// Create a new Menu object
		System.out.print("Enter the name of the new menu item: ");
		String name = scanner.nextLine();

		System.out.print("Enter the price of the new menu item: ");
		double price = scanner.nextDouble();

		scanner.nextLine(); // Consume newline character

		System.out.print("Enter the branch of the new menu item: ");
		String branch = scanner.nextLine();

		System.out.print("Enter the category of the new menu item (BURGER/SIDE/SETMEAL/DRINK): ");
		String categoryStr = scanner.nextLine().toUpperCase();
		ItemType category = ItemType.valueOf(categoryStr);
		boolean added = menuController.addMenuItem(name, price, branch, category);
		if (added) {
			System.out.println("New menu item added successfully.");
		} else {
			System.out.println("Failed to add the new menu item. It may already exist.");
		}
	}
}
