package Others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;
import Entity.Order.Order;
import Entity.Order.OrderLine;
import Entity.Order.OrderStatus;
import Entity.Order.OrderType;

/**
 * Handles the serialization and deserialization of order-related data to and from files.
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
 * @return A List containing deserialized objects read from the file. Returns null if an exception occurs.
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
 * Writes a list of objects as a serialized file
 * Serializes entire list of objects into a file to help with persistence
 *
 * @param filename The path of the file where the serialized data will be written.
 * @param list The List of objects to serialize and write to the file.
 * @exception IOException If an I/O error occurs during writing to the file.
 */
	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
 * Serializes and writes a single Order object to a file.
 * Same as above but for single orders
 *
 * @param filename The path of the file where the serialized data will be written.
 * @param order The Order object to serialize and write to the file.
 * @exception IOException If an I/O error occurs during writing to the file.
 */
    public static void writeSerializedObject(String filename, Order order) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(order);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
