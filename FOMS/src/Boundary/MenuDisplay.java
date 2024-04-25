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

		IO.displayDivider();
		System.out.println("\t" + branch + " MENU");
		IO.displayDivider();
		System.out.print("No.");
		System.out.format("%-8", "Item");
		System.out.format("%-15", "Price");

		String filePath = branch + "MenuListRepo";
		try {
			ArrayList menuList = TextDBFood.readFoodList(filePath);
			FoodItem food = new FoodItem();
			for (int i = 0; i < menuList.size(); i++)
			{
				food = (FoodItem) menuList.get(i);
				System.out.print("\t" + (i+1) + ".");
				System.out.format("%-8", food.getFoodItemName());
				System.out.format("%-8", food.getFoodItemPrice());
				System.out.format("\n%-8", food.getFoodItemType());
				System.out.print("   " + food.getFoodItemDesc());
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static void printTable(FoodItem food)
	{
		
	}

}