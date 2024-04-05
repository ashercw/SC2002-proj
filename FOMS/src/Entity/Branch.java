package Entity;
import java.util.ArrayList;

public class Branch {
    private BranchType branchName;
	private ArrayList<Staff> staffList;
	private ArrayList<Manager> managerList;
	private int staffNum;
	private int managerNum;
	private Menu menu;
	
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
