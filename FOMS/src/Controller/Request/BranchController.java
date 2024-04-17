package Controller.Request;

import Entity.Branch;
import Entity.User.Staff;
import Entity.User.Manager;
import java.util.List;

import Controller.Account.AccountController;

import java.io.IOException;
import java.util.ArrayList;
import Others.IO; // Assume IO operations for reading/writing data
import Others.TextDB;
import Others.TextDBBranch;
import Others.TextDBStaff;



public class BranchController {

    private List<Branch> branches = new ArrayList<>();
    public static String FILEPATH = "FOMS\\src\\Others\\branch_listCSV.csv";


    /**
     * Loads branch list from branch list CSV file. Additionally initialises the
     * respective Branch entity objects.
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
     * @param al       is a List of Branch objects whose content will be saved in
     *                 the txt file.
     * 
     */
    public static void addToBranchRepo(String FILEPATH, List al) {
        try {

            TextDBBranch.saveBranch(FILEPATH, al);
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }

    /**
     * 
     * @param FILEPATH
     * @param al       is a List of employee objects
     * 
     */
    public static void createStaffListbyBranch(List<List<String>> empList) {

        try {
            ArrayList branchList = TextDBBranch.readBranch("BranchRepo.txt", false);

            for (int i = 0; i < branchList.size(); i++) {
                List childBranchList = new ArrayList();
                System.out.println(branchList.get(i));
                String branchName = (String) branchList.get(i);
                String FILENAME = branchName + "StaffListRepo.txt";
                for (List<String> str : empList) {
                    String role = str.get(2);
                    if (!role.equals("A") && str.get(5).equals(branchName)) {
                        childBranchList.add(AccountController.createEmployeeObj(str, role));
                    }
                }
                TextDBStaff.saveEmployee(FILENAME, childBranchList);

            }
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }
}
