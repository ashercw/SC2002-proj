package Controller.Request;

import Entity.Branch;
import Entity.Food.FoodItem;
import Entity.Food.ItemType;
import Entity.User.*;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import Others.IO;
import Others.TextDB;
import Boundary.AttributeGetter;
import Controller.Account.*;

/**
 * Handles any sort of data control/ manipulation for branch entities.
 * 
 * @author Christian Asher, Saffron Lim
 */

public class BranchController {

	public static String FILEPATH = "FOMS\\src\\Others\\branch_listCSV.csv";

	/**
	 * 
	 * @param branchName
	 */
	public Branch addBranch(String branchName) {
		// TODO - implement BranchController.addBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branchId
	 * @param newName
	 */
	public void updateBranch(String branchId, String newName) {
		// TODO - implement BranchController.updateBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branchId
	 */
	public void removeBranch(String branchId) {
		// TODO - implement BranchController.removeBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * Loads branch list from branch list CSV file. Additionally initialises the
	 * respective Branch entity objects.
	 * INCOMPLETE
	 */

	public static void loadBranches() {
		List al = new ArrayList();

		List<List<String>> empList = IO.readCSV(FILEPATH);

		for (List<String> str : empList) {
			String name = str.get(0);
			String location = str.get(1);
			int quota = Integer.parseInt(str.get(2));

			Branch branchObj = new Branch(name, location, quota);
			al.add(branchObj);

		}
		// add to repo
		addToBranchRepo("BranchRepo.txt", al);

	}

	/**
	 * This function writes branch data to a txt file for data storage purposes.
	 * 
	 * @param FILEPATH is the String directory of the file to write to.
	 * @param al       is a List of Branch objects whose content will be saved in
	 *                 the txt file.
	 * 
	 */
	public static void addToBranchRepo(String FILEPATH, List al) {
		TextDB txtDB = new TextDB();
		try {

			TextDB.saveBranch(FILEPATH, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
	 * Creates a staff list for each branch. This function is called by loadEmployees() in AccountController.
	 * @param empList a List<List<String>> of all the employees.
	 */
	public static void createStaffListbyBranch(List<List<String>> empList) {
		
		try {
			ArrayList branchList = TextDB.readBranch("BranchRepo.txt", false);

			for (int i = 0; i < branchList.size(); i++) {
				List childBranchList = new ArrayList();
				System.out.println( branchList.get(i));
				String branchName = (String) branchList.get(i);
				String FILENAME = branchName + "StaffList.txt";
				for(List<String> str : empList)
				{
					String role = str.get(2);
					if(!role.equals("A") && str.get(5).equals(branchName))
					{
						childBranchList.add(AccountController.createEmployeeObj(str, role));
					}
				}
				TextDB.saveEmployee(FILENAME, childBranchList);

			}
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

}