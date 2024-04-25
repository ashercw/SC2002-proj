package Boundary;

import java.io.IOException;
import java.util.ArrayList;

import Entity.Food.FoodItem;
import Others.IO;
import Others.TextDBFood;

public class MenuDisplay {

	/**
	 * Allows user to look at the branch menu.
	 */
	public static void menuBrowsing(String branch) {
		IO.printNewLine(5);
		IO.displayDivider();
		System.out.println("\t\t" + branch + " MENU");
		IO.displayDivider();
		System.out.format("%-8s", "No.");
		System.out.format("%-15s", "Item");
		System.out.println("Price");
		//System.out.format("", "Price\n");

		String filePath = branch + "MenuListRepo.txt";
		try {
			ArrayList menuList = TextDBFood.readFoodList(filePath);
			FoodItem food = new FoodItem();
			for (int i = 0; i < menuList.size(); i++)
			{
				food = (FoodItem) menuList.get(i);
				System.out.format("%-8s", (i+1));
				System.out.format("%-15s", food.getFoodItemName());
				System.out.println("$"+ food.getFoodItemPrice());
				System.out.format("%-8s", "");
				System.out.format("%-15s", food.getFoodItemType());
				System.out.println( food.getFoodItemDesc());
				System.out.print("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


}