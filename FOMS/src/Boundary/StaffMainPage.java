package Boundary;

import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Others.IO;

import java.util.ArrayList;
import java.util.List;

public class StaffMainPage {

 private String staffID;
 private List<Order> orders; 

 public StaffMainPage(String staffID) {
    //TO-DO: instantiate staff obj?
        this.staffID = staffID;
        this.orders = new ArrayList<>(); 
    }

 public void displayStaffMainPage() {
    while (true) {
        System.out.println("Welcome to the staff page!\n");
        System.out.println("What would you like to do today?");
        System.out.println("\t1. Display new order");
        System.out.println("\t2. Process new order");
        System.out.println("\t3. Go back");
        System.out.print("\n\nYour choice (1-3): ");
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
        else if (userChoice == 3) 
        {
            return;
        }
        else
            System.out.println("Invalid response. Please enter 1-3!");
            System.out.print("Your choice (1-3): ");
    }
 }

 // Method to simulate adding orders to the list (for demonstration purposes)
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Display orders that are new
    public void displayNewOrders() {
        System.out.println("New Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Status: " + order.getOrderStatus());
            System.out.println("Items:");
            order.getOrderLine().forEach((item) -> System.out.println(item.getItem().getFoodItemName() + ", Quantity: " + item.getitemQuanity()));
            System.out.println("Total Price: " + order.getTotalPrice());
        }
    }


    // Process an order by ID, updating its status to Ready to Pickup
    public void processOrder(int orderId) {
        if(orderId <= 0){
            throw new UnsupportedOperationException("OrderID is not given.");
        }
        for (Order order : orders) {
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