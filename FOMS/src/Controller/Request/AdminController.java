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
import Entity.Order.Payment;
import Controller.Request.BranchController;
import Controller.Request.PaymentController;
import java.util.stream.Collectors;
import Controller.Account.AddEmployee;

/**
 * @author Reuben
 */

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

		((Staff) staffToPromote).setPromotion = true;

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
				staffToTransfer.getAge(), newBranchName.getBranchName(),
				staffToTransfer.getPassword());

		transferredStaff.setBranch(newBranchName.getBranchName());

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
			if (branch.getBranchName().equals(branchName)) {
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
			System.out.prpackage Controller.Request;

import java.io.IOException;
import java.util.*;
import Entity.User.EmployeeType;
import Entity.User.Gender;
import Entity.User.Staff;
import Entity.User.User;
import Entity.Branch;
import Entity.User.Manager;
import Entity.Order.Payment;
import Controller.Request.PaymentController;
import java.util.stream.Collectors;
import Others.TextDBBranch;
import Others.TextDBStaff;
import Entity.Order.CreditandDebit;
import Entity.Order.Paynow;

/**
 * @author Reuben Farrel
 */

public class AdminController {
	private List<Payment> paymentMethods;

    public AdminController() {
        paymentMethods = new ArrayList<>();
        paymentMethods.add(new CreditandDebit());
        paymentMethods.add(new Paynow());
    }
	public boolean addStaff(String username, String password, String name, String age, String gender, String branch,
			EmployeeType role) {
		boolean added = false;
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt"); // Read existing staff data
			for (User user : employees) {
				if (user.getLoginID().equals(username)) {
					return false; // Staff already exists
				}
			}

			Staff newStaff = new Staff(name, username, role, gender, age, branch, password);
			employees.add(newStaff); // Adding staff to the list
			TextDBStaff.saveEmployee("TextDBStaff.txt", employees); // Save updated staff data to the file
			added = true;
		} catch (IOException e) {
			System.out.println("Error adding staff: " + e.getMessage());
			added = false;
		}
		return added;
	}

	public void editStaff(String userId, String newName, String newAge, String newGender, EmployeeType newRole,
			String newBranch) {
		try {
			// Read existing staff data
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
			boolean found = false;
			for (int i = 0; i < employees.size(); i++) {
				User user = employees.get(i);
				if (user.getLoginID().equals(userId) && user instanceof Staff) {
					Staff staff = (Staff) user;
					// Update staff information
					staff.setEmployeeName(newName);
					staff.setAge(newAge);
					staff.setGender(newGender);
					staff.setRole(newRole);
					staff.setBranch(newBranch);
					employees.set(i, staff); // Update staff in the list
					TextDBStaff.saveEmployee("TextDBStaff.txt", employees); // Save updated staff data to the file
					System.out.println("Staff details updated successfully.");
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Staff not found with the provided ID.");
			}
		} catch (IOException e) {
			System.out.println("Error editing staff: " + e.getMessage());
		}
	}

	public void removeStaff(String userId) {
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
			boolean found = false;
			for (int i = 0; i < employees.size(); i++) {
				User user = employees.get(i);
				if (user.getLoginID().equals(userId) && user instanceof Staff) {
					employees.remove(i); // Remove staff from the list
					TextDBStaff.saveEmployee("TextDBStaff.txt", employees); // Save updated staff data to the file
					System.out.println("Staff member with ID '" + userId + "' has been removed.");
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Staff member with ID '" + userId + "' not found.");
			}
		} catch (IOException e) {
			System.out.println("Error removing staff: " + e.getMessage());
		}
	}
	public void assignManagerToBranch(String managerId, String branchName, String branchType) {
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
			Manager manager = null;
			for (User user : employees) {
				if (user instanceof Manager && user.getLoginID().equals(managerId)) {
					manager = (Manager) user;
					break;
				}
			}
			if (manager == null) {
				System.out.println("Manager with ID " + managerId + " not found.");
				return;
			}
			@SuppressWarnings("unchecked")
			List<Branch> branches = TextDBBranch.readBranch("TextDBBranch.txt", true);
	
			if (manager.getBranch() != null) {
				System.out.println("Manager " + manager.getEmployeeName() + " is already assigned to a branch.");
				return;
			}
			Branch branch = null;
			for (Branch b : branches) {
				if (b.getBranchName().equals(branchName)) {
					branch = b;
					break;
				}
			}
			if (branch == null) {
				branch = new Branch(branchName, null, new ArrayList<Manager>(), null);
				branches.add(branch);
			}
			if (!branch.getManagerList().isEmpty()) {
				System.out.println("The branch already has a manager assigned.");
				return;
			}
			manager.setBranch(branchType);
			branch.getManagerList().add(manager);
			System.out.println("Manager " + manager.getEmployeeName() + " has been assigned to branch " + branchName + ".");
			TextDBStaff.saveEmployee("TextDBStaff.txt", employees);
			TextDBBranch.saveBranch("TextDBBranch.txt", branches);
		} catch (IOException e) {
			System.out.println("Error assigning manager to branch: " + e.getMessage());
		}
	}

	public boolean promoteStaffToManager(String staffId, String branchName, EmployeeType managerRole) {
		boolean promoted = false;
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
			User staffToPromote = null;
			for (User user : employees) {
				if (user.getLoginID().equals(staffId) && user instanceof Staff) {
					staffToPromote = user;
					break;
				}
			}
			if (staffToPromote == null) {
				System.out.println("Staff member with ID " + staffId + " not found.");
				return false;
			}
			employees.remove(staffToPromote);
	
			Manager promotedManager = new Manager(
					staffToPromote.getEmployeeName(),
					staffToPromote.getLoginID(),
					managerRole,
					staffToPromote.getGender(),
					staffToPromote.getAge(),
					branchName,
					staffToPromote.getPassword());
	
			@SuppressWarnings("unchecked")
			List<Branch> branches = TextDBBranch.readBranch("TextDBBranch.txt", true);

			for (Branch branch : branches) {
				if (branch.getBranchName().equals(branchName)) {
					branch.getManagerList().add(promotedManager);
					break;
				}
			}
			TextDBStaff.saveEmployee("TextDBStaff.txt", employees);
	
			TextDBBranch.saveBranch("TextDBBranch.txt", branches);
	
			System.out.println("Staff member " + staffId + " has been promoted to branch manager.");
			promoted = true;
		} catch (IOException e) {
			System.out.println("Error promoting staff: " + e.getMessage());
			promoted = false;
		}
		return promoted;
	}
	public void transferUserToBranch(String userId, String newBranchName, String currentBranchName) {
		try {
			// Read all employees from TextDBStaff
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
	
			// Read all branches from TextDBBranch
			@SuppressWarnings("unchecked")
			List<Branch> branches = TextDBBranch.readBranch("TextDBBranch.txt", true);
	
			// Find the staff member to transfer
			Staff staffToTransfer = null;
			Branch currentBranch = null;
			for (User user : employees) {
				if (user instanceof Staff && user.getLoginID().equals(userId)) {
					staffToTransfer = (Staff) user;
					for (Branch branch : branches) {
						if (branch.getBranchName().equals(staffToTransfer.getBranch())) {
							currentBranch = branch;
							break;
						}
					}
					break;
				}
			}
			if (staffToTransfer == null) {
				System.out.println("Staff with ID '" + userId + "' not found.");
				return;
			}
			Branch newBranch = null;
			for (Branch branch : branches) {
				if (branch.getBranchName().equals(newBranchName)) {
					newBranch = branch;
					break;
				}
			}
			if (newBranch == null) {
				System.out.println("Branch '" + newBranchName + "' not found.");
				return;
			}
			currentBranch.getStaffList().remove(staffToTransfer);
			Staff transferredStaff = new Staff(
					staffToTransfer.getEmployeeName(),
					staffToTransfer.getLoginID(),
					staffToTransfer.getEmployeeType(),
					staffToTransfer.getGender(),
					staffToTransfer.getAge(),
					newBranchName,
					staffToTransfer.getPassword());

			newBranch.getStaffList().add(transferredStaff);
	
			employees.remove(staffToTransfer);
			employees.add(transferredStaff);
			TextDBStaff.saveEmployee("TextDBStaff.txt", employees);
			TextDBBranch.saveBranch("TextDBBranch.txt", branches);
	
			System.out.println("Staff with ID '" + userId + "' transferred to branch '" + newBranchName + "'.");
		} catch (IOException e) {
			System.out.println("Error transferring staff: " + e.getMessage());
		}
	}
	
	public void addPaymentMethod(String paymentMethodName) {
        PaymentController paymentController = new PaymentController();
        double dummyAmount = 0.0; // Dummy amount

        try {
            boolean paymentMethodSupported = paymentController.processPayment(dummyAmount, paymentMethodName);
            if (paymentMethodSupported) {
                Payment paymentMethod = null;
                switch (paymentMethodName.toLowerCase()) {
                    case "credit/debit":
                        paymentMethod = new CreditandDebit();
                        break;
                    case "paynow":
                        paymentMethod = new Paynow();
                        break;
                    case "newpaymentmethod":
                        //paymentMethod = new NewPaymentImpl(); // Using the new payment method implementation
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported payment method: " + paymentMethodName);
                }
                paymentMethods.add(paymentMethod);
                System.out.println("Payment method '" + paymentMethodName + "' added successfully.");
            } else {
                System.out.println("Payment method '" + paymentMethodName + "' is not supported.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Payment method '" + paymentMethodName + "' is not supported.");
        }
    }
	 

	public void openBranch(String branchName) {
		try {
			// Read existing staff data
			@SuppressWarnings("unchecked")
			List<User> staffList = TextDBStaff.readEmployee("TextDBStaff.txt");
	
			// Read existing branch data
			@SuppressWarnings("unchecked")
			List<Branch> existingBranches = TextDBBranch.readBranch("TextDBBranch.txt", true);
	
			// Create a new branch
			Branch newBranch = new Branch(branchName, new ArrayList<>(), new ArrayList<>(), null);
	
			// Add staff to the new branch
			ArrayList<Staff> branchStaffList = new ArrayList<>();
			for (User user : staffList) {
				if (user instanceof Staff) {
					branchStaffList.add((Staff) user);
				}
			}
			newBranch.setStaffList(branchStaffList);
	
			// Add the new branch to the existing branches list
			existingBranches.add(newBranch);
	
			// Save updated branch data
			TextDBBranch.saveBranch("TextDBBranch.txt", existingBranches);
	
			System.out.println("Branch '" + branchName + "' has been opened.");
		} catch (IOException e) {
			System.out.println("Error opening branch: " + e.getMessage());
		}
	}
	public void closeBranch(String branchName) {
		try {
			// Read existing branch data
			@SuppressWarnings("unchecked")
			List<Branch> existingBranches = TextDBBranch.readBranch("TextDBBranch.txt", true);
	
			// Find the branch to close
			Branch branchToClose = null;
			for (Branch branch : existingBranches) {
				if (branch.getBranchName().equals(branchName)) {
					branchToClose = branch;
					break;
				}
			}
	
			// If the branch is not found, print an error message and return
			if (branchToClose == null) {
				System.out.println("Branch '" + branchName + "' not found.");
				return;
			}
	
			// Remove the branch from the existing branches list
			existingBranches.remove(branchToClose);
	
			// Save updated branch data
			TextDBBranch.saveBranch("TextDBBranch.txt", existingBranches);
	
			System.out.println("Branch '" + branchName + "' has been closed.");
		} catch (IOException e) {
			System.out.println("Error closing branch: " + e.getMessage());
		}
	}
	public void displayStaffList(int filterCriterion, String filterValue, boolean sortByAge) {
		System.out.println("===== Displaying Staff List =====");
		System.out.println("Filter Criterion: " + filterCriterion);
		System.out.println("Filter Value: " + filterValue);
		System.out.println("Sort By Age: " + sortByAge);
	
		// Filter staff list
		List<User> filteredList = new ArrayList<>();
		try {
			// Read staff data from TextDBStaff
			@SuppressWarnings("unchecked")
			List<User> staffList = TextDBStaff.readEmployee("TextDBStaff.txt");
	
			switch (filterCriterion) {
				case 1: // Gender
					Gender gender = Gender.valueOf(filterValue.toUpperCase());
					filteredList = staffList.stream()
							.filter(user -> Gender.valueOf(user.getGender().toUpperCase()) == gender)
							.collect(Collectors.toList());
					break;
				case 2: // Branch
					// Read branch data from TextDBBranch
					@SuppressWarnings("unchecked")
					List<Branch> branches = TextDBBranch.readBranch("TextDBBranch.txt", true);
	
					String finalFilterValue = filterValue;
					filteredList = staffList.stream()
							.filter(user -> {
								if (user instanceof Staff) {
									String branchName = ((Staff) user).getBranch();
									return getBranchByName(branches, branchName).getBranchName().equals(finalFilterValue);
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
		} catch (IOException e) {
			System.out.println("Error displaying staff list: " + e.getMessage());
		}
	}
	private Branch getBranchByName(List<Branch> branches, String branchName) {
		for (Branch branch : branches) {
			if (branch.getBranchName().equals(branchName)) {
				return branch;
			}
		}
		return null;
	}
}
