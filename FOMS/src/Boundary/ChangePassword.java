package Boundary;
/**
 * @author Saffron Lim
 */
public class ChangePassword {

	/**
	 * 
	 * @param UserType
	 */
	public static void dispChangePassword() {
		System.out.println("\n\n\n\n\n");
		System.out.println("Your new password must meet the following requirements:");
		System.out.println("\t1) Must be longer than 12 characters.");
		System.out.println("\t2) Must have at least one special symbol (@, #, $, %, etc.).");
		System.out.println("\t3) Must have at least one numeric digit (0-1).");
		System.out.println("\t4) Must contain a mix of upper and lowercase letters (a-z, A-Z).");
		System.out.println("\nPlease enter a new password.");

	}

	public static void dispDefaultPasswordErr()
	{
		System.out.println("New password cannot be default password!");
	}

	public static void dispSuccess()
	{
		System.out.println("Password has been successfully changed. Please login again!");
	}

}