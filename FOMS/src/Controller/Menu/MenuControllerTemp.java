package Controller.Menu;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;
import Entity.Food.Menu;
import Entity.User.Admin;
import Entity.User.EmployeeType;
import Entity.User.Manager;
import Entity.User.Staff;
import Entity.User.User;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDBBranch;
import Others.TextDBFood;
import Others.TextDBStaff;

/**
 * Temporary because idt i can run the current menucontroller coz of code issues
 * 
 * @author Saffron Lim;
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MenuControllerTemp {

	public static String FILEPATH = "FOMS\\src\\Others\\menu_listCSV.csv";
	public static String FOOD_REPO_FILEPATH = "FoodItemRepo.txt";

	private Menu menu; // Assuming 'Menu' handles the collection of menu items.
	// private MenuRepository menuRepository; // For persistence. NOT BEING USED

	/*
	 * public MenuControllerTemp(Menu menu, MenuRepository menuRepository) {
	 * this.menu = menu;
	 * this.menuRepository = menuRepository;
	 * }
	 */
	// idk

	/**
	 * This function checks whether the "FoodItemRepo.txt" file is empty or does not
	 * exist by calling
	 * TextDBFood.isEmptyFile().
	 * 
	 * @return boolean, true if file is not empty, false if file is empty or does
	 *         not exist.
	 */
	public static boolean isEmpty() {
		return TextDBFood.isEmptyFile(FOOD_REPO_FILEPATH);
	}

	/**
	 * Loads food item list from menu CSV file. Additionally initialises the
	 * respective FoodItem entity objects.
	 * 
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

	public static void addToFoodRepo(String FILEPATH, List al) throws IOException {
		try {
			TextDBFood.saveFood(FILEPATH, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	public static void loadMenuItemsFromFood() {
		try {
			ArrayList branchList = TextDBBranch.readBranch("BranchRepo.txt", false);
			ArrayList foodRepoList = TextDBFood.readFoodList("FoodItemRepo.txt");
			for (int i = 0; i < branchList.size(); i++) {
				List menuList = new ArrayList();
				String branchName = (String) branchList.get(i);
				String FILENAME = branchName + "MenuListRepo.txt";
				for (int j = 0; j < foodRepoList.size(); j++) {
					FoodItem foodObj = (FoodItem) foodRepoList.get(j);
					String branch = foodObj.getFoodItemBranch();
					if (branch.equals(branchName)) {
						menuList.add(foodObj);
					}
				}
				TextDBFood.saveFood(FILENAME, menuList);

			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	public static void loadMenuItems(List al) {
		try {
			ArrayList branchList = TextDBBranch.readBranch("BranchRepo.txt", false);

			for (int i = 0; i < branchList.size(); i++) {
				List menuList = new ArrayList();
				String branchName = (String) branchList.get(i);
				String FILENAME = branchName + "MenuListRepo.txt";
				for (int j = 0; j < al.size(); j++) {
					FoodItem foodObj = (FoodItem) al.get(j);
					String branch = foodObj.getFoodItemBranch();
					if (branch.equals(branchName)) {
						menuList.add(foodObj);
					}
				}
				TextDBFood.saveFood(FILENAME, menuList);

			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	public static void addToFoodRepo(String name, double price, String branch, ItemType itemType, String desc)
			throws IOException {
		try {
			ArrayList currentFood = TextDBFood.readFoodList("FoodItemRepo.txt");
			for(int i = 0; i < currentFood.size(); i++)
			{
				FoodItem foodObj = (FoodItem) currentFood.get(i);
				String foodBranch = foodObj.getFoodItemBranch();
				String foodName = foodObj.getFoodItemName();
				if(foodBranch.equals(branch) && foodName.equals(name))
				{
					System.out.println("Error! Cannot add duplicate items to the same menu!");
					return;
				}
			}
			List<FoodItem> foodItems = TextDBFood.readFoodList("FoodItemRepo.txt");
			FoodItem newFoodItem = new FoodItem(name, price, branch, itemType, desc);
			foodItems.add(newFoodItem);
			TextDBFood.saveFood("FoodItemRepo.txt", foodItems);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	public static void updateMenuItem(String oldName, String newName, double newPrice, String newDescription,
			String newBranch, ItemType newItemType) {
		try {
			List<FoodItem> foodItems = TextDBFood.readFoodList("FoodItemRepo.txt");
			boolean updated = false;
			for (int i = 0; i < foodItems.size(); i++) {
				FoodItem foodItem = foodItems.get(i);
				if (foodItem.getFoodItemName().equals(oldName) && foodItem instanceof FoodItem) {
					if (!newName.isEmpty()) {
						foodItem.setFoodItemName(newName);
					}
					if (newPrice != 0.0) {
						foodItem.setPrice(newPrice);
					}
					if (!newBranch.isEmpty()) {
						foodItem.setFoodItemBranch(newBranch);
					}
					if (!newDescription.isEmpty()) {
						foodItem.setDescription(newDescription);
					}
					if (newItemType != null) {
						foodItem.setFoodItemType(newItemType);
					}
					foodItems.set(i, foodItem);
					TextDBFood.saveFood("FoodItemRepo.txt", foodItems);
					System.out.println("Food item details updated successfully.");
					updated = true;
					break;
				}
			}
			if (!updated) {
				System.out.println("Food Item not found.");
			}
		} catch (Exception e) {
			System.out.println("Error editing food: " + e.getMessage());
		}
	}

	public static void removeMenuItem(String name) {
		try {
			List<FoodItem> foodItems = TextDBFood.readFoodList("FoodItemRepo.txt");
			boolean found = false;
			for (int i = 0; i < foodItems.size(); i++) {
				FoodItem foodItem = foodItems.get(i);
				if (foodItem.getFoodItemName().equals(name) && foodItem instanceof FoodItem) {
					foodItems.remove(i);
					TextDBFood.saveFood("FoodItemRepo.txt", foodItems);
					System.out.println("Food item with name '" + name + "' has been removed.");
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Food item with name '" + name + "' not found.");
			}
		} catch (IOException e) {
			System.out.println("Error removing food: " + e.getMessage());
		}
	}

}
