
package user;

public class ManagerStaff extends Staff  {

    private float benefit;

    // ====== Constructor  ======
    public ManagerStaff(String staffId, String fullName, String phone,
                        String username, String password, float salary, float benefit) {


        super(staffId, fullName, phone, username, password, salary); // call CashierStaff constructor to set Staff fields and salary
        this.benefit = benefit;
    }

    // ====== Manager can do everything ======
    @Override
    public boolean can(String action) {
        return true;
    }

    // ====== Salary Getter/Setter ======
    public float getBenefit() {
        return benefit;
    }

    public void setBenefit(float benefit) {
         if (benefit < 400) {
            System.out.println("error: need more salary");
        } else {
            this.benefit = benefit;
        }
       
    }
    public float getTotalPay() {
    return getSalary() + benefit;
}

    // ====== toString ======
    @Override
    public String toString() {
        return super.toString() + "ManagerStaff [\"Position: Manager benefit=" + benefit + "]";
    }

    // ====== equals ======
    @Override
    public boolean equals(Object obj) {
        ManagerStaff other = (ManagerStaff) obj;
        if (!super.equals(obj)) {
            return false;
        } else {
            if (Float.floatToIntBits(benefit) != Float.floatToIntBits(other.benefit)) {
                return false;
            }
        }
        return true;
    }
}