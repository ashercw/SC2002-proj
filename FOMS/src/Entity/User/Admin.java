package Entity.User;


public class Admin extends User {

    // Constructor
    public Admin(String _en, String _lID, EmployeeType _et, String _g, String _a, String _p) {
        super(_en, _lID, _et, _g, _a, _p);
        System.out.println("Creating admin" + _en);
        
    //Accessors and Mutators
    }
}
