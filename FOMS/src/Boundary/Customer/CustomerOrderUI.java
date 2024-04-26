package Boundary.Customer;


import Boundary.MenuDisplay;
import Controller.Request.OrderController;

import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDBFood;

/**
 * This page allows customer to see the branch menu, place an order, customise
 * order, make payment etc
 * 
 * @author Saffron, Christian
 */
@SuppressWarnings("rawtypes")
public class CustomerOrderUI {

    private String branch;
    private String repoFileName;

    @SuppressWarnings("unused")
    private ArrayList foodItems;
    //private Map<Integer, Order> orders;

    /**
     * Retrieves the name of the branch associated with this instance.
     * 
     * @return The name of the branch.
     */

    public String getBranch() {
        return this.branch;
    }

    /**
     * Constructs a CustomerOrderUI object with specified branch.
     * It initializes the repository file name based on the branch and loads food items from it.
     * 
     * @param branch The branch for which the UI is being created.
     * @throws IOException If there is an error reading the food list from the repository.
     */

    public CustomerOrderUI(String branch) {
        this.branch = branch;
        this.repoFileName = branch + "MenuListRepo.txt";
        try {
            this.foodItems = TextDBFood.readFoodList(this.repoFileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Branch Menu is out of order. Please try again later!");
        }

    }

    /**
     * This function displays a menu that allows the customer to choose what to do
     * (browse menu, make an order,
     * make payment).
     */
    public void displayMenu() {
        int userChoice = 0;

        while (userChoice != 3) {
            System.out.println("\t   Welcome to " + getBranch() + "!");
            IO.displayDivider();
            System.out.println("\t1) Browse Menu");
            System.out.println("\t2) Place an Order");
            System.out.println("\t3) Go Back");
            IO.displayDivider();
            System.out.print("\n\nWhat would you like to do today? (1-3): ");
            userChoice = IO.userInputInt();

            if (userChoice == 1) // Allow user to look at menu only
            {
                MenuDisplay.menuBrowsing(getBranch());
            } else if (userChoice == 2) // Allow user to place an order
            {
                displayPlaceOrderUI(getBranch());
                //CALL FUNCTION TO ALLOW CUSTOMER TO PLACE ORDER

            } else if (userChoice == 3) // Exit
            {
                return;
            }
        }
    }

/**
     * Displays the interface for placing an order at a specific branch.
     * It fetches the menu list for the branch and starts the order creation process.
     *
     * @param branch The branch at which the order is to be placed.
     */
    public void displayPlaceOrderUI(String branch)
    {

        ArrayList menuList = MenuDisplay.printMenu(getBranch());
        OrderController.createOrder(branch, menuList);


    }

}
