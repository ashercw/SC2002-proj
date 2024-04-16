package Controller.Menu;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;
import Entity.Food.Menu;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDB;
import Others.TextDBBranch;
import Others.TextDBFood;

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
     * @throws IOException 
     */

    public static void loadFoodItems() throws IOException {
        
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
			ArrayList branchList = TextDBBranch.readBranch("BranchRepo.txt", false);

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
				TextDBFood.saveFood(FILENAME, menuList);

			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
    }
    

    public static void addToFoodRepo(String FILEPATH, List al) throws IOException{
		try {
			TextDBFood.saveFood(FILEPATH, al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

    // for use by manager
    public boolean addMenuItem(String name, double price, String branch, ItemType category) {
        boolean added = false;
        // Check if item already exists
        for (FoodItem item : menu.getMenuList()) {
            if (item.getFoodItemName().equals(name) && item.getFoodItemBranch().equals(branch)) {
                return false; // Item already exists
            }
        }
        FoodItem newItem = new FoodItem(name, price, branch, category);
        //not sure why desription doesn't work
        menu.getMenuList().add(newItem); // Adding to the menu list
        added = true;
        // Save to repository
        try {
            addToFoodRepo("FoodItemRepo.txt", menu.getMenuList());
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
            added = false;
        }
        return added;
    }

    public boolean updateMenuItem(String name, double newPrice, String newDescription) {
        boolean updated = false;
        for (FoodItem item : menu.getMenuList()) {
            if (item.getFoodItemName().equals(name) && item.getFoodItemBranch().equals(branch))
             { //no clue why branch isnt valid FIX: need to access branch variable somehow 
                item.setPrice(newPrice);
                item.setDescription(newDescription);
                updated = true;
                break;
            }
        }
        if (updated) {
            try {
                addToFoodRepo("FoodItemRepo.txt", menu.getMenuList());
            } catch (IOException e) {
                System.out.println("IOException > " + e.getMessage());
                updated = false;
            }
        }
        return updated;
    }

    public boolean removeMenuItem(String name, String branch) {
        boolean removed = false;
        FoodItem toRemove = null;
        for (FoodItem item : menu.getMenuList()) {
            if (item.getFoodItemName().equals(name) && item.getFoodItemBranch().equals(branch)) {
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            menu.getMenuList().remove(toRemove);
            removed = true;
            try {
                addToFoodRepo("FoodItemRepo.txt", menu.getMenuList());
            } catch (IOException e) {
                System.out.println("IOException > " + e.getMessage());
                removed = false;
            }
        }
        return removed;
    }


    
}
