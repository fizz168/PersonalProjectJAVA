
package user;

import controller.Garage;

public class SellerStaff extends Staff {

    // ====== Constructor — simple inheritance (like teacher's BaristaStaff) ======
    public SellerStaff(String staffId, String fullName, String phone,
                       String username, String password, String position) {
        super(staffId, fullName, phone, username, password);
    }

    // ====== Permission Logic ======
    @Override
    public boolean can(String action) {
        if (action.equals(Garage.CREATE_MENU_ITEM)
         || action.equals(Garage.VIEW_ORDERS)
         || action.equals(Garage.UPDATE_ORDER_STATUS)) {
            return true;
        }
        return false;
    }
}