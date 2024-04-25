package Controller.Request;

import java.io.IOException;
import java.util.*;
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

	public boolean addStaff(String name, String loginID, EmployeeType role, String gender, String age, String branch,
			String password) {
		boolean added = false;
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
			for (User user : employees) {
				if (user.getLoginID().equals(loginID)) {
					return false;
				}
			}
			Staff newStaff = new Staff(name, loginID, role, gender, age, branch, password);
			employees.add(newStaff);
			TextDBStaff.saveEmployee("TextDBStaff.txt", employees);
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
	public void editStaff(String loginID, String newName, String newAge, String newGender, EmployeeType newRole,
			String newBranch) {
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
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
					TextDBStaff.saveEmployee("TextDBStaff.txt", employees);
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
	public void removeStaff(String userId) {
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
			boolean found = false;
			for (int i = 0; i < employees.size(); i++) {
				User user = employees.get(i);
				if (user.getLoginID().equals(userId) && user instanceof Staff) {
					employees.remove(i);
					TextDBStaff.saveEmployee("TextDBStaff.txt", employees);
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

	/**
	 * Assigns a manager to a branch.
	 * 
	 * @param managerId  The ID of the manager to be assigned.
	 * @param branchName The name of the branch to which the manager will be
	 *                   assigned.
	 * @param branchType The type of the branch.
	 */
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
			System.out.println(
					"Manager " + manager.getEmployeeName() + " has been assigned to branch " + branchName + ".");
			TextDBStaff.saveEmployee("TextDBStaff.txt", employees);
			TextDBBranch.saveBranch("TextDBBranch.txt", branches);
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

	/**
	 * Transfers a user (staff member) to a different branch.
	 * 
	 * @param userId            The ID of the user (staff member) to be transferred.
	 * @param newBranchName     The name of the new branch to which the user will be
	 *                          transferred.
	 * @param currentBranchName The name of the current branch from which the user
	 *                          will be transferred.
	 */
	public void transferUserToBranch(String userId, String newBranchName, String currentBranchName) {
		try {
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");

			@SuppressWarnings("unchecked")
			List<Branch> branches = TextDBBranch.readBranch("TextDBBranch.txt", true);

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
			assert currentBranch != null;
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

	/**
	 * Adds a new payment method to the system.
	 * 
	 * @param paymentMethod The name of the payment method to be added.
	 * @param paymentClass  The class representing the payment method
	 *                      implementation.
	 */
	public void addPaymentMethod(String paymentMethod, Class<? extends Payment> paymentClass) {
		PaymentController.registerPaymentMethod(paymentMethod, paymentClass);
	}

	/**
	 * Opens a new branch.
	 * 
	 * @param branchName The name of the branch to be opened.
	 */
	public void openBranch(String branchName) {
		try {
			@SuppressWarnings("unchecked")
			List<User> staffList = TextDBStaff.readEmployee("TextDBStaff.txt");
			@SuppressWarnings("unchecked")
			List<Branch> existingBranches = TextDBBranch.readBranch("TextDBBranch.txt", true);

			Branch newBranch = new Branch(branchName, new ArrayList<>(), new ArrayList<>(), null);

			ArrayList<Staff> branchStaffList = new ArrayList<>();
			for (User user : staffList) {
				if (user instanceof Staff) {
					branchStaffList.add((Staff) user);
				}
			}
			newBranch.setStaffList(branchStaffList);

			existingBranches.add(newBranch);

			TextDBBranch.saveBranch("TextDBBranch.txt", existingBranches);

			System.out.println("Branch '" + branchName + "' has been opened.");
		} catch (IOException e) {
			System.out.println("Error opening branch: " + e.getMessage());
		}
	}

	/**
	 * Closes an existing branch.
	 * 
	 * @param branchName The name of the branch to be closed.
	 */
	public void closeBranch(String branchName) {
		try {
			@SuppressWarnings("unchecked")
			List<Branch> existingBranches = TextDBBranch.readBranch("TextDBBranch.txt", true);

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

			TextDBBranch.saveBranch("TextDBBranch.txt", existingBranches);

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
	public void displayStaffList(int filterCriterion, String filterValue, boolean sortByAge) {
		System.out.println("===== Displaying Staff List =====");
		System.out.println("Filter Criterion: " + filterCriterion);
		System.out.println("Filter Value: " + filterValue);
		System.out.println("Sort By Age: " + sortByAge);

		List<User> filteredList = new ArrayList<>();
		try {
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
					@SuppressWarnings("unchecked")
					List<Branch> branches = TextDBBranch.readBranch("TextDBBranch.txt", true);

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
	private Branch getBranchByName(List<Branch> branches, String branchName) {
		for (Branch branch : branches) {
			if (branch.getBranchName().equals(branchName)) {
				return branch;
			}
		}
		return null;
	}
}
