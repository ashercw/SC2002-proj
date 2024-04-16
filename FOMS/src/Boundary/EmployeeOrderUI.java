package Boundary;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Entity.Food.FoodItem;
import Entity.Order.Customisation;
import Entity.Order.Order;
import Entity.Order.OrderStatus;
import Entity.Order.OrderType; 
import Entity.Order.OrderLine;

/*
 * Page allows staff/ manager to do order related thing
 * 1) Display the new orders.
 * 2) View the details of a particular order.
 * 3) Process order: select order to process, update the status of the
      processed order from a new order to be “Ready to pickup”.
 */

public class EmployeeOrderUI {

    public void displayOrderUI(int orderID) {
		// TODO - implement OrderUI.displayOrderUI
		/*Order order = Order.get(orderID);
		if(order == null){
			System.out.println("Order not found!");
		}else{
			System.out.println(orderID + "Items : " + order);
			OrderStatusViewer.displayOrderStatus(orderID);
		}*/
	}

}
