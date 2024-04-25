package Boundary.Account;
/**
 * This class prints out the menu for changing the password.
 * @author Saffron Lim
 */
public class ChangePassword {

	/**
	 * This function prints out the menu for changing the password.
	 */
	public static void dispChangePassword() {
		System.out.println("\n\n");
		System.out.println("Your new password must meet the following requirements:");
		System.out.println("\t1) Must be longer than 12 characters.");
		System.out.println("\t2) Must have at least one special symbol (@, #, $, %, etc.).");
		System.out.println("\t3) Must have at least one numeric digit (0-1).");
		System.out.println("\t4) Must contain a mix of upper and lowercase letters (a-z, A-Z).");

	}

	/**
	 * Prints error message.
	 */
	public static void dispDefaultPasswordErr()
	{
		System.out.println("New password cannot be default password!");
	}

	/**
	 * Prints success message.
	 */
	public static void dispSuccess()
	{
		System.out.println("Password has been successfully changed. Please login again!");
	}

}