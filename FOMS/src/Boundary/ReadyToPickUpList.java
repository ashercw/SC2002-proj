package Boundary;
import java.util.Set;
import java.util.HashSet;
public class ReadyToPickUpList {
	private static final Set<Integer> DisplayOrder = new HashSet<>(); //orderID stored in Hashset

	/**
	 * 
	 * @param orderID
	 */
	public void displayReadyToPickUpList(int orderID) {
		// TODO - implement ReadyToPickUpList.displayReadyToPickUpList
		if(DisplayOrder.isEmpty()){
			System.out.println("Order(s) are not ready to get picked up!");
			return;
		}else{
			System.out.println("Order(s) are ready to be picked up!");

		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void addReadyToPickUpList(int orderID) {
		// TODO - implement ReadyToPickUpList.addReadyToPickUpList
		DisplayOrder.add(orderID);
		System.out.println("Order: " + DisplayOrder + "added to 'Ready to be picked up' List");
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderID
	 */
	public void deleteReadyToPickUpList(int orderID) {
		// TODO - implement ReadyToPickUpList.deleteReadyToPickUpList
		if(DisplayOrder.remove(orderID)){
			System.out.println("Order" + orderID + "is removed from the list");
		}else{
			System.out.println("Order" + orderID + "is not found in the list. Please choose orders in the list!");
		}
		throw new UnsupportedOperationException();
	}

}

