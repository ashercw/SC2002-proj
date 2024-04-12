package Controller.Menu;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDB;

/**
 * Temporary because idt i can run the current menucontroller coz of code issues
 * @author Saffron Lim;
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MenuControllerTemp {

    public static String FILEPATH = "FOMS\\src\\Others\\menu_listCSV.csv";

    /**
     * Loads food item list from menu CSV file. Additionally initialises the
     * respective FoodItem entity objects.
     */

    
    public static void loadFoodItems() {
        
        List al = new ArrayList();

        List<List<String>> foodList = IO.readCSV(FILEPATH);

        for (List<String> str : foodList) {
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
        addToFoodRepo("FoodItemRepo.txt", al);
        loadMenuitems(al);

    }


    public static void loadMenuitems(List al)
    {
        try {
			ArrayList branchList = TextDB.readBranch("BranchRepo.txt", false);

			for (int i = 0; i < branchList.size(); i++) {
				List menuList = new ArrayList();
				String branchName = (String) branchList.get(i);
				String FILENAME = branchName + "MenuListRepo.txt";
				for(int j = 0; j< al.size(); j++)
				{
                    FoodItem foodObj = (FoodItem) al.get(j);
					String branch = foodObj.getFoodItemBranch();
					if(branch.equals(branchName))
					{
						menuList.add(foodObj);
					}
				}
				TextDB.saveFood(FILENAME, menuList);

			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
    }
    

    public static void addToFoodRepo(String FILEPATH, List al) {
		try {
			
			TextDB.saveFood(FILEPATH, al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

}
