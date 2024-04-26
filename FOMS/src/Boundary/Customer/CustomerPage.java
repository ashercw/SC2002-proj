package Boundary.Customer;

import java.util.List;

import Controller.Menu.MenuControllerTemp;
import Controller.Request.OrderController;
import Others.IO;

/**
 * This page displays the Customer's main page, where they can choose between tracking their order and 
 * selecting a branch to place an order.
 * @author Christian Asher, Saffron Lim
 */

@SuppressWarnings("resource")
public class CustomerPage {

    /**
     * A list of branch names for demonstration for further utility.
     */
    private static List<String> branches; 

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
            MenuControllerTemp.loadMenuItemsFromFood();
            System.out.println("\t    WELCOME CUSTOMER");
            IO.displayDivider();
            System.out.println("\t1) Select branch");
            System.out.println("\t2) Track order");
            System.out.println("\t3) Go back");
            System.out.print("\n\nYour choice (1-3): ");
            int userChoice = IO.userInputInt();

            if (userChoice == 1) // allow customer to select branch
            {
                String custBranch = branchBrowsing();
                if(custBranch.equals("")) continue;
                else
                {
                    //proceed to other functionality
                    CustomerOrderUI custOrderUIObj = new CustomerOrderUI(custBranch);
                    custOrderUIObj.displayMenu();
                }
            } else if (userChoice == 2) // allow customer to track order
            {
                IO.printNewLine(5);
                IO.displayDivider();
                System.out.println("\t\tTRACK ORDER");
                IO.displayDivider();
                IO.printNewLine(2);
                
                //OrderController.printAllOrders();
                OrderController.collectOrder();
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
     * Displays available branches and allows the customer to select one to browse
     * further.
     * @return String representing the customer's selected branch.
     */
    public static String branchBrowsing() {
        //if branches list is empty
        if (branches.isEmpty()) {
            System.out.println("No branches available at the moment.");
            return "";
        }

        //print out available branches
        IO.printNewLine(5);
        System.out.println("\tAVAILABLE BRANCHES");
        IO.displayDivider();
        int index = 1;
        for (String branch : branches) {
            System.out.println(index++ + ". " + branch);
        }

        // allow user to choose branch
        System.out.print("Enter the number of the branch you want to explore: ");
        int choice = IO.userInputInt();
        //check if choice is valid
        if (choice < 1 || choice > branches.size()) {
            System.out.println("Invalid branch selection.\n\n");
            return "";
        } else {
            String branch = branches.get(choice - 1);
            System.out.println("You have selected: " + branch);
            return branch;
        }
    }


    
}
