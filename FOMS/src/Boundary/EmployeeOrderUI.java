package Boundary;

import java.util.Scanner;
import java.util.InputMismatchException;
import Controller.Request.OrderController;
import Entity.Order.Order;
import Entity.Order.OrderStatus;
/**
 * This class provides the user interface for employees to manage orders.
 * Employees can display all orders, view details of specific orders, process orders,
 * and exit the menu.
 */

public class EmployeeOrderUI {
    private Scanner scanner;

    /**
     * Constructs an EmployeeOrderUI instance initializing the scanner used for input.
     */

    public EmployeeOrderUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu to the employee and processes user input to navigate through options.
     * The menu loops indefinitely until the user chooses to exit.
     */

    public void displayMenu() {
        while (true) {
            System.out.println("\n1. Display All Orders");
            System.out.println("2. View Order Details");
            System.out.println("3. Process Order");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        OrderController.printAllOrders();
                        break;
                    case 2:
                        System.out.print("Enter Order ID: ");
                        int orderId = scanner.nextInt();
                        displayOrderDetails(orderId);
                        break;
                    case 3:
                        processOrder();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
    }
    /**
     * Displays the details of an order specified by the order ID.
     * 
     * @param orderID
     */

    private void displayOrderDetails(int orderID) {
        Order order = OrderController.getOrderById(orderID);
        if (order != null) {
            OrderController.displayOrder(order);
        } else {
            System.out.println("Order not found!");
        }
    }

    /**
     * Processes an order by setting its status to READY if it is currently ORDERPLACED.
     * The employee is prompted to enter the order ID, and the order is updated accordingly.
     */

    private void processOrder() {
        System.out.print("Enter order ID: ");
        try {
            String orderID = scanner.nextLine();
            if(orderID.isEmpty() || orderID.isBlank())
            {
                System.out.println("Please select an item! Do not enter an empty input!");
                return;
            }
            int orderIDint = Integer.parseInt(orderID);
            Order order = OrderController.getOrderById(orderIDint);
            if (order != null && order.getOrderStatus() == OrderStatus.ORDERPLACED) {
                order.setOrderStatus(OrderStatus.READY); // Update the order status
                OrderController.updateOrder(order); 
                System.out.println("Order ID: " + orderID + " is now Ready to Pickup.");
            } else {
                System.out.println("Order is not eligible for processing");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric ID.");
            scanner.nextLine(); // Clear the buffer
        }
    }
}
