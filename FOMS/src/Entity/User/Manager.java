package Entity.User;
import Entity.BranchType;

public class Manager extends Staff{

	//constructor 
	public Manager(boolean _ip, String _en, String _lID, EmployeeType _et, Gender _g, int _a, BranchType _bt, String _p)
	{
		super(_ip, _en, _lID, _et, _g, _a, _bt, _p);
	}
	
}
