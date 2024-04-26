package Entity.Food;
import java.util.ArrayList;

/**
 * Represents the menu for the restaurant.
 * This class holds the branch name and a list of food items offered.
 */
public class Menu {
    private String branchName;
	private ArrayList<FoodItem> menuList;

	//Constructor
	public Menu(String _b, ArrayList<FoodItem> _menuList)
	{
		this.branchName = _b;
		this.menuList = _menuList;
	}
	
	//Accessors and mutators
	public String getLocation() 
	{
		return this.branchName;
	}
	
	public ArrayList<FoodItem> getMenuList()
	{
		return this.menuList;
	}
}
