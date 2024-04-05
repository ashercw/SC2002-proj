package Entity.Food;
import java.util.ArrayList;

import Entity.BranchType;

public class Menu {
    private BranchType branchName;
	private ArrayList<FoodItem> menuList;
	
	public BranchType getLocation() 
	{
		return this.branchName;
	}
	
	public ArrayList<FoodItem> getMenuList()
	{
		return this.menuList;
	}
}
