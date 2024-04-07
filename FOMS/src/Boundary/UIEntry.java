package Boundary;
import Controller.AccountController;
import Controller.MenuController;

public class UIEntry {

	/*private boolean firstStart() {
		//TODO - implement UIEntry.firstStart
		throw new UnsupportedOperationException();
	}*/

	public static void UIstart() {

		boolean isFirstStart = false; //placeholder
		//TODO - write functions to check if the various repositories are empty.

		if(isFirstStart)
		{
			// initialise repositories
			AccountController.loadEmployees();
			MenuController.loadMenuItems();
		}

		StartMenuUI.startMenuUI();
	}

}