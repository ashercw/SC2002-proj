package Boundary.Customer;

import java.util.List;

import Others.IO;
@SuppressWarnings("resource")
public class CustomerPage {

    private static List<String> branches; // A list of branch names for demonstration.

    public CustomerPage(List<String> branchList) {
        if (branches == null) {
            throw new IllegalArgumentException("Null error. Sorry, this function is not available at the moment!");
        }
        branches = branchList;
    }

    public static void displayBurger() {
        System.out.println("""
                   __
                   /
                .-/-.
                |'-'|
                |   |
                |   |   .-\"\"\"\"-.
                \\___/  /' .  '. \\   \\|/\\//
                      (`-..:...-')  |`""`|
                       ;-......-;   |    |
                        '------'    \\____/
                       """);
    }

    public static void displayCustomerMainPg() {
        displayBurger();
        IO.printNewLine(2);
        System.out.println("\t    WELCOME CUSTOMER");
        IO.displayDivider();
        System.out.println("\t1) Select branch");
        System.out.println("\t2) Track order");
        System.out.println("\t3. Go back");
        System.out.print("\n\nYour choice (1-3): ");

        while (true) {
            
			int userChoice = IO.userInputInt();

            if (userChoice == 1) // allow customer to select branch
			{
				IO.printNewLine(5);
				branchBrowsing();
			} else if (userChoice == 2) // allow customer to track order
			{
				System.out.println("Continue as customer...");
				IO.printNewLine(5);
			} else if (userChoice == 3) { //Exit and Logout
				return;
			} 
			else
			{
				System.out.println("Invalid response. Please enter 1-3!");
				System.out.print("Your choice (1-3): ");
			}
            
        }
    }

    /**
     * Displays available branches and allows the user to select one to browse further.
     */
    public static void branchBrowsing() {
        if (branches.isEmpty()) {
            System.out.println("No branches available at the moment.");
            return;
        }

        System.out.println("Available Branches:");
        int index = 1;
        for (String branch : branches) {
            System.out.println(index++ + ". " + branch);
        }

        
        System.out.print("Enter the number of the branch you want to explore: ");
        int choice = IO.userInputInt();
        if (choice < 1 || choice > branches.size()) {
            System.out.println("Invalid branch selection.");
        } else {
            System.out.println("You have selected: " + branches.get(choice - 1));
            // Further functionality can be added here, like browsing menus of the selected branch.
        }
    }
}
