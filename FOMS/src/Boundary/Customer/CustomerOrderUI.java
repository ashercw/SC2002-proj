package Boundary.Customer;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import Boundary.MenuDisplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Entity.Food.FoodItem;
import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Entity.Order.OrderType;
import Others.IO;
import Others.TextDBFood;
import Entity.Order.OrderLine;

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

    private ArrayList foodItems;
    private Map<Integer, Order> orders;
    private Scanner scanner;

    public String getBranch() {
        return this.branch;
    }

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
                MenuDisplay.menuBrowsing(getBranch());
                //CALL FUNCTION TO ALLOW CUSTOMER TO PLACE ORDER

            } else if (userChoice == 3) // Exit
            {
                return;
            }
        }
    }

}
