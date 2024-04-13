package Controller.Menu;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;
import Entity.Food.Menu;
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

    private Menu menu; // Assuming 'Menu' handles the collection of menu items.
    //private MenuRepository menuRepository; // For persistence. NOT BEING USED

    /*public MenuControllerTemp(Menu menu, MenuRepository menuRepository) {
        this.menu = menu;
        this.menuRepository = menuRepository;
    }*/ //idk


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
        loadMenuItems(al);

    }


    public static void loadMenuItems(List al)
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


    public boolean addMenuItem(String name, double price, String branch, ItemType category) {
        // Use MenuValidator here if needed
        boolean added = false;
        FoodItem newItem = new FoodItem(name, price, branch, category);
        // Adding to the 'Menu' object for runtime representation
        // TO-DO: Write to repo (using TextDB functions)
        return added;
    }

    public boolean updateMenuItem(String name, double newPrice, String newDescription) {
        // Update operation logic, potentially involving MenuValidator
        boolean updated = false;
        //How to choose which description to update??
        // update in repo
        return updated;
    }

    public boolean removeMenuItem(String name) {
        // Removal logic
        boolean removed = false;
        //remove 
        return removed;
    }

}
