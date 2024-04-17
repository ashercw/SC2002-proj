package Boundary.Customer;

import java.util.List;

import Others.IO;

/**
 * @author Christian Asher, Saffron Lim
 */

@SuppressWarnings("resource")
public class CustomerPage {

    private static List<String> branches; // A list of branch names for demonstration.

    /**
     * Constructor.
     * 
     * @param branchList List<String> of available branches.
     * 
     */
    public CustomerPage(List<String> branchList) {
        branches = branchList;
        if (branches == null) {
            throw new IllegalArgumentException("Null error. Sorry, this function is not available at the moment!");
        }
    }

    /**
     * Prints burger ASCII art.
     * ASCII art obtained from: https://www.qqpr.com/ascii-art-food-2.html
     */
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

    /*
     * Displays the page that allows the customer to track their order or select a
     * branch
     * to place an order.
     */
    public static void displayCustomerMainPg() {
        displayBurger();
        IO.printNewLine(1);

        while (true) {
            System.out.println("\t    WELCOME CUSTOMER");
            IO.displayDivider();
            System.out.println("\t1) Select branch");
            System.out.println("\t2) Track order");
            System.out.println("\t3. Go back");
            System.out.print("\n\nYour choice (1-3): ");
            int userChoice = IO.userInputInt();

            if (userChoice == 1) // allow customer to select branch
            {
                String custBranch = branchBrowsing();
                if(custBranch.equals("")) continue;
                else
                {
                    //proceed to other functionality
                }
            } else if (userChoice == 2) // allow customer to track order
            {
                System.out.println("Continue as customer...");
                IO.printNewLine(5);
            } else if (userChoice == 3) { // Exit page
                System.out.println("Exiting page...");
                return;
            } else {
                System.out.println("Invalid response. Please enter 1-3!");
                System.out.print("Your choice (1-3): ");
            }

        }
    }

    /**
     * Displays available branches and allows the user to select one to browse
     * further.
     * @return String representing the customer's selected branch.
     */
    public static String branchBrowsing() {
        if (branches.isEmpty()) {
            System.out.println("No branches available at the moment.");
            return "";
        }

        IO.printNewLine(5);

        System.out.println("\tAVAILABLE BRANCHES");
        IO.displayDivider();
        int index = 1;
        for (String branch : branches) {
            System.out.println(index++ + ". " + branch);
        }

        System.out.print("Enter the number of the branch you want to explore: ");
        int choice = IO.userInputInt();
        if (choice < 1 || choice > branches.size()) {
            System.out.println("Invalid branch selection.\n\n");
            return "";
        } else {
            String branch = branches.get(choice - 1);
            System.out.println("You have selected: " + branch);
            return branch;
        }
    }


    /**
     * Prints out the menu to allow customer to track order
     */
    public static void trackOrderDisplay()
    {
        IO.printNewLine(5);
        System.out.println("\tTRACK ORDER");
        IO.displayDivider();

        // allow customer to select branch in order to track ID
        String custBranch = branchBrowsing();

        // read orderByBranchRepo (orderByBranchRepo) is not done
        // check if orderByBranchRepo is empty

        // if not empty
        System.out.print("Enter your Order ID: ");
        int orderID = IO.userInputInt();

        // check if order exists

        // display order details and order status

        // if order status = READY TO COLLECT
        // get verification from customer (e.g., phone number)
        // allow user to collect order

    }
}
