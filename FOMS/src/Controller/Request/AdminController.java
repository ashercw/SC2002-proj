package Controller.Request;

import java.io.IOException;
import java.util.*;

import Entity.User.Admin;
import Entity.User.EmployeeType;
import Entity.User.Gender;
import Entity.User.Staff;
import Entity.User.User;
import Entity.Branch;
import Entity.User.Manager;
import Entity.Order.Payment;
import java.util.stream.Collectors;
import Others.TextDBBranch;
import Others.TextDBStaff;
import Others.TextDBPayment;

/**
 * The AdminController class gives the power for Admin to do administrative
 * jobs, such as adding and removing staff, editing staff information, assigning
 * managers to branches, promoting staff to managers, transferring staff to
 * other branches, adding payment methods, opening and closing branches, and
 * displaying staff lists with filter and sort. It interacts with the data
 * repository, Staff class and User class.
 * 
 * @author Reuben Farrel
 */

public class AdminController {
	/**
	 * Adds a new staff member to the system.
	 * 
	 * @param password The login password of the staff member.
	 * @param name     The name of the staff member.
	 * @param loginID  The login ID of the staff member.
	 * @param age      The age of the staff member.
	 * @param gender   The gender of the staff member.
	 * @param branch   The branch to which the staff member belongs.
	 * @param role     The role of the staff member.
	 * @return true if the staff member is successfully added, false otherwise.
	 */

	public AdminController() {
	}

