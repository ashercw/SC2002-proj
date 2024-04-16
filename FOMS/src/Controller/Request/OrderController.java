package Controller.Request;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import Entity.Food.FoodItem;
import Entity.Food.ItemType; // If ItemType is used for setting the type of food items.
import Entity.Order.Customisation;
import Entity.Order.Order;
import Entity.Order.OrderLine;
import Entity.Order.OrderStatus; // If setting the order status upon creation.
import Entity.Order.OrderType; // For handling different types of orders (DINE-IN, TAKEAWAY).
import Controller.Request.OrderController; 



public class OrderController {
    private List<Order> orders = new ArrayList<>();

    public Order placeOrder(List<OrderLine> orderLines, OrderType orderType) {
        double totalPrice = 0;
        for (OrderLine line : orderLines) {
            totalPrice += line.getItem().getFoodItemPrice() * line.getitemQuanity();
        }
        //TODO:get branch name when placing order
        Order newOrder = new Order(OrderStatus.ORDERPLACED, orderType, totalPrice, orderLines, orderLines.size(),branchName);
        orders.add(newOrder); // Simulating adding to a repository
        return newOrder;
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
