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

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TextDBOrder extends TextDB {

    

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
		// print out the size
		//System.out.println(" Details Size: " + pDetails.size());
		//System.out.println();
		return pDetails;
	}

	public static void writeSerializedObject(String filename, List list) {
        
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

    public static void writeSerializedObject(String filename, Order order) {
        File file = new File(filename);
        List orderList = new ArrayList<>();

        if(file.exists()) {

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
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

    public static List checkDuplicates(List orderList, Order order)
    {
        for(int i = 0; i < orderList.size(); i++)
        {
            Order currOrder = (Order)orderList.get(i);
            if(currOrder.getOrderID() == order.getOrderID())
            {
                orderList.remove(i);
            }

        }
        return orderList;
    }

}
