package Controller.Request;

import java.util.*;
import Boundary.AdminMainPage;
import Entity.User.EmployeeType;
import Entity.User.Gender;
import Entity.User.Staff;
import Entity.User.User;
import Entity.User.UserDatabase;
import Entity.BranchDatabase;
import Entity.Branch;
import Entity.User.Manager;
import Entity.BranchType;
import Entity.Order.Payment;
import Controller.Request.BranchController;
import Controller.Request.PaymentController;
import java.util.stream.Collectors;
import Controller.Account.AddEmployee;

public class AdminController {
	private List<User> staffList;
	private UserDatabase userDatabase;
	private BranchDatabase branchDatabase;
	private BranchController branchController;
	private PaymentController paymentController;
	private AddEmployee addEmployee;

	public AdminController(UserDatabase userDatabase, BranchDatabase branchDatabase, AddEmployee addEmployee) {
		this.userDatabase = userDatabase;
		this.branchDatabase = branchDatabase;
		this.addEmployee = addEmployee;
	}

	/*
	 * @param filterCriterion Filter criterion (1: Gender, 2: Branch, 3: Role)
	 * 
	 * @param filterValue Filter value
	 * 
	 * @param branchName The name of the new branch
	 * 
	 * @param userId The ID of the staff to edit
	 * 
	 * @param managerId the ID of the manager to be assigned
	 * 
	 * @param newName The new name of the staff
	 * 
	 * @param newAge The new age of the staff
	 * 
	 * @param newGender The new gender of the staff
	 * 
	 * @param newRole The new role of the staff
	 * 
	 * @param newBranch The new branch of the staff
	 */
	private List<Branch> branches;

	public AdminController() {
		this.branches = new ArrayList<>();
		this.paymentController = new PaymentController();
	}

	public Staff addStaff(String username, String password, String name, String age, String gender, String branch,
			EmployeeType role) {
		int ageInt = Integer.parseInt(age);

		Staff staff = new Staff(name, username, role, gender, age, branch, password);
		System.out.println("Staff added successfully!");
		return staff;
	}

	public void editStaff(String userId, String newName, int newAge, Gender newGender, EmployeeType newRole,
			String newBranch) {
		for (int i = 0; i < staffList.size(); i++) {
			User user = staffList.get(i);
			if (user.getLoginID().equals(userId) && user instanceof Staff) {
				Staff staff = (Staff) user;
				Staff updatedStaff = new Staff(staff.getEmployeeName(), newName, newRole, newGender.toString(),
                    String.valueOf(newAge), newBranch, staff.getPassword());
				staffList.set(i, updatedStaff);
				System.out.println("Staff details updated successfully.");
				return;
			}
		}
		System.out.println("Staff not found with the provided ID.");
	}

	public void removeStaff(String userId) {
		for (int i = 0; i < staffList.size(); i++) {
			User user = staffList.get(i);
			if (user.getLoginID().equals(userId) && user instanceof Staff) {
				Staff staff = (Staff) user;
				staffList.remove(i);
				System.out.println("Staff member with ID '" + userId + "' has been removed.");
				return;
			}
		}
		System.out.println("Staff member with ID '" + userId + "' not found.");
	}

	public void assignManagerToBranch(String managerId, Branch branch, String branchType) {

		Manager manager = null;

		for (User user : staffList) {
			if (user instanceof Manager && user.getLoginID().equals(managerId)) {
				manager = (Manager) user;
				break;
			}
		}

		if (manager.getBranch() != null) {
			System.out.println("Manager " + manager.getEmployeeName() + " is already assigned to a branch.");
			return;
		}

		ArrayList<Manager> branchManagers = branch.getManagerList();

		if (!branchManagers.isEmpty()) {
			System.out.println("The branch already has a manager assigned.");
			return;
		}

		manager.setBranch(branchType);
		System.out.println("Manager " + manager.getEmployeeName() + " has been assigned to branch " + branchType + ".");
	}

	public void promoteStaffToManager(String staffId, String branchName, EmployeeType managerRole) {
		User staffToPromote = null;
		for (User user : staffList) {
			if (user.getLoginID().equals(staffId) && user instanceof Staff) {
				staffToPromote = user;
				break;
			}
		}

		if (staffToPromote == null) {
			System.out.println("Staff member with ID " + staffId + " not found.");
			return;
		}

		staffList.remove(staffToPromote);

		Manager promotedManager = new Manager(
				staffToPromote.getEmployeeName(),
				staffToPromote.getLoginID(),
				managerRole,
				staffToPromote.getGender(),
				staffToPromote.getAge(),
				branchName,
				staffToPromote.getPassword());

		((Staff) staffToPromote).isPromoted = true;

		Branch branch = new Branch(branchName, null, new ArrayList<>(Collections.singletonList(promotedManager)), 0, 1,
				null);

		branch.getManagerList().add(promotedManager);

		System.out.println("Staff member " + staffId + " has been promoted to branch manager.");

	}

