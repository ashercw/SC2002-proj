package Boundary;
import Controller.Account.AccountController;
import Controller.Menu.MenuControllerTemp;
import Controller.Request.BranchController;

public class UIEntry {

	public static void UIstart() {

		boolean isFirstStart = true; //placeholder
		//TODO - write functions to check if the various repositories are empty.

		if(isFirstStart)
		{
			// initialise repositories
			AccountController.loadEmployees();
			BranchController.loadBranches();
			MenuControllerTemp.loadFoodItems();
		}

		StartMenuUI.startMenuUI();
	}

}