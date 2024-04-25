package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;
import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Entity.Order.OrderLine;
import Controller.Request.OrderController;

public class EmployeeOrderUI {
    private OrderController orderControl;
    private Scanner scanner;

    public EmployeeOrderUI(OrderController orderControl) {
        this.orderControl = orderControl;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n1. Display New Orders");
            System.out.println("2. View Order Details");
            System.out.println("3. Process Order");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        displayAllOrders();
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

    public void displayAllOrders() {
        System.out.println("All Orders: ");
        for (Order order : orderControl.getOrders().values()) {
            if (order.getOrderStatus() == OrderStatus.ORDERPLACED) {
                System.out.println("Order ID: " + order.getOrderID() + " Branch: " + order.getBranchName());
            }
        }
    }

    public void displayOrderDetails(int orderID) {
        Order order = orderControl.getOrder(orderID);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Branch: " + order.getBranchName());
            System.out.println("Status: " + order.getOrderStatus());
            System.out.println("Type: " + order.getOrderType());
            System.out.println("Total Price: $" + order.getTotalPrice());
            System.out.println("Items:");
            for (OrderLine line : order.getOrderLine()) {
                System.out.println(" - " + line.getItem().getFoodItemName() + " x" + line.getItemQuantity() + " @ $"
                        + line.getItem().getFoodItemPrice()
                        + (line.getCustomisation() != null ? " Customisation: " + line.getCustomisation() : ""));
            }
        } else {
            System.out.println("Order not found!");
        }
    }

    public void processOrder() {
        System.out.print("Enter order ID: ");
        try {
            int orderID = scanner.nextInt();
            Order order = orderControl.getOrder(orderID);
            if (order != null && order.getOrderStatus() == OrderStatus.PROCESSING) {
                order.setOrderStatus(OrderStatus.READY);
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
