package Controller.Menu;

import Entity.BranchType;
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
 * Temporary because idt i can run the current menucontroller coz of code issues
 * @author Saffron Lim;
 */
public class MenuControllerTemp {

    public static String FILEPATH = "FOMS\\src\\Others\\menu_listCSV.csv";

    /**
     * Loads food item list from menu CSV file. Additionally initialises the
     * respective FoodItem entity objects.
     */

    public static void loadFoodItems() {
        List al = new ArrayList();

        List<List<String>> empList = IO.readCSV(FILEPATH);

        for (List<String> str : empList) {
            String name = str.get(0);
            name = IO.toDisplayCase(name);
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
        addToFoodRepo("FoodItemRepo", al);

    }
    

    public static void addToFoodRepo(String FILEPATH, List al) {
		TextDB txtDB = new TextDB();
		try {
			
			TextDB.saveFood(FILEPATH, al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

}