	public static boolean addStaff(String name, String loginID, EmployeeType role, String gender, String age,
			String branch,
			String password) {
		boolean added = false;
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
			for (User user : employees) {
				if (user.getLoginID().equals(loginID)) {
					return false;
				}
			}
			if (role == EmployeeType.S) {
				Staff newStaff = new Staff(name, loginID, role, gender, age, branch, password);
				employees.add(newStaff);
			} else if (role == EmployeeType.M) {
				Manager newMan = new Manager(name, loginID, role, gender, age, branch, password);
				employees.add(newMan);
			} else if (role == EmployeeType.A) {
				Admin newAd = new Admin(name, loginID, role, gender, age, password);
				employees.add(newAd);
			}

			TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
			added = true;
		} catch (IOException e) {
			System.out.println("Error adding staff: " + e.getMessage());
			added = false;
		}
		return added;
	}

	/**
	 * Edits the details of an existing staff member.
	 *
	 * @param loginID   The login ID of the staff member to be edited.
	 * @param newName   The new name to assign to the staff member.
	 * @param newAge    The new age to assign to the staff member.
	 * @param newGender The new gender to assign to the staff member.
	 * @param newRole   The new role to assign to the staff member.
	 * @param newBranch The new branch to assign to the staff member.
	 */
	public static void editStaff(String loginID, String newName, String newAge, String newGender, EmployeeType newRole,
			String newBranch) {
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
			boolean found = false;
			for (int i = 0; i < employees.size(); i++) {
				User user = employees.get(i);
				if (user.getLoginID().equals(loginID) && user instanceof Staff) {
					Staff staff = (Staff) user;
					if (!newName.isEmpty()) {
						staff.setEmployeeName(newName);
					}
					if (!newAge.isEmpty()) {
						staff.setAge(newAge);
					}
					if (!newGender.isEmpty()) {
						staff.setGender(newGender);
					}
					if (newRole != null) {
						staff.setEmpType(newRole);
					}
					if (!newBranch.isEmpty()) {
						staff.setBranch(newBranch);
					}
					employees.set(i, staff);
					TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
					System.out.println("Staff details updated successfully.");
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Staff not found.");
			}
		} catch (Exception e) {
			System.out.println("Error editing staff: " + e.getMessage());
		}
	}

	/**
	 * Removes a staff member from the system.
	 * 
	 * @param userId The ID of the staff member to be removed.
	 */
	public static void removeStaff(String userId) {
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
			boolean found = false;
			for (int i = 0; i < employees.size(); i++) {
				User user = employees.get(i);
				if (user.getLoginID().equals(userId) && user instanceof Staff) {
					employees.remove(i);
					TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
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

	public static void assignManagerToBranch(String managerId, String branchName) {
		try {
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
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

			List<Branch> branches = TextDBBranch.readBranch("BranchRepo.txt", true);
			Branch branch = branches.stream()
					.filter(b -> b.getBranchName().equals(branchName))
					.findFirst().orElse(null);

			if (branch == null) {
				System.out.println("Branch with name " + branchName + " not found.");
				return;
			}

			ArrayList<Manager> managers = branch.getManagerList();
			if (managers == null) {
				managers = new ArrayList<>(); // Initialize the list if it is null
				branch.setManagerList(managers); // Update the branch
			}

			if (managers.stream().anyMatch(m -> m.getBranch().equals(branchName))) {
				System.out.println("The branch already has a manager assigned.");
				return;
			}

			manager.setBranch(branchName);
			managers.add(manager);

			TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
			TextDBBranch.saveBranch("BranchRepo.txt", branches);

			System.out.println("Manager with ID " + managerId + " has been assigned to branch " + branchName + ".");
		} catch (IOException e) {
			System.out.println("Error assigning manager to branch: " + e.getMessage());
		}
	}

	/**
	 * Promotes a staff member to a branch manager.
	 * 
	 * @param staffId     The ID of the staff member to be promoted.
	 * @param branchName  The name of the branch to which the promoted manager will
	 *                    belong.
	 * @param managerRole The role of the promoted manager.
	 * @return true if the staff member is successfully promoted, false otherwise.
	 */
	public static boolean promoteStaffToManager(String staffId, String branchName, EmployeeType managerRole) {
		boolean promoted = false;
		try {
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
			int staffIndex = -1;
			for (int i = 0; i < employees.size(); i++) {
				User user = employees.get(i);
				if (user.getLoginID().equals(staffId) && user instanceof Staff) {
					staffIndex = i;
					break;
				}
			}
			if (staffIndex == -1) {
				System.out.println("Staff member with ID " + staffId + " not found.");
				return false;
			}
			Staff staffToPromote = (Staff) employees.get(staffIndex);
			Manager promotedManager = new Manager(
					staffToPromote.getEmployeeName(),
					staffToPromote.getLoginID(),
					managerRole, // This should be EmployeeType.M for Manager
					staffToPromote.getGender(),
					staffToPromote.getAge(),
					staffToPromote.getBranch(),
					staffToPromote.getPassword());

			employees.set(staffIndex, promotedManager); // Replace the Staff object with a new Manager object

			TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
			System.out.println("Staff member " + staffId + " has been promoted to branch manager.");
			promoted = true;
		} catch (IOException e) {
			System.out.println("Error promoting staff: " + e.getMessage());
		}
		return promoted;
	}

	/**
	 * Transfers a user (staff member) to a different branch.
	 * 
	 * @param userId            The ID of the user (staff member) to be transferred.
	 * @param newBranchName     The name of the new branch to which the user will be
	 *                          transferred.
	 * @param currentBranchName The name of the current branch from which the user
	 *                          will be transferred.
	 */
	public static void transferUserToBranch(String userId, String newBranchName, String currentBranchName) {
		try {
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
			List<Branch> branches = TextDBBranch.readBranch("BranchRepo.txt", true);

			Staff staffToTransfer = null;
			for (User user : employees) {
				if (user instanceof Staff && user.getLoginID().equals(userId)) {
					staffToTransfer = (Staff) user;
					break;
				}
			}
			if (staffToTransfer == null) {
				System.out.println("Staff with ID '" + userId + "' not found.");
				return;
			}

			Branch currentBranch = null, newBranch = null;
			for (Branch branch : branches) {
				if (branch.getBranchName().equals(currentBranchName)) {
					currentBranch = branch;
				}
				if (branch.getBranchName().equals(newBranchName)) {
					newBranch = branch;
				}
			}
			if (newBranch == null) {
				System.out.println("New branch '" + newBranchName + "' not found.");
				return;
			}
			if (currentBranch == null) {
				System.out.println("Current branch '" + currentBranchName + "' not found.");
				return;
			}

			currentBranch.getStaffList().remove(staffToTransfer);
			newBranch.getStaffList().add(staffToTransfer);
			staffToTransfer.setBranch(newBranchName);

			TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
			TextDBBranch.saveBranch("BranchRepo.txt", branches);

			System.out.println("Staff with ID '" + userId + "' transferred to branch '" + newBranchName + "'.");
		} catch (IOException e) {
			System.out.println("Error transferring staff: " + e.getMessage());
		}
	}

	/**
	 * Adds a new payment method to the system.
	 * 
	 * @param paymentMethod The name of the payment method to be added.
	 * @param paymentClass  The class representing the payment method
	 *                      implementation.
	 */
	public static void addPaymentMethod(String paymentMethodName, String paymentClass) throws IOException {
		List<String[]> existingPayments = TextDBPayment.readPaymentMethods("PaymentRepo.txt", true);
		
		// Check if the payment method already exists
		for (String[] payment : existingPayments) {
			if (payment[0].equalsIgnoreCase(paymentMethodName)) {
				System.out.println("Payment method '" + paymentMethodName + "' already exists.");
				return; // Early return to prevent further processing
			}
		}
		existingPayments.add(new String[] { paymentMethodName, paymentClass });

		// Save the updated list of payment methods back to the text file
		TextDBPayment.savePaymentMethods("PaymentRepo.txt", existingPayments);

		System.out.println("New payment method '" + paymentMethodName + "' added successfully.");
	}

	/**
	 * Opens a new branch.
	 * 
	 * @param branchName The name of the branch to be opened.
	 */
	public static void openBranch(String branchName, String location, int quota) throws IOException {
		@SuppressWarnings("unchecked")
		List<Branch> existingBranches = TextDBBranch.readBranch("BranchRepo.txt", true);

		// Check if the branch already exists
		for (Branch user : existingBranches) {
			if (user instanceof Branch) {
				Branch existingBranch = (Branch) user;
				if (existingBranch.getBranchName().equalsIgnoreCase(branchName)) {
					System.out.println("Branch '" + branchName + "' already exists.");
					return;
				}
			}
		}

		Branch newBranch = new Branch(branchName, new ArrayList<>(), new ArrayList<>(), null);
		newBranch.setLocation(location);
		newBranch.setQuota(quota);

		existingBranches.add(newBranch);

		TextDBBranch.saveBranch("BranchRepo.txt", existingBranches);
		System.out.println("Branch '" + branchName + "' has been opened.");

	}

	/**
	 * Closes an existing branch.
	 * 
	 * @param branchName The name of the branch to be closed.
	 */
	public static void closeBranch(String branchName) {
		try {
			@SuppressWarnings("unchecked")
			List<Branch> existingBranches = TextDBBranch.readBranch("BranchRepo.txt", true);

			Branch branchToClose = null;
			for (Branch branch : existingBranches) {
				if (branch.getBranchName().equals(branchName)) {
					branchToClose = branch;
					break;
				}
			}

			if (branchToClose == null) {
				System.out.println("Branch '" + branchName + "' not found.");
				return;
			}

			existingBranches.remove(branchToClose);

			TextDBBranch.saveBranch("BranchRepo.txt", existingBranches);

			System.out.println("Branch '" + branchName + "' has been closed.");
		} catch (IOException e) {
			System.out.println("Error closing branch: " + e.getMessage());
		}
	}

	/**
	 * Displays a list of staff members based on specified filtering criteria.
	 * 
	 * @param filterCriterion The criterion for filtering (1 for gender, 2 for
	 *                        branch, 3 for role).
	 * @param filterValue     The value to filter by.
	 * @param sortByAge       true if the list should be sorted by age, false
	 *                        otherwise.
	 */
	public static void displayStaffList(int filterCriterion, String filterValue, boolean sortByAge) {
		System.out.println("===== Displaying Staff List =====");
		System.out.println("Filter Criterion: " + filterCriterion);
		System.out.println("Filter Value: " + filterValue);
		System.out.println("Sort By Age: " + sortByAge);

		List<User> filteredList = new ArrayList<>();
		try {
			@SuppressWarnings("unchecked")
			List<User> staffList = TextDBStaff.readEmployee("EmployeeRepo.txt");

			switch (filterCriterion) {
				case 1: // Gender
					Gender gender = Gender.valueOf(filterValue.toUpperCase());
					filteredList = staffList.stream()
							.filter(user -> Gender.valueOf(user.getGender().toUpperCase()) == gender)
							.collect(Collectors.toList());
					break;
				case 2: // Branch
					@SuppressWarnings("unchecked")
					List<Branch> branches = TextDBBranch.readBranch("BranchRepo.txt", true);

					String finalFilterValue = filterValue;
					filteredList = staffList.stream()
							.filter(user -> {
								if (user instanceof Staff) {
									String branchName = ((Staff) user).getBranch();
									return getBranchByName(branches, branchName).getBranchName()
											.equals(finalFilterValue);
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

	/**
	 * Retrieves a branch by its name from a list of branches.
	 * 
	 * @param branches   The list of branches.
	 * @param branchName The name of the branch to retrieve.
	 * @return The branch object if found, null otherwise.
	 */
	private static Branch getBranchByName(List<Branch> branches, String branchName) {
		for (Branch branch : branches) {
			if (branch.getBranchName().equals(branchName)) {
				return branch;
			}
		}
		return null;
	}
}