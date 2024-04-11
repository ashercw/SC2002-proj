package Boundary;
import java.util.HashMap;
import java.util.Map;

public class OrderStatusViewer {
	private static final Map<Integer, String> Status = new HashMap<Integer, String>(); //Stores the status of list of orderID
	// Integer represents orderID
	// String represents Status


	/**
	 * 
	 * @param orderID
	 */
	public static void displayOrderStatus(int orderID) {
		// TODO - implement OrderStatusViewer.displayOrderStatus
		if(!Status.containsKey(orderID)){  //orderID does not exist
			System.out.println("Order ID Not Found!");
			return;
		}else{
			System.out.println(orderID + ": " + Status.get(orderID)); 
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public static void changeOrderStatus(int orderID, String newStatus) {
		// TODO - implement OrderStatusViewer.changeOrderStatus
		Status.put(orderID, newStatus); //changes order status
		System.out.println(orderID + "status changed to: " + newStatus);
		throw new UnsupportedOperationException();
	}

}
