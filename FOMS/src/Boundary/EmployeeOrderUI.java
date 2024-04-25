package Boundary;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Entity.Food.FoodItem;
import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Entity.Order.OrderLine;
import Controller.Request.OrderController;

/*
 * Page allows staff/ manager to do order related thing
 * 1) Display the new orders.
 * 2) View the details of a particular order.
 * 3) Process order: select order to process, update the status of the
      processed order from a new order to be “Ready to pickup”.
 */

public class EmployeeOrderUI {
	private OrderController orderControl;
	private Scanner scanner;
	public void displayMenu() {
        while (true) {
            System.out.println("\n1. Display New Orders\n2. View Order Details\n3. Process Order\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayNewOrders();
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
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

	public void EmployeeOrder(OrderController orderControl){
		this.orderControl = orderControl;
		this.scanner = new Scanner(System.in);
	}
    public void displayNewOrders() {
		System.out.println("New Orders: ");
		for(Order order : orderControl.getOrder().values()){
			if(order.getOrderStatus() == OrderStatus.ORDERPLACED){
				System.out.println("Order ID:" + order.getOrderID() + "Branch : " + order.getBranchName());
			}
		}
	}
	public void displayOrderDetails(int orderID){
		Order order = orderControl.getOrder(orderID);
		if(order != null){
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Branch: " + order.getBranchName());
            System.out.println("Status: " + order.getOrderStatus());
            System.out.println("Type: " + order.getOrderType());
            System.out.println("Total Price: $" + order.getTotalPrice());
            System.out.println("Items:");
			for(OrderLine line : order.getOrderLine()){
				System.out.println(" - " + line.getItem().getFoodItemName() + " x" + line.getItemQuantity() + " @ $" + line.getItem().getFoodItemPrice() + "Customisation:" + line.getCustomisation());
			}
		}else{
			System.out.println("Order not found!");
		}
	}
	public void processOrder(){
		System.out.println("Enter orderID: ");
		int orderID = scanner.nextInt();
		Order order = orderControl.getOrder(orderID);
		if(order != null && order.getOrderStatus() == OrderStatus.PROCESSING){
			order.setOrderStatus(OrderStatus.READY);
            System.out.println("Order ID: " + orderID + " is now Ready to Pickup.");
		}else{
			System.out.println("Order is not eligible for processing");
		}

	}

}

