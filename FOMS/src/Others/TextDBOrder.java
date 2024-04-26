package Others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import Entity.Order.Order;

/**
 * Handles the serialization and deserialization of order-related data to and
 * from files.
 * Class provides methods to read and write order details as serialized objects.
 * Helps persist order data across sessions in binary format.
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TextDBOrder extends TextDB {

	/**
	 * Reads serialized object data from a file and returns it as a List.
	 * Retrieves order details from a serialized file format.
	 *
	 * @param filename The path of the file from which to read the serialized data.
	 * @return A List containing deserialized objects read from the file. Returns
	 *         null if an exception occurs.
	 */
	public static List readSerializedObject(String filename) {
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}


	/**
	 * Serializes and writes a single Order object to a file.
	 * Same as above but for single orders
	 *
	 * @param filename The path of the file where the serialized data will be
	 *                 written.
	 * @param order    The Order object to serialize and write to the file.
	 * @exception IOException If an I/O error occurs during writing to the file.
	 */
	public static void writeSerializedObject(String filename, Order order) {
		File file = new File(filename);
		List orderList = new ArrayList<>();

		if (file.exists()) {

			if (file.length() != 0) {
				orderList = readSerializedObject(filename);
				orderList = checkDuplicates(orderList, order);
			}
		}
		orderList.add(order);
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(orderList);
			out.close();
			// System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
     * Checks for duplicate orders in the list and removes them.
     *
     * @param orderList The list of orders to check for duplicates.
     * @param order     The order to be checked for duplication.
     * @return A list of orders without any duplicates.
     */
	public static List checkDuplicates(List orderList, Order order) {
		for (int i = 0; i < orderList.size(); i++) {
			// List currOrder = (List)orderList.get(i);
			Order currOrder2 = (Order) orderList.get(i);
			if (currOrder2.getOrderID() == order.getOrderID()) {
				orderList.remove(i);
			}

		}
		return orderList;
	}

}
