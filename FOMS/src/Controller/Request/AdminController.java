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
 * @author Reuben Farrel
 */

public class AdminController {

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
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("TextDBStaff.txt");
			boolean found = false;
			for (int i = 0; i < employees.size(); i++) {
				User user = employees.get(i);
				if (user.getLoginID().equals(userId) && user instanceof Staff) {
					Staff staff = (Staff) user;
					staff.setEmployeeName(newName);
					staff.setAge(newAge);
					staff.setGender(newGender);
					staff.setEmpType(newRole);
					staff.setBranch(newBranch);
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
			System.out.println(
					"Manager " + manager.getEmployeeName() + " has been assigned to branch " + branchName + ".");
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

	public void addPaymentMethod(String paymentMethod, Class<? extends Payment> paymentClass) {
		PaymentController.registerPaymentMethod(paymentMethod, paymentClass);
	}

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

	private Branch getBranchByName(List<Branch> branches, String branchName) {
		for (Branch branch : branches) {
			if (branch.getBranchName().equals(branchName)) {
				return branch;
			}
		}
		return null;
	}
}
