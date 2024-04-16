package Entity.User;

public class Staff extends User{
    private boolean isPromoted;
    private String branch;

    //Constructor
    public Staff(String _en, String _lID, EmployeeType _et, String _g, String _a, String _bt, String _p)
    {
        super( _en, _lID, _et, _g, _a, _p);
        this.isPromoted = false;
        this.branch = _bt;
    }


    //Accessors and mutators
    public boolean getPromotion()
    {
        return this.isPromoted;
    }

    public void setPromotion(boolean promotion)
    {
        this.isPromoted = promotion;
    }
    
    public String getBranch()
    {
        return this.branch;
    } 
}
