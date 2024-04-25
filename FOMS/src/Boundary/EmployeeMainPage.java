package Boundary;
import Others.IO;
import Entity.User.EmployeeType;

import java.io.IOException;

import Boundary.Account.AttributeGetter;
import Controller.Account.AccountController;

/**
 * This class allows the employee (Staff, Admin, Manager) to select their doman for login. 
 * It then accepts their password and loginID as user input
 * @author Saffron Lim, Reuben Farrel
 */
public class EmployeeMainPage {

	/**
	 * Displays the menu that allows employees to login and calls the respective functions needed
	 * for logging in.
	 * @throws IOException 
	 */
	public static void displayEmployeeMainPage() throws IOException {
		System.out.println("\t    EMPLOYEE LOGIN");
		IO.displayDivider();
		System.out.println("\t1) Login as Staff");
		System.out.println("\t2) Login as Manager");
		System.out.println("\t3) Login as Admin");
		System.out.println("\t4) Go Back");
		IO.displayDivider();

		//Get employee type: S, M, A
		EmployeeType domain = AttributeGetter.getEmployeeType();
		if(domain == null) return;

		//Login
		
		AccountController.login(domain);
		
	}

}