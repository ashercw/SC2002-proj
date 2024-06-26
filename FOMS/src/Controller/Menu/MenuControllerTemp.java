package Controller.Menu;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDBBranch;
import Others.TextDBFood;

/**
 *  Controls the operations related to menu management, including loading and updating food items from repositories,
 * adding new items, and removing existing items from the menu.
 * 
 * @author Saffron Lim;
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MenuControllerTemp {

	public static String FILEPATH = "FOMS\\src\\Others\\menu_listCSV.csv";
	public static String FOOD_REPO_FILEPATH = "FoodItemRepo.txt";



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

	/**
     * Saves the list of food items to the specified repository file.
     * 
     * @param filePath Path to the file where food items will be saved.
     * @param al List of FoodItem objects to be saved.
     * @throws IOException if there is an error writing to the file.
     */

	public static void addToFoodRepo(String FILEPATH, List al) throws IOException {
		try {
			TextDBFood.saveFood(FILEPATH, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
     * Loads menu items from the food repository for each branch.
     * 
     * @throws IOException if there is an error reading or writing to the files.
     */

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

	/**
     * Processes and saves menu items to repository files for each branch.
     * 
     * @param al List of FoodItem objects to be processed.
     * @throws IOException if there is an error writing to the files.
     */

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

	/**
 * Adds a new food item to the food repository. It checks for duplicates
 * in the existing list of food items based on the branch and name.
 * If a duplicate is found, it does not add the item and prints an error message.
 * 
 * @param name The name of the food item.
 * @param price The price of the food item.
 * @param branch The branch where the food item is available.
 * @param itemType The type of the food item (e.g., DRINK, MAIN, SIDE).
 * @param desc A description of the food item.
 * @throws IOException If there is an issue with reading or writing to the food repository file.
 */

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

	/**
 * Updates the details of an existing food item in the food repository.
 * It replaces the food item's details with new values provided if they are not empty or null.
 * 
 * @param oldName The current name of the food item to update.
 * @param newName The new name for the food item, if not empty.
 * @param newPrice The new price for the food item, if not zero.
 * @param newDescription The new description for the food item, if not empty.
 * @param newBranch The new branch for the food item, if not empty.
 * @param newItemType The new type of the food item, if not null.
 */

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
						System.out.println(foodItem.getFoodItemPrice());
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

	/**
 * Removes a food item from the food repository based on its name.
 * It searches for the food item by name and removes it if found, otherwise prints an error message.
 * 
 * @param name The name of the food item to be removed.
 * @throws IOException If there is an issue with reading or writing to the food repository file.
 */
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
