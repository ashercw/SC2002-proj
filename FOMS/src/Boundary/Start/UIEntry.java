package Boundary.Start;
import java.io.IOException;

import Controller.Account.AccountController;
import Controller.Menu.MenuControllerTemp;
import Controller.Request.BranchController;

/*
 * This class instantiates the repos if needed and calls the startMenuUI 
 * function to display the welcome menu.
 */
public class UIEntry {

	public static void UIstart() throws IOException {

		// checks whether repo text files (database) have been instantiated.
		boolean isFirstStart = AccountController.isEmpty() && AccountController.isEmpty() && BranchController.isEmpty();
		

		if(isFirstStart == false)
		{
			// initialise repositories
			AccountController.loadEmployees();
			BranchController.loadBranches();
			MenuControllerTemp.loadFoodItems();
		}


		// display user menu
		StartMenuUI.startMenuUI();
	}

}