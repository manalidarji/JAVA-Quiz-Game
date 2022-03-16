package businessLogic;

public class User {
    private String fName;
    private String lName;
    private double cashPrize;

    public User() {
    }
    
    public User(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
        this.cashPrize = 0;
    }

    public User(String fName, String lName, double cashPrize) {
        this.fName = fName;
        this.lName = lName;
        this.cashPrize = cashPrize;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
    public String getFullName() {
        return fName + " " + lName;
    }

    public double getCashPrize() {
        return cashPrize;
    }

    public void setCashPrize(double cashPrize) {
        this.cashPrize = cashPrize;
    }

    @Override
    public String toString() {
        return "User{" + "fName=" + fName + ", lName=" + lName + ", cashPrize=" + cashPrize + '}';
    }
}
