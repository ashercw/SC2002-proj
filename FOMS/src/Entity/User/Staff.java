package Entity.User;
import Entity.BranchType;

public class Staff extends User{
    private boolean isPromoted;
    private String branch;

    //Constructor
    public Staff(String _en, String _lID, EmployeeType _et, String _g, String _a, String _bt, String _p)
    {
        super( _en, _lID, _et, _g, _a, _p);
        this.isPromoted = false;
        this.branch = _bt;
        System.out.println("Creating staff" + _en);
    }


    //Accessors and mutators
    public boolean getPromotion()
    {
        return this.isPromoted;
    }
    
    public String getBranch()
    {
        return this.branch;
    } 
}
