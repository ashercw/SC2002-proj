public class AdminController {

	/**
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @param age
	 * @param gender
	 * @param branch
	 * @param role
	 */
	public Staff addStaff(String username, String password, String name, int age, Gender gender, Branch branch, Role role) {
		// TODO - implement AdminController.addStaff
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 * @param newName
	 * @param newAge
	 * @param newGender
	 * @param newBranch
	 * @param newRole
	 */
	public void editStaff(String userId, String newName, int newAge, Gender newGender, Branch newBranch, Role newRole) {
		// TODO - implement AdminController.editStaff
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 */
	public void removeStaff(String userId) {
		// TODO - implement AdminController.removeStaff
		throw new UnsupportedOperationException();
	}

	public void assignManagerToBranch() {
		// TODO - implement AdminController.assignManagerToBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 */
	public void promoteToBranchManager(String userId) {
		// TODO - implement AdminController.promoteToBranchManager
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 * @param newBranch
	 */
	public void transferUserToBranch(String userId, Branch newBranch) {
		// TODO - implement AdminController.transferUserToBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 * @param paymentMethod
	 */
	public void addPaymentMethod(String userId, PaymentMethod paymentMethod) {
		// TODO - implement AdminController.addPaymentMethod
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 * @param paymentMethod
	 */
	public void removePaymentMethod(String userId, PaymentMethod paymentMethod) {
		// TODO - implement AdminController.removePaymentMethod
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branch
	 */
	public void openBranch(Branch branch) {
		// TODO - implement AdminController.openBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param branch
	 */
	public void closeBranch(Branch branch) {
		// TODO - implement AdminController.closeBranch
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param filter
	 */
	public List<User> displayStaffList(Filter filter) {
		// TODO - implement AdminController.displayStaffList
		throw new UnsupportedOperationException();
	}

}