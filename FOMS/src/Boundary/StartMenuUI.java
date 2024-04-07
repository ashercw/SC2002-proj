package Boundary;
import Others.UserInput;

/**
 * The StartMenuUI class displays the starting UI that the user will see
 * upon first starting up FOMS.
 * @author Saffron Lim, Reuben Farrel
 */

// TODO - implement employee login, customer "login" in startMenuUI();


public class StartMenuUI {

	/**
	 * Prints out the FOMS logo for the welcome page. 
	 * ASCII art generated from: https://patorjk.com/software/taag/#p=display&f=Big%20Money-nw&t=FOMS
	 */
	private static void printASCIILogo()
	{
		System.out.println("""

		$$$$$$$$\\  $$$$$$\\  $$\\      $$\\  $$$$$$\\  
		$$  _____|$$  __$$\\ $$$\\    $$$ |$$  __$$\\ 
		$$ |      $$ /  $$ |$$$$\\  $$$$ |$$ /  \\__|
		$$$$$\\    $$ |  $$ |$$\\$$\\$$ $$ |\\$$$$$$\\  
		$$  __|   $$ |  $$ |$$ \\$$$  $$ | \\____$$\\ 
		$$ |      $$ |  $$ |$$ |\\$  /$$ |$$\\   $$ |
		$$ |       $$$$$$  |$$ | \\_/ $$ |\\$$$$$$  |
		\\__|       \\______/ \\__|     \\__| \\______/ 
												   

		""");
	}

	/**
	 * Displays the starting UI and gets user input on which FOMS function they
	 * wish to use: Login as Employee, Continue as Customer, Exit FOMS.
	 */
	public static void startMenuUI() {
		

		printASCIILogo();
		System.out.println("Welcome to the Fastfood Ordering Management System!\n");
        System.out.println("What would you like to do today?");
        System.out.println("\t1. Login as Employee");
        System.out.println("\t2. Continue as Customer");
        System.out.println("\t3. Exit FOMS");
        System.out.print("Your choice (1-3): ");

		while(true)
		{
			int userChoice = UserInput.userInputInt();

			if(userChoice == 1) { System.out.println("Login as employee");} //Call employee login 
			else if(userChoice == 2) {System.out.println("Continue as customer");} //Call customer "login"
			else if(userChoice == 3) { LogOutUI.LogOut();} //
			else System.out.println("Invalid response. Please enter 1-3!");
			System.out.print("Your choice (1-3): ");
		}

	}

}