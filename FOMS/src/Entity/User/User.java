package Entity.User;

/**
 * @author Saffron, Reuben Farrel
 */
public class User implements EmployeeUser {

    private String empName;
    private String loginID;
    protected EmployeeType empType;
    protected String gender;
    protected String age;
    private String password;

    public User() {
    }

    public User(String _en, String _lID, EmployeeType _et, String _g, String _a, String _p) {
        this.empName = _en;
        this.loginID = _lID;
        this.empType = _et;
        this.gender = _g;
        this.age = _a;
        this.password = _p;
    }

    
    /** 
     * @return String
     */
    public String getEmployeeName() {
        return this.empName;
    }

    public void setEmployeeName(String name) {
        this.empName = name;
    }

    public String getLoginID() {
        return this.loginID;
    }

    public EmployeeType getEmployeeType() {
        return this.empType;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAge() {
        return this.age;
    }

    public void setPassword(String _p) {
        this.password = _p;
    }

    public String getPassword() {
        return this.password;
    }

}
