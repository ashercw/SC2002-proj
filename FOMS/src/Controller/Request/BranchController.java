package Controller.Request;

import Entity.Branch;
import Entity.User.Staff;
import Entity.User.Manager;
import java.util.List;
import java.util.ArrayList;
import Others.IO; // Assume IO operations for reading/writing data
import Others.TextDBBranch;

public class BranchController {
    private List<Branch> branches = new ArrayList<>();

    public BranchController() {
        loadBranches(); // Loads branches from storage
    }

    public void addStaffToBranch(String staffId, String branchName) {
        // Find the branch
        Branch branch = branches.stream()
            .filter(b -> b.getBranchName().equals(branchName))
            .findFirst()
            .orElse(null);

        if (branch != null) {
            // Assuming staff details are fetched or created elsewhere
            Staff newStaff = fetchStaffById(staffId); // Implement fetchStaffById according to your data source
            if (newStaff != null) {
                List<Staff> staffList = branch.getStaffList();
                if (!staffList.contains(newStaff)) {
                    staffList.add(newStaff);
                    newStaff.setBranch(branchName);
                    saveBranches();  // Save updates
                }
            }
        }
    }

    public void promoteStaffToManager(String staffId, String branchName) {
        Branch branch = branches.stream()
            .filter(b -> b.getBranchName().equals(branchName))
            .findFirst()
            .orElse(null);

        if (branch != null) {
            Staff staff = branch.getStaffList().stream()
                .filter(s -> s.getLoginID().equals(staffId))
                .findFirst()
                .orElse(null);

            if (staff != null && !staff.getPromotion()) {
                staff.setPromotion(true);
                // Assuming Manager extends Staff
                Manager newManager = new Manager(staff.getEmployeeName(), staff.getLoginID(), staff.getEmployeeType(),
                                                staff.getGender(), staff.getAge(), branchName, staff.getPassword());
                branch.getManagerList().add(newManager);
                branch.getStaffList().remove(staff);
                saveBranches();
            }
        }
    }

    public void transferStaff(String staffId, String fromBranchName, String toBranchName) {
        Branch fromBranch = branches.stream()
            .filter(b -> b.getBranchName().equals(fromBranchName))
            .findFirst()
            .orElse(null);
        Branch toBranch = branches.stream()
            .filter(b -> b.getBranchName().equals(toBranchName))
            .findFirst()
            .orElse(null);

        if (fromBranch != null && toBranch != null) {
            Staff staff = fromBranch.getStaffList().stream()
                .filter(s -> s.getLoginID().equals(staffId))
                .findFirst()
                .orElse(null);

            if (staff != null) {
                fromBranch.getStaffList().remove(staff);
                toBranch.getStaffList().add(staff);
                staff.setBranch(toBranchName);
                saveBranches();
            }
        }
    }

    private void loadBranches() {
        // Implement loading logic
    }

    private void saveBranches() {
        // Implement saving logic
    }
}
