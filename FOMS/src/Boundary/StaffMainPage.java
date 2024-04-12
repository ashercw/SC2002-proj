package Boundary;

import Entity.Order.OrderNew;
import Entity.Order.OrderStatus;
import Entity.Order.OrderType;
import Entity.User.EmployeeType;
import Others.IO;

import java.util.ArrayList;
import java.util.List;

import Controller.Account.AccountController;

public class StaffMainPage {

 private String staffID;
 private List<OrderNew> orders; 

 public StaffMainPage(String staffID) {
        this.staffID = staffID;
        this.orders = new ArrayList<>(); 
    }

 public void e() {
    while (true) {
        System.out.println("Welcome to the staff page!\n");
        System.out.println("What would you like to do today?");
        System.out.println("\t1. Display new order");
        System.out.println("\t2. Process new order");
        System.out.print("\n\nYour choice (1-2): ");
        int userChoice = IO.userInputInt();

        if (userChoice == 1) 
        {
            System.out.println("Display new order...");
            System.out.println("\n\n\n\n\n");
            displayNewOrders();
        } else if (userChoice == 2) 
        {
            System.out.println("Process new order...");
            System.out.println("\n\n\n\n\n");
            while (true){
                try{
                    System.out.println("Please enter the OrderID to process.");
                    int OrderID = IO.userInputInt();
                    processOrder(OrderID);
                    break;
                }
                catch(UnsupportedOperationException error){
                    System.out.println(error.getMessage());
                }
            }
        } 
        else
            System.out.println("Invalid response. Please enter 1-2!");
            System.out.print("Your choice (1-2): ");
    }
 }

 // Method to simulate adding orders to the list (for demonstration purposes)
    public void addOrder(OrderNew order) {
        orders.add(order);
    }

    // Display orders that are new
    public void displayNewOrders() {
        System.out.println("New Orders:");
        for (OrderNew order : orders) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Status: " + order.getOrderStatus());
            System.out.println("Items:");
            order.getItems().forEach((item, quantity) -> System.out.println(item.getName() + ", Quantity: " + quantity));
            System.out.println("Total Price: " + order.getTotalPrice());
        }
    }


    // Process an order by ID, updating its status to Ready to Pickup
    public void processOrder(int orderId) {
        if(orderId <= 0){
            throw new UnsupportedOperationException("OrderID is not given.");
        }
        for (OrderNew order : orders) {
            if (order.getOrderID() == orderId && (order.getOrderStatus() == OrderStatus.ORDERPLACED || order.getOrderStatus() == OrderStatus.PROCESSING)) {
                order.setOrderStatus(OrderStatus.READY);
                System.out.println("Order ID " + orderId + " is now ready to pick up.");
                return;
            }
        }
        System.out.println("Order with ID " + orderId + " not found or not in a 'new' status.");
    }

    // Getters and Setters
    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

}