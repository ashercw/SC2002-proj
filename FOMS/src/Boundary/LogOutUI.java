package Boundary;

/**
 * The LogOutUI class displays the thank you message that the user will see
 * upon exiting FOMS.
 * @author Saffron Lim, Reuben Farrel
 */

public class LogOutUI {

	
	/**
	 * Prints the thank you logo for the exit page.
	 * ASCII art generating from: https://patorjk.com/software/taag/#p=display&f=Big%20Money-sw&t=Thank%20you!%20%3C%203
	 */

	private static void printASCIILogo()
	{
		System.out.println("""
			________  __                            __                                            __           __         ______  
			/        |/  |                          /  |                                          /  |         /  |       /      \\ 
			$$$$$$$$/ $$ |____    ______   _______  $$ |   __        __    __   ______   __    __ $$ |        /$$/       /$$$$$$  |
			   $$ |   $$      \\  /      \\ /       \\ $$ |  /  |      /  |  /  | /     \\ /  |  /  |$$ |       /$$/        $$ ___$$ |
			   $$ |   $$$$$$$  | $$$$$$  |$$$$$$$  |$$ |_/$$/       $$ |  $$ |/$$$$$$  |$$ |  $$ |$$ |      /$$<           /   $$< 
			   $$ |   $$ |  $$ | /    $$ |$$ |  $$ |$$   $$<        $$ |  $$ |$$ |  $$ |$$ |  $$ |$$/       $$  \\         _$$$$$  |
			   $$ |   $$ |  $$ |/$$$$$$$ |$$ |  $$ |$$$$$$  \\       $$ \\__$$ |$$ \\__$$ |$$ \\__$$ | __        $$  \\       /  \\__$$ |
			   $$ |   $$ |  $$ |$$    $$ |$$ |  $$ |$$ | $$  |      $$    $$ |$$    $$/ $$    $$/ /  |        $$  |      $$    $$/ 
			   $$/    $$/   $$/  $$$$$$$/ $$/   $$/ $$/   $$/        $$$$$$$ | $$$$$$/   $$$$$$/  $$/          $$/        $$$$$$/  
																	/  \\__$$ |                                                     
																	$$    $$/                                                      
																	 $$$$$$/                                                       
				""");
	}


	/**
	 * Displays the thank you message and exits the System.
	 */

	public static void LogOut() {
		printASCIILogo();
		System.out.println("\n See you soon!");
		System.exit(0);
	}

}