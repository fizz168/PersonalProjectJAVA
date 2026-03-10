
package user;

public class ManagerStaff extends CashierStaff  {

    private float benefit;

    // ====== Constructor — takes a Staff object + salary (teacher's pattern) ======
    public ManagerStaff(Staff s, float salary) {
        super(s, salary); // call CashierStaff constructor to set Staff fields and salary
        System.out.println("ManagerStaff created: " + this);
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

    public void setSalary(float benefit) {
         if (benefit < 1000) {
            System.out.println("error: need more salary");
        } else {
            this.benefit = benefit;
        }
       
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