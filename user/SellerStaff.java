
package user;

import controller.Garage;

public class SellerStaff extends Staff {

    // ====== Constructor — simple inheritance (like teacher's BaristaStaff) ======
    public SellerStaff(String staffId, String fullName, String phone,
                       String username, String password, float salary) {
        super(staffId, fullName, phone, username, password ,salary); 
    }

    // ====== Permission Logic ======
    @Override
    public boolean can(String action) {
        if (action.equals(Garage.CREATE_MENU_ITEM)
         || action.equals(Garage.VIEW_ORDERS)
         || action.equals(Garage.UPDATE_ORDER_STATUS) || action.equals(Garage.CREATE_ORDER)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "SellerStaff []";
    }
    
}