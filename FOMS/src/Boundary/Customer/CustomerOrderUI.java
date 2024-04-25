package Boundary.Customer;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Entity.Food.FoodItem;
import Entity.Order.Customisation;
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

    public CustomerOrderUI(String branch) 
    {
        this.branch = branch;
        this.repoFileName = branch + "MenuListRepo";
        try
        {this.foodItems = TextDBFood.readFoodList(this.repoFileName);}
        catch(IOException e)
        {
            System.out.println("Branch Menu is out of order. Please try again later!");
        }

    }


    /**
     * This function displays a menu that allows the customer to choose what to do (browse menu, make an order, 
     * make payment).
     */
    public void displayMenu() 
    {
        System.out.println("Menu for branch: " + branch);
        /*for (FoodItem item : foodItems) 
        {
            System.out.println(item.getFoodItemName() + " - $" + item.getFoodItemPrice() + " - " + item.getFoodItemDesc());
        }*/
    }


}


