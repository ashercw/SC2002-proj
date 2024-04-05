package Entity;
import java.util.ArrayList;

import Entity.Food.Menu;
import Entity.User.Manager;
import Entity.User.Staff;

public class Branch {
    private BranchType branchName;
	private ArrayList<Staff> staffList;
	private ArrayList<Manager> managerList;
	private int staffNum;
	private int managerNum;
	private Menu menu;
	
	//Constructor
	public Branch(BranchType _bt, ArrayList<Staff> _sl, ArrayList<Manager> _ml, int _sn, int _mn, Menu _menu)
	{
		this.branchName = _bt;
		this.staffList = _sl;
		this.managerList = _ml;
		this.staffNum = _sn;
		this.managerNum = _mn;
		this.menu = _menu;
	}

	//Accessors and Mutators
	public BranchType getBranchType()
	{
		return this.branchName;
	}
	
	public ArrayList<Staff> getStaffList()
	{
		return this.staffList;
	}
	
	public ArrayList<Manager> getManagerList()
	{
		return this.managerList;
	}
	
	public int getStaffCount()
	{
		return this.staffNum;
	}
	
	public int getManagerCount()
	{
		return this.managerNum;
	}
	
	public Menu getMenu()
	{
		return this.menu;
	}

}
