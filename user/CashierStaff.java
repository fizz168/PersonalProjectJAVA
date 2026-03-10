
package user;

import controller.Garage;

public class CashierStaff extends Staff {

    private float salary;

    // ====== Constructor — takes a Staff object + salary (teacher's pattern) ======
    public CashierStaff(String staffId, String fullName, String phone,
                        String username, String password, float salary) {
super(staffId, fullName, phone, username, password, salary);
       
    }

    // ====== Salary Getter/Setter ======
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        if (salary < 400) {
            System.out.println("error: need more salary");
        } else {
            this.salary = salary;
        }
    }

    // ====== Permission Logic ======
    @Override
    public boolean can(String action) {
        if (action.equals(Garage.CREATE_CUSTOMER)
         || action.equals(Garage.CREATE_ORDER)
         || action.equals(Garage.VIEW_CUSTOMERS)
         || action.equals(Garage.VIEW_ORDERS)) {
            return true;
        }
        return false;
    }

    // ====== equals ======
    @Override
    public boolean equals(Object obj) {
        CashierStaff other = (CashierStaff) obj;
        if (!super.equals(obj)) {
            return false;
        } else {
            if (Float.floatToIntBits(salary) != Float.floatToIntBits(other.salary))
                return false;
        }
        return true;
    }

    // ====== toString ======
    @Override
    public String toString() {
        return super.toString() +
                ", salary=" + salary +
                '}';
    }
}