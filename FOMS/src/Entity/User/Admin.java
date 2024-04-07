package Entity.User;

import Entity.BranchType;

public class Admin implements EmployeeUser{
    private String empName;
    private String loginID;
    private EmployeeType empType;
    private Gender gender;
    private int age;
    private BranchType branch;

    //Constructor
    public Admin(boolean _ip, String _en, String _lID, EmployeeType _et, Gender _g, int _a, BranchType _bt)
    {
        this.empName = _en;
        this.loginID = _lID;
        this.empType = _et;
        this.gender = _g;
        this.age = _a;
        this.branch = _bt;
    }
    

    //Accessors and mutators
    public String getEmployeeName()
    {
        return this.empName;
    }
    public String getLoginID()
    {
        return this.loginID;
    }
    public EmployeeType getEmployeeType()
    {
        return this.empType;
    }
    public Gender getGender()
    {
        return this.gender;
    }
    public int getAge()
    {
        return this.age;
    }
    public BranchType getBranch()
    {
        return this.branch;
    } 


}