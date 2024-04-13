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
public class OrderUI {

	private Map<Integer, Order> Orders = new HashMap<>();

	/**
	 * 
	 * @param orderID
	 */
	public void displayOrderUI(int orderID) {
		// TODO - implement OrderUI.displayOrderUI
		Order order = Orders.get(orderID);
		if(order == null){
			System.out.println("Order not found!");
		}else{
			System.out.println(orderID + "Items : " + order);
			OrderStatusViewer.displayOrderStatus(orderID);
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void addOrderItem(int orderID) {
		Scanner scanner = new Scanner(System.in);
		List<OrderLine> orderLines = new ArrayList<>();
		String moreItems;

		//collect order status and type before putting in new item
		System.out.println("Enter order status (e.g., NEW, PROCESSING, COMPLETED):");
        	OrderStatus orderStatus = OrderStatus.valueOf(scanner.nextLine().toUpperCase());

        	System.out.println("Enter order type (e.g., DINE_IN, TAKE_OUT):");
        	OrderType orderType = OrderType.valueOf(scanner.nextLine().toUpperCase());

		//when adding order items
		while(moreItems.equalsIgnoreCase("Y")){
			System.out.println("Add new item :");
			String Item = scanner.nextLine();
			FoodItem fooditem = new FoodItem(newItem); //????

			System.out.println("Enter quantity: ");
			int quantity = scanner.nextInt();

			//customisation of item
			System.out.println("Customise? (YESCUSTOM/NOCUSTOM)");
			Customisation customise = Customisation.valueOf(scanner.nextLine().toUpperCase());
			String newCustomise = "";
			if(customise.equals(Customisation.YESCUSTOM)){
				System.out.println("Describe the customization:");
            			newCustomise = scanner.nextLine();
			}
			
			OrderLine orderline = new OrderLine(fooditem, quantity, customise, newCustomise);
			orderLines.add(orderline);
			System.out.println("Add more items? (Y/N)");
			moreItems = scanner.nextLine();
		}
        	int orderQuantity = Integer.parseInt(scanner.nextLine());
		double newPrice = calculateTotalPrice();
		orderID++;
		Order newOrder = new Order(orderStatus, orderType, newPrice, orderLines, orderQuantity, orderID);
		Orders.put(newOrder.getOrderID(), newOrder);
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void deleteOrderItem(int orderID) {
		// TODO - implement OrderUI.deleteOrderItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void editOrderItem(int orderID) {
		// TODO - implement OrderUI.editOrderItem
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void generateOrderDetails(int orderID) {
		// TODO - implement OrderUI.generateOrderDetails
		throw new UnsupportedOperationException();
	}

}
