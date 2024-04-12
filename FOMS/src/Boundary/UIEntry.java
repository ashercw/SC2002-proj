package Boundary;
import Controller.Account.AccountController;
import Controller.Menu.MenuController;
import Controller.Request.BranchController;

public class UIEntry {

	/*private boolean firstStart() {
		//TODO - implement UIEntry.firstStart
		throw new UnsupportedOperationException();
	}*/

	public static void UIstart() {

		boolean isFirstStart = true; //placeholder
		//TODO - write functions to check if the various repositories are empty.

		if(isFirstStart)
		{
			// initialise repositories
			AccountController.loadEmployees();
			BranchController.loadBranches();
			
			//MenuController.loadMenuItems();
		}

		StartMenuUI.startMenuUI();
	}

}