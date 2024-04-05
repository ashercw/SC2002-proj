package Entity.User;
import Entity.BranchType;

public interface EmployeeUser {

    public String getEmployeeName();
    public String getLoginID();
    public EmployeeType getEmployeeType();
    public Gender getGender();
    public int getAge();
    public BranchType getBranch(); 

}
