package Entity;
import java.util.ArrayList;

import Entity.Food.Menu;
import Entity.User.Manager;
import Entity.User.Staff;

/**
 * Represents a branch of an organization, containing staff, managers, and menu information.
 */

public class Branch {
    private String branchName;
    private String location;
    private int staffQuota;
    private ArrayList<Staff> staffList;
    private ArrayList<Manager> managerList;
    private int staffNum;
    private int managerNum;
    private Menu menu;
    
    // Constructor for minimal initialization
    public Branch(String _bn, String _loc, int _sq) {
        this.branchName = _bn;
        this.location = _loc;
        this.staffQuota = _sq;
        this.staffList = new ArrayList<>(); // Initialize staff list
        this.managerList = new ArrayList<>(); // Initialize manager list
    }

    // Constructor for full initialization
    public Branch(String _bn, ArrayList<Staff> _sl, ArrayList<Manager> _ml, Menu _menu) {
        this.branchName = _bn;
        this.staffList = _sl != null ? _sl : new ArrayList<>(); // Ensure staff list is not null
        this.managerList = _ml != null ? _ml : new ArrayList<>(); // Ensure manager list is not null
        this.staffNum = this.staffList.size(); // Update staff count based on the list
        this.managerNum = this.managerList.size(); // Update manager count based on the list
        this.menu = _menu;
    }

    // Accessors and Mutators

    /**
     * Retrieves the name of the branch.
     * @return The name of the branch.
     */
    public String getBranchName() {
        return this.branchName;
    }


    /**
     * Sets the name of the branch.
     * @param branchName The name to set for the branch.
     */
    public void setBranchName(String _bn) {
        this.branchName = _bn;
    }


    /**
     * Retrieves the location of the branch.
     * @return The location of the branch.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets the location of the branch.
     * @param location The location to set for the branch.
     */
    public void setLocation(String _loc){
        this.location = _loc;
    }
    
    /**
     * Sets the staff quota for the branch.
     * @param _sq The staff quota to set for the branch.
     */
    public void setQuota(int _sq){
            this.staffQuota = _sq;
    }

    /**
     * Sets the list of staff members at the branch.
     * @param _sl The list of staff members to set.
     */
    public void setStaffList(ArrayList<Staff> _sl) {
        this.staffList = _sl != null ? _sl : new ArrayList<>(); // Ensure staff list is not null
        this.staffNum = this.staffList.size();
    }

    /**
     * Gets the list of staff members at the branch.
     * @return The list of staff members.
     */
    public ArrayList<Staff> getStaffList() {
        return this.staffList;
    }
    
    /**
     * Gets the list of manager members at the branch.
     * @return The list of manager members.
     */
    public ArrayList<Manager> getManagerList() {
        return this.managerList;
    }

    /**
     * Sets the list of manager members at the branch.
     * @param _ml The list of manager members to set.
     */
    public void setManagerList(ArrayList<Manager> _ml) {
        this.managerList = _ml != null ? _ml : new ArrayList<>(); // Ensure manager list is not null
        this.managerNum = this.managerList.size();
    }

    /**
     * Gets the quota at the branch.
     * @return The staff quota.
     */
    public int getQuota() {
        return this.staffQuota;
    }
    
    /**
     * Gets the staff number at the branch.
     * @return The staff number.
     */
    public int getStaffCount() {
        return this.staffNum;
    }
    
    /**
     * Gets the manager number at the branch.
     * @return The manager number.
     */
    public int getManagerCount() {
        return this.managerNum;
    }
    
    /**
     * Gets the menu at the branch.
     * @return The menu.
     */
    public Menu getMenu() {
        return this.menu;
    }
}

