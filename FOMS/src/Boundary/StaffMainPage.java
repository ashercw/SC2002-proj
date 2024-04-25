package Boundary;

import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Entity.User.Staff;
import Entity.User.User;
import Others.IO;
import Others.TextDBOrder;
import Others.TextDBStaff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller.Request.OrderController;

/**
 * The StaffMainPage class provides an interface for staff members to interact with orders.
 * This class handles operations like displaying new orders, processing orders, and tracking order status.
 */
@SuppressWarnings("rawtypes")
public class StaffMainPage {

 private Staff staff;
 private List<Order> orders; 

 /**
 * Constructs a StaffMainPage object for a given staff member identified by staffID.
 * It initializes the list of orders and loads staff details from the "EmployeeRepo.txt" file.
 *
 * @param staffID the unique identifier of the staff member.
 */

public StaffMainPage(String staffID) {
        this.orders = new ArrayList<Order>(); 
        try {
			ArrayList employeeList = TextDBStaff.readEmployee("EmployeeRepo.txt");
            User emp = new User();

			for (int i = 0; i < employeeList.size(); i++) {
				emp = (User)employeeList.get(i);
				if (emp.getLoginID() == staffID) {
                    staff = (Staff)emp;
                    break;
                }
			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
    }

/**
     * Displays the main page interface for the staff, allowing them to choose between
     * displaying new orders, processing orders, or exiting the interface.
     */

 public void displayStaffMainPage() {
    while (true) {
        System.out.println("Welcome to the staff page!\n");
        System.out.println("What would you like to do today?");
        System.out.println("\t1. Display new order");
        System.out.println("\t2. Process new order");
        System.out.println("\t3. Track order status");
        System.out.println("\t4. Go back");
        System.out.print("\n\nYour choice (1-4): ");
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
            System.out.println("Please enter the OrderID to process.");
            int orderID = IO.userInputInt();
            System.out.println("Track order status...");
            System.out.println("\n\n\n\n\n");
            trackOrderStatus(orderID);
        }
        else if (userChoice == 4) 
        {
            return;
        }
        else
            System.out.println("Invalid response. Please enter 1-4!");
            System.out.print("Your choice (1-4): ");
    }
 }

/**
     * Displays a list of new orders, including details such as order ID, status, items, and total price.
     */

    public void displayNewOrders() {
        // TODO: switch to OrderByBranchRepo
        /*System.out.println("New Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Status: " + order.getOrderStatus());
            System.out.println("Items:");
            order.getOrderLine().forEach((item) -> System.out.println(item.getItem().getFoodItemName() + ", Quantity: " + item.getItemQuantity()));
            System.out.println("Total Price: " + order.getTotalPrice());
        }*/
        OrderController.printAllOrders();
    }

/**
     * Processes an order by updating its status to "Ready to Pickup" based on the given order ID.
     *
     * @param orderId the ID of the order to be processed.
     * @throws UnsupportedOperationException if the order ID is not valid.
     */

    public void processOrder(int orderId) {
        if(orderId <= 0){
            throw new UnsupportedOperationException("OrderID is not given.");
        }

        List fullOrderL = TextDBOrder.readSerializedObject("OrderRepo.txt");
        Order currOrder2;
        //System.out.print("NUMBER OF ORDERS: " + fullOrderL.size());
        for (int i = 0; i < fullOrderL.size(); i++) {
            //List currOrder = (List)fullOrderL.get(i);
			
            currOrder2 = (Order)fullOrderL.get(i);
            //Order orderObj = (Order) fullOrderL.get(i);
            if (currOrder2.getOrderID() == orderId && (currOrder2.getOrderStatus() == OrderStatus.ORDERPLACED || currOrder2.getOrderStatus() == OrderStatus.PROCESSING)) {
                currOrder2.setOrderStatus(OrderStatus.READY);
                OrderController.displayOrder(currOrder2);
                System.out.println("Order ID " + orderId + " is now ready to pick up.");
                TextDBOrder.writeSerializedObject("OrderRepo.txt", currOrder2);
                return;
            }
            
        }

        /*for (Order order : orders) {
            if (order.getOrderID() == orderId && (order.getOrderStatus() == OrderStatus.ORDERPLACED || order.getOrderStatus() == OrderStatus.PROCESSING)) {
                order.setOrderStatus(OrderStatus.READY);
                System.out.println("Order ID " + orderId + " is now ready to pick up.");
                return;
            }
        }*/
        System.out.println("Order with ID " + orderId + " not found or not in a 'new' status.");
    }

/**
     * Tracks and prints the status of an order based on the given order ID.
     *
     * @param orderID the ID of the order to be tracked.
     */

    public void trackOrderStatus(int orderID){
        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                switch(order.getOrderStatus()){
                    case ORDERPLACED:
                        System.out.println("The order is placed.");
                        break;
                    case PROCESSING:
                        System.out.println("The order is processing.");
                        break;
                    case READY:
                        System.out.println("The order is ready.");
                        break;
                    case COLLECTED:
                        System.out.println("The order is collected.");
                        break;
                    case DISCARDED:
                        System.out.println("The order is discarded.");
                        break;
                }
                return;
            }
        }
        System.out.println("Order with ID " + orderID + " not found.");
    }

/**
     * Returns the login ID of the staff member.
     *
     * @return the login ID of the staff.
     */
    
    public String getStaffID() {
        return staff.getLoginID();
    }
}