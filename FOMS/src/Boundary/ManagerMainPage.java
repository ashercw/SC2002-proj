/**
 * The ManagerMainPage class represents the main page interface for managers in the system.
 * It allows managers to perform various tasks such as displaying staff lists, adding, editing, and removing menu items.
 * This class interacts with other controllers and entities to perform these operations.
 */

package Boundary;

import java.util.Scanner;

import Entity.Food.ItemType;
import Others.IO;
import Controller.Request.AdminController;
import Controller.Menu.MenuControllerTemp;

/**
 * The ManagerMainPage class provides functionality for managers to interact with the system.
 * It allows managers to perform tasks such as displaying staff lists, adding, editing, and removing menu items.
 */

public class ManagerMainPage {
	private static Scanner scanner = new Scanner(System.in);
	private static MenuControllerTemp menuController;
	private StaffMainPage staffMain;
	private String userID;

	/**
     * Constructs a ManagerMainPage object with the specified userID.
     * Initializes the MenuControllerTemp and StaffMainPage objects.
     * 
     * @param userID The ID of the manager.
     */

	public ManagerMainPage(String userID) {
		this.userID = userID;
		this.menuController = new MenuControllerTemp();
		this.staffMain = new StaffMainPage(userID);
	}

	/**
     * Displays the main page interface for managers.
     * Allows managers to perform various tasks such as displaying staff lists, adding, editing, and removing menu items.
     */

	public void displayManagerMainPage() {
		int choice;
		do {
			System.out.println("\nWelcome, Manager " + userID);
			System.out.println("1. Display Staff list Based on " + userID + " Branch");
			System.out.println("2. Add Menu Items.");
			System.out.println("3. Edit Menu Items");
			System.out.println("4. Remove Menu Items");
			System.out.println("5. Display/Process Orders.");
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
					StaffMainPage.displayStaffMainPage();
					break;
				case 0:
					System.out.println("Exiting Manager Main Page...");
					break;
				default:
					System.out.println("Invalid choice. Please enter a number between 0 and 5.");
			}
		} while (choice != 0);
	}

	/**
     * Displays the list of staff members based on the branch entered by the manager.
     */

	private void displayStaffList() {
		System.out.println("===== Display Staff List =====");
		System.out.print("Enter the name of the branch: ");
		String branchName = scanner.nextLine();
		if (branchName == null) {
			System.out.println("No branch found for the specified manager.");
			return;
		}
		AdminController.displayStaffList(2, branchName, false);
	}

	public static void AddMenuItems() {
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
			ItemType itemType;
			String itemTypeStr = scanner.nextLine().toUpperCase();
			itemType = ItemType.valueOf(itemTypeStr.toUpperCase());

			System.out.print("Enter the description of the new menu item: ");
			String desc = scanner.nextLine();

			MenuControllerTemp.addToFoodRepo(name, price, branch, itemType, desc);
			System.out.println("Item " + name + " added successfully!");
		} catch (Exception e) {
			System.out.println("Error occurred while adding the menu item: " + e.getMessage());
		}
	}

/**
     * Allows the manager to add a new menu item to the system.
     */
	public void EditMenuItems() {
		try {
			// Input the details of the menu item to be edited
			System.out.print("Enter the name of the menu item to edit: ");
			String oldName = IO.userInpuString();

			System.out.print("Enter the new name of the menu item to edit: ");
			String newName = IO.userInpuString();

			System.out.print("Enter the new price of the menu item: ");
			double newPrice = scanner.nextDouble();
			scanner.nextLine();

			System.out.print("Enter the new description of the menu item: ");
			String newDescription = IO.userInpuString();

			System.out.print("Enter the branch of the menu item: ");
			String newBranch = IO.userInpuString();

			System.out.print("Enter the new category of the menu item (BURGER/SIDE/SETMEAL/DRINK): ");
			ItemType newItemType;
			String itemTypeStr = scanner.nextLine().toUpperCase();
			newItemType = ItemType.valueOf(itemTypeStr.toUpperCase());

			MenuControllerTemp.updateMenuItem(oldName, newName, newPrice, newDescription, newBranch, newItemType);
		} catch (Exception e) {
			System.out.println("Error occurred while updating the menu item: " + e.getMessage());
		}
	}
	
/**
     * Allows the manager to edit an existing menu item in the system.
     */
	public void RemoveMenuitems() {
		try {
			System.out.print("Enter the name of the menu item to edit: ");
			String name = scanner.nextLine();

			MenuControllerTemp.removeMenuItem(name);
		} catch (Exception e) {
			System.out.println("Error occurred while removing the menu item: " + e.getMessage());
		}
	}

}
