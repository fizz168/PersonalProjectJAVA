package user;
import controller.Garage;

public class CashierStaff extends Staff {

    // ====== Constructor ======
    public CashierStaff(String staffId, String fullName, String phone,
                        String username, String password, float salary) {
        super(staffId, fullName, phone, username, password, salary); // salary goes to Staff
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
        if (!super.equals(obj)) return false;
        return true;
    }
// ====== toString ======
    @Override
    public String toString() {
        return "CashierStaff []";
    }

}