package Entity.User;

import Entity.BranchDatabase;
import Entity.Branch;
import Entity.Manager;
import Entity.BranchType;
import Entity.EmployeeType;

public class Admin extends User {

    // Constructor
    public Admin(String _en, String _lID, EmployeeType _et, String _g, String _a, String _p) {
        super(_en, _lID, _et, _g, _a, _p);
        System.out.println("Creating admin" + _en);
    }

    // Accessors and mutators
    public String getEmployeeName() {
        return this.empName;
    }

    public String getLoginID() {
        return this.loginID;
    }

    public EmployeeType getEmployeeType() {
        return this.empType;
    }

    public Gender getGender() {
        return this.gender;
    }

    public int getAge() {
        return this.age;
    }

    public BranchType getBranch() {
        return this.branch;
    }

    // Methods
    public void closeBranch(BranchType branch) {
        BranchDatabase.removeBranch(branch); // add removebranch method in branch database
    }

    public void displayStaffList(BranchType branch, EmployeeType role, Gender gender, int age) {
        List<EmployeeUser> staffList = UserDatabase.getStaffList();
        for (EmployeeUser user : staffList) {
            if (user.getBranch() == branch && user.getEmployeeType() == role && user.getGender() == gender
                    && user.getAge() == age) {
                System.out.println(user.getEmployeeName());
            }
        }
    }

    public void assignManagersToBranches(BranchType branch, Manager manager) {
        branch.setManager(manager);

        System.out.println("Assigned manager " + manager.getEmployeeName() + " to branch " + branch.getName());

        // add to BranchController:
        // Branch branch = BranchDatabase.getBranchById(1); // Get branch by ID
        // Manager manager = UserDatabase.getManagerById(1); // Get manager by ID
        // assignManagersToBranches(branch, manager);
    }

    public void promoteToBranchManager(String staffName) {
        Staff staff = UserDatabase.findUserByName(staffName); // add findUserbyName in UserDatabase
        if (staff != null) {
            Manager newManager = new Manager(staff.getEmployeeName(), staff.getLoginID(), staff.getGender(),
                    staff.getAge(), staff.getBranch());
            UserDatabase.removeStaff(staff);
            UserDatabase.addManager(newManager);
        }
    }

    public void transferStaff(String staffName, BranchType targetBranch) {
        // Get the user by name
        EmployeeUser user = UserDatabase.findUserByName(staffName); // add findUserByName in UserDatabase

        BranchType currentBranch = user.getBranch();
        // Remove the user from the current branch
        if (currentBranch != null) {
            currentBranch.removeEmployee(user);
        } else {
            System.out.println("Employee " + staffName + " is not assigned to any branch.");
            return;
        }
        // Add the user to the new branch
        targetBranch.addEmployee(user);

        // Update the user's branch
        user.setBranch(targetBranch);

        System.out.println("Transferred employee " + staffName + " to " + targetBranch.getName());
    }
    // add to BranchController:
    // Branch branch = BranchDatabase.getBranchById(1); // Get branch by ID
    // Manager manager = UserDatabase.getManagerById(1); // Get manager by ID
    // transferStaff(staffName, targetBranch);

}
