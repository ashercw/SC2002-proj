package Controller.Request;

import java.io.File;
import java.io.IOException;
import java.util.*;

import Entity.User.Admin;
import Entity.User.EmployeeType;
import Entity.User.Gender;
import Entity.User.Staff;
import Entity.User.User;
import Entity.Branch;
import Entity.User.Manager;
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

	
	/** 
	 * This function adds a staff to a specific branch.
	 * @param name
	 * @param loginID
	 * @param role
	 * @param gender
	 * @param age
	 * @param branch
	 * @param password
	 * @return boolean
	 */
	public static boolean addStaff(String name, String loginID, EmployeeType role, String gender, String age, String branch, String password) {
		boolean added = false;
		try {
		 @SuppressWarnings("unchecked")
		 List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
		 for (User user : employees) {
		  if (user.getLoginID().equals(loginID)) {
		   return false;
		  }
		 }
	   
		 User newUser;
		 if (role == EmployeeType.S) {
		  newUser = new Staff(name, loginID, role, gender, age, branch, password);
		 } else if (role == EmployeeType.M) {
		  newUser = new Manager(name, loginID, role, gender, age, branch, password);
		 } else {
		  newUser = new Admin(name, loginID, role, gender, age, password);
		 }
	   
		 employees.add(newUser);
		 TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
	   
		 @SuppressWarnings("unchecked")
		 List<User> branchStaff = TextDBStaff.readEmployee(branch.concat("StaffListRepo.txt"));
		 branchStaff.add(newUser);
		 TextDBStaff.saveEmployee(branch.concat("StaffListRepo.txt"), branchStaff);
	   
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
	public static void editStaff(String loginID, String newName, String newAge, String newGender, EmployeeType newRole, String newBranch) {
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
		
			@SuppressWarnings("unchecked")
			List<User> oldBranchStaff = TextDBStaff.readEmployee(staff.getBranch().concat("StaffListRepo.txt"));
			oldBranchStaff.removeIf(u -> u.getLoginID().equals(loginID));
			TextDBStaff.saveEmployee(staff.getBranch().concat("StaffListRepo.txt"), oldBranchStaff);
		
			@SuppressWarnings("unchecked")
			List<User> newBranchStaff = TextDBStaff.readEmployee(newBranch.concat("StaffListRepo.txt"));
			newBranchStaff.add(staff);
			TextDBStaff.saveEmployee(newBranch.concat("StaffListRepo.txt"), newBranchStaff);
		
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
		   Staff staff = (Staff) user;
		   String branchName = staff.getBranch();
	   
		   // Remove the staff member from the main staff list
		   employees.remove(i);
		   TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
		   System.out.println("Staff member with ID '" + userId + "' has been removed.");
		   found = true;
	   
		   String staffListRepoPath = branchName.concat("StaffListRepo.txt");
		   @SuppressWarnings("unchecked")
		   List<User> branchStaffList = TextDBStaff.readEmployee(staffListRepoPath);
		   branchStaffList.removeIf(u -> u.getLoginID().equals(userId));
		   TextDBStaff.saveEmployee(staffListRepoPath, branchStaffList);
		   System.out.println("Staff list repository for branch '" + branchName + "' has been updated.");
	   
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
	 * @param managerId
	 * @param branchName
	 */
	@SuppressWarnings("unchecked")
	public static void assignManagerToBranch(String managerId, String branchName) {
		try {
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
			Manager manager = null;
			String oldBranchName = null;
	
			for (User user : employees) {
				if (user instanceof Manager && user.getLoginID().equals(managerId)) {
					manager = (Manager) user;
					oldBranchName = manager.getBranch();
					break;
				}
			}
	
			if (manager == null) {
				System.out.println("Manager with ID " + managerId + " not found.");
				return;
			}
	
			// Fetch branch information to check the quota
			Optional <Branch> branchOptional = TextDBBranch.readBranch("BranchRepo.txt", true).stream()
			.filter(b -> ((Branch) b).getBranchName().equals(branchName))
			.findFirst();

			Branch branch = branchOptional.orElse(null);
	
			if (branch == null) {
				System.out.println("Branch with name " + branchName + " not found.");
				return;
			}
			int managerNum = 0;
			int staffNum = 0;
			@SuppressWarnings("rawtypes")
			List newBranchStaffLIST = TextDBStaff.readEmployee(branchName + "StaffListRepo.txt");
			//count manager
			for(int i = 0;  i< newBranchStaffLIST.size(); i++)
			{
				User manObj = (User) newBranchStaffLIST.get(i);
				if(manObj.getEmployeeType() == EmployeeType.M)
				{
					managerNum ++;
				}
				else if(manObj.getEmployeeType() == EmployeeType.S)
				{
					staffNum ++;
				}

			}			
			if (managerNum >= 2 && staffNum < 5) {
				System.out.println("Cannot assign manager to branch " + branchName + " as the manager quota for less than 5 staff is already reached.");
				return;
			} else if (managerNum >= 3 && staffNum < 9) {
				System.out.println("Cannot assign manager to branch " + branchName + " as the manager quota for less than 9 staff is already reached.");
				return;
			} else if (managerNum >= 4 && staffNum < 15) {
				System.out.println("Cannot assign manager to branch " + branchName + " as the manager quota for less than 15 staff is already reached.");
				return;
			}
			/*long currentManagerCount = newBranchStaff.stream().filter(m -> m instanceof Manager).count();
			if (currentManagerCount >= quota) {
				System.out.println("Cannot assign manager to branch " + branchName + " as the quota (" + quota + ") is already reached.");
				return;
			}*/
	
			if (oldBranchName != null) {
				List<User> oldBranchStaff = TextDBStaff.readEmployee(oldBranchName + "StaffListRepo.txt");
				oldBranchStaff.removeIf(u -> u.getLoginID().equals(managerId));
				TextDBStaff.saveEmployee(oldBranchName + "StaffListRepo.txt", oldBranchStaff);
			}
	
			newBranchStaffLIST.add(manager);
			TextDBStaff.saveEmployee(branchName + "StaffListRepo.txt", newBranchStaffLIST);
			manager.setBranch(branchName);
			TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
	
			System.out.println("Manager with ID " + managerId + " has been reassigned to branch " + branchName + ".");
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
			@SuppressWarnings("unchecked")
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
	
			@SuppressWarnings("unchecked")
			List<User> branchStaff = TextDBStaff.readEmployee(branchName + "StaffListRepo.txt");
	
			// Count existing managers in the branch
			long currentManagerCount = branchStaff.stream().filter(u -> u instanceof Manager).count();
			int staffNum = branchStaff.size();
			
			// Adjusted quota limits: defining maximum managers based on the staff count
			if (currentManagerCount >= 2 && staffNum < 5) {
				System.out.println("Cannot promote to manager as the manager quota for 5 or fewer staff is already reached.");
				return false;
			} else if (currentManagerCount >= 3 && staffNum < 9) {
				System.out.println("Cannot promote to manager as the manager quota for 9 or fewer staff is already reached.");
				return false;
			} else if (currentManagerCount >= 4 && staffNum < 15) {
				System.out.println("Cannot promote to manager as the manager quota for 15 or fewer staff is already reached.");
				return false;
			}
	
			Staff staffToPromote = (Staff) employees.get(staffIndex);
			Manager promotedManager = new Manager(
				staffToPromote.getEmployeeName(),
				staffToPromote.getLoginID(),
				managerRole,
				staffToPromote.getGender(),
				staffToPromote.getAge(),
				staffToPromote.getBranch(),
				staffToPromote.getPassword());
	
			employees.set(staffIndex, promotedManager);
			TextDBStaff.saveEmployee("EmployeeRepo.txt", employees);
	
			branchStaff.removeIf(user -> user.getLoginID().equals(staffId));
			branchStaff.add(promotedManager);
			TextDBStaff.saveEmployee(branchName + "StaffListRepo.txt", branchStaff);
	
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
			@SuppressWarnings("unchecked")
			List<User> employees = TextDBStaff.readEmployee("EmployeeRepo.txt");
			@SuppressWarnings("unchecked")
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
	
			@SuppressWarnings("unchecked")
			List<User> newBranchStaff = TextDBStaff.readEmployee(newBranchName + "StaffListRepo.txt");
			long currentManagerCount = newBranchStaff.stream().filter(u -> u instanceof Manager).count();
			int staffCount = newBranchStaff.size();
	
			// Check quota limit based on staff count
			if (staffToTransfer instanceof Manager) {
				if (staffCount < 5 && currentManagerCount >= 1) {
					System.out.println("Cannot transfer manager as the manager quota for less than 5 staff is already reached.");
					return;
				} else if (staffCount < 9 && currentManagerCount >= 2) {
					System.out.println("Cannot transfer manager as the manager quota for less than 9 staff is already reached.");
					return;
				} else if (staffCount < 15 && currentManagerCount >= 3) {
					System.out.println("Cannot transfer manager as the manager quota for less than 15 staff is already reached.");
					return;
				}
			}
	
			// Read staff list for current branch
			List<User> currentBranchStaff = TextDBStaff.readEmployee(currentBranchName + "StaffListRepo.txt");
	
			// Remove staff from current branch staff list
			currentBranchStaff.removeIf(user -> user.getLoginID().equals(userId));
	
			// Add staff to new branch staff list
			newBranchStaff.add(staffToTransfer);
	
			// Save updated staff lists
			TextDBStaff.saveEmployee(currentBranchName + "StaffListRepo.txt", currentBranchStaff);
			TextDBStaff.saveEmployee(newBranchName + "StaffListRepo.txt", newBranchStaff);
	
			// Update staff's branch
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
        for (Branch branch : existingBranches) {
            if (branch.getBranchName().equalsIgnoreCase(branchName)) {
                System.out.println("Branch '" + branchName + "' already exists.");
                return;
            }
        }

        Branch newBranch = new Branch(branchName, new ArrayList<>(), new ArrayList<>(), null);
        newBranch.setLocation(location);
        newBranch.setQuota(quota);
        existingBranches.add(newBranch);
        TextDBBranch.saveBranch("BranchRepo.txt", existingBranches);

        // Create new staff and menu list repo files
        String staffListRepoFile = branchName.concat("StaffListRepo.txt");
        String menuListRepoFile = branchName.concat("MenuListRepo.txt");
        new File(staffListRepoFile).createNewFile();
        new File(menuListRepoFile).createNewFile();

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

            // Delete staff and menu list repo files
            String staffListRepoFile = branchName.concat("StaffListRepo.txt");
            String menuListRepoFile = branchName.concat("MenuListRepo.txt");
            File staffFile = new File(staffListRepoFile);
            File menuFile = new File(menuListRepoFile);
            if (staffFile.exists()) staffFile.delete();
            if (menuFile.exists()) menuFile.delete();

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
		   System.out.println(user.getEmployeeName() + " (" + user.getAge() + ")"); // Print age along with name
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
