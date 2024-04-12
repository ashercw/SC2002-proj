package Controller.Request;
import Entity.Branch;
import Entity.Food.FoodItem;
import Entity.Food.ItemType;
import Entity.User.*;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDB;
import Boundary.AttributeGetter;

/**
 * Handles any sort of data control/ manipulation for branch entities.
 * @author Christian Asher, Saffron Lim
 */

public class BranchController {

	public static String FILEPATH;

	/**
	 * 
	 * @param branchName
	 */
	public Branch addBranch(String branchName) {
		// TODO - implement BranchController.addBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branchId
	 * @param newName
	 */
	public void updateBranch(String branchId, String newName) {
		// TODO - implement BranchController.updateBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branchId
	 */
	public void removeBranch(String branchId) {
		// TODO - implement BranchController.removeBranch
		throw new UnsupportedOperationException();
	}

	/**
     * Loads branch list from branch list CSV file. Additionally initialises the
     * respective Branch entity objects.
	 * INCOMPLETE
     */

	 public static void loadBranches() {
        List al = new ArrayList();

        List<List<String>> empList = IO.readCSV(FILEPATH);

        for (List<String> str : empList) {
            String name = str.get(0);
            double price = Double.parseDouble(str.get(1));
            String branch = str.get(2);
            String item = str.get(3);
            ItemType itemType = ItemType.BURGER; // for initialisation purposes

            // changing item type
            if (item.equals("side"))
                itemType = ItemType.SIDE;
            else if (item.equals("set meal"))
                itemType = ItemType.SETMEAL;
            else if (item.equals("burger"))
                itemType = ItemType.BURGER;
            else if (item.equals("drink"))
                itemType = ItemType.DRINK;

            FoodItem food = new FoodItem(name, price, branch, itemType);
            al.add(food);

        }
        // add to repo

    }

}