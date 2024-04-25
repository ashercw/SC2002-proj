package Boundary;

import java.util.Scanner;
import java.util.InputMismatchException;
import Controller.Request.OrderController;
import Entity.Order.Order;
import Entity.Order.OrderStatus;

public class EmployeeOrderUI {
    private Scanner scanner;

    public EmployeeOrderUI() {
        this.scanner = new Scanner(System.in);
    }

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

    private void displayOrderDetails(int orderID) {
        Order order = OrderController.getOrderById(orderID);
        if (order != null) {
            OrderController.displayOrder(order);
        } else {
            System.out.println("Order not found!");
        }
    }

    private void processOrder() {
        System.out.print("Enter order ID: ");
        try {
            int orderID = scanner.nextInt();
            Order order = OrderController.getOrderById(orderID);
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
