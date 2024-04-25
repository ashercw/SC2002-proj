package Entity;
import java.util.ArrayList;

import Entity.Food.Menu;
import Entity.User.Manager;
import Entity.User.Staff;

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
    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String _bn) {
        this.branchName = _bn;
    }

    public String getLocation() {
        return this.location;
    }

    public void setStaffList(ArrayList<Staff> _sl) {
        this.staffList = _sl != null ? _sl : new ArrayList<>(); // Ensure staff list is not null
        this.staffNum = this.staffList.size();
    }

    public ArrayList<Staff> getStaffList() {
        return this.staffList;
    }
    
    public ArrayList<Manager> getManagerList() {
        return this.managerList;
    }

    public void setManagerList(ArrayList<Manager> _ml) {
        this.managerList = _ml != null ? _ml : new ArrayList<>(); // Ensure manager list is not null
        this.managerNum = this.managerList.size();
    }

    public int getQuota() {
        return this.staffQuota;
    }
    
    public int getStaffCount() {
        return this.staffNum;
    }
    
    public int getManagerCount() {
        return this.managerNum;
    }
    
    public Menu getMenu() {
        return this.menu;
    }
}

