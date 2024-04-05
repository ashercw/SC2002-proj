package Entity.Food;
import java.util.ArrayList;

import Entity.BranchType;

public class Menu {
    private BranchType branchName;
	private ArrayList<FoodItem> menuList;

	//Constructor
	public Menu(BranchType _b, ArrayList<FoodItem> _menuList)
	{
		this.branchName = _b;
		this.menuList = _menuList;
	}
	
	//Accessors and mutators
	public BranchType getLocation() 
	{
		return this.branchName;
	}
	
	public ArrayList<FoodItem> getMenuList()
	{
		return this.menuList;
	}
}
