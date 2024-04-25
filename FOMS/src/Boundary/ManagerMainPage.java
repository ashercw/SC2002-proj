package Boundary;

import java.util.Scanner;
import Entity.Food.ItemType;
import Controller.Request.AdminController;
import Controller.Menu.MenuControllerTemp;

public class ManagerMainPage {
	private Scanner scanner = new Scanner(System.in);
	private MenuControllerTemp menuController;
	private String userID;

	public ManagerMainPage(String userID) {
		this.userID = userID;
		this.menuController = new MenuControllerTemp();
	}

	public void displayManagerMainPage() {
		int choice;
		do {
			System.out.println("\nWelcome, Manager " + userID);
			System.out.println("1. Display Staff list Based on " + userID + " Branch");
			System.out.println("2. Add Menu Items.");
			System.out.println("3. Edit Menu Items");
			System.out.println("4. Remove Menu Items");
			System.out.println("5. Display Orders.");
			System.out.print("Please enter your choice (0 to exit): ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline character after nextInt()

			switch (choice) {
				case 1:
					displayStaffList();
					break;
				case 2:
					AddMenuItems();
					break;
				case 3:
					EditMenuItems();
					break;
				case 4:
					RemoveMenuitems();
					break;
				case 5:
					// DisplayOrders(); // Define this method if needed.
					break;
				case 0:
					System.out.println("Exiting Admin Main Page...");
					break;
				default:
					System.out.println("Invalid choice. Please enter a number between 0 and 5.");
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
		try {
			// Input the details of the new menu item
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
		} catch (Exception e) {
			System.out.println("Error occurred while adding the menu item: " + e.getMessage());
		}
	}

	public void EditMenuItems() {
		try {
			// Input the details of the menu item to be edited
			System.out.print("Enter the name of the menu item to edit: ");
			String name = scanner.nextLine();

			System.out.print("Enter the new price of the menu item: ");
			double newPrice = scanner.nextDouble();
			scanner.nextLine();

			System.out.print("Enter the new description of the menu item: ");
			String newDescription = scanner.nextLine();

			System.out.print("Enter the branch of the menu item: ");
			String branch = scanner.nextLine();

			boolean updated = menuController.updateMenuItem(name, newPrice, newDescription, branch);
			if (updated) {
				System.out.println("Menu item updated successfully.");
			} else {
				System.out.println("Failed to update the menu item. It may not exist or the branch is invalid.");
			}
		} catch (Exception e) {
			System.out.println("Error occurred while updating the menu item: " + e.getMessage());
		}
	}

	public void RemoveMenuitems() {
		try {
			// Input the details of the menu item to be removed
			System.out.print("Enter the name of the menu item to remove: ");
			String name = scanner.nextLine();

			System.out.print("Enter the branch of the menu item: ");
			String branch = scanner.nextLine();

			boolean removed = menuController.removeMenuItem(name, branch);
			if (removed) {
				System.out.println("Menu item removed successfully.");
			} else {
				System.out.println("Failed to remove the menu item. It may not exist or the branch is invalid.");
			}
		} catch (Exception e) {
			System.out.println("Error occurred while removing the menu item: " + e.getMessage());
		}
	}

}