	public void transferUserToBranch(String userId, Branch newBranchName, Branch currentBranchName) {
		Staff staffToTransfer = null;
		for (Staff staff : currentBranchName.getStaffList()) {
			if (staff.getLoginID().equals(userId)) {
				staffToTransfer = staff;
				break;
			}
		}

		if (staffToTransfer == null) {
			System.out.println("Staff with ID '" + userId + "' not found in the current branch.");
			return;
		}

		Staff transferredStaff = new Staff(staffToTransfer.getEmployeeName(), staffToTransfer.getLoginID(),
				staffToTransfer.getEmployeeType(), staffToTransfer.getGender(),
				staffToTransfer.getAge(), newBranchName.getBranchType(),
				staffToTransfer.getPassword());

		transferredStaff.setBranch(newBranchName.getBranchType());

		//addEmployee.addStaff(transferredStaff, newBranchName); // need to add parameter to AddEmployee

		//removeEmployee.removeStaff(staffToTransfer, currentBranchName); // add function of removeStaff in AddEmployee

		System.out.println("Staff with ID '" + userId + "' transferred to branch '" + newBranchName.getBranchType() + "'.");
	}

	public void addPaymentMethod(String userId, String paymentMethod) {
		// need to add "getPaymentMethods" in User Class and getLoginID with parameter
		/*
		User user = getLoginID(userId);
		if (user == null) {
			System.out.println("User object is null.");
			return;
		}

		List<Payment> paymentMethods = user.getPaymentMethods();

		if (paymentMethods.contains(paymentMethod)) {
			System.out.println("Payment method already exists for the user.");
			return;
		}

		paymentMethods.addAll(paymentMethod);

		System.out.println("Payment method added successfully.");
 		*/
	}

	public void openBranch(String branchName) {
		Branch newBranch = new Branch(branchName, new ArrayList<>(staffList), new ArrayList<>(), 0, 0, null);
		branchController.addBranch(newBranch);
		System.out.println("Branch '" + branchName + "' has been opened.");
	}

	public void closeBranch(String branchName) {
		//List<Branch> branches = branchController.getAllBranches(); // add getAllBranches to branchController
		for (Branch branch : branches) {
			if (branch.getBranchType().equals(branchName)) {
				//branchController.removeBranch(branch.getId()); // add getId for Branch (because closebranch requires
																// branchID)
				System.out.println("Branch '" + branchName + "' has been closed.");
				return;
			}
		}
		System.out.println("Branch '" + branchName + "' not found.");
	}

	public void displayStaffList(int filterCriterion, String filterValue, boolean sortByAge) {
		System.out.println("===== Displaying Staff List =====");
		System.out.println("Filter Criterion: " + filterCriterion);
		System.out.println("Filter Value: " + filterValue);
		System.out.println("Sort By Age: " + sortByAge);

		// Filter staff list
		List<User> filteredList = new ArrayList<>();
		switch (filterCriterion) {
			case 1: // Gender
				Gender gender = Gender.valueOf(filterValue.toUpperCase());
				filteredList = staffList.stream()
						.filter(user -> Gender.valueOf(user.getGender().toUpperCase()) == gender)
						.collect(Collectors.toList());
				break;
			case 2: // Branch
				filteredList = staffList.stream()
						.filter(user -> {
							if (user instanceof Staff) {
								return ((Staff) user).getBranch().equals(filterValue);
							} else {
								return false;
							}
						})
						.collect(Collectors.toList());
				break;
			case 3: // Role
				EmployeeType employeeType = EmployeeType.valueOf(filterValue.toUpperCase());
				filteredList = staffList.stream()
						.filter(user -> user.getEmployeeType() == employeeType)
						.collect(Collectors.toList());
				break;
			default:
				System.out.println("Invalid filter criterion.");
				return;
		}

		if (sortByAge) {
			filteredList.sort(Comparator.comparingInt(user -> Integer.parseInt(user.getAge())));
		}

		if (filteredList.isEmpty()) {
			System.out.println("No staff found matching the criteria.");
		} else {
			System.out.println("===== Staff List =====");
			for (User user : filteredList) {
				System.out.println(user);
			}
		}
	}

	public Branch getBranchByName(String branchName){
		for (Branch branch : branches) {
            if (branch.getBranchType().equals(branchName)) {
                return branch;
            }
        }
		return null;
	}
}
