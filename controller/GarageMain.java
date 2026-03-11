package controller;
// import java.util.Scanner;


import java.util.ArrayList;

import user.CashierStaff;
import user.ManagerStaff;
import user.SellerStaff;
import user.Staff;

public class GarageMain {
    public static void main(String[] args) {
ArrayList<Staff> staffs = new ArrayList<>();
staffs.add(new CashierStaff("S001", "Alice", "010000001", "alice", "1234", 1000));
staffs.add(new SellerStaff("S002", "Bob", "010000002", "bob", "1234",400));
staffs.add(new ManagerStaff("S003", "Charlie", "010000003", "charlie", "1234", 2000));

for (Staff s : staffs) {
    System.out.println(s.getUsername() 
        + " | CREATE_ORDER: "   + s.can(Garage.CREATE_ORDER)
        + " | CREATE_MENU_ITEM: " + s.can(Garage.CREATE_MENU_ITEM)
        + " | VIEW_ORDERS: "    + s.can(Garage.VIEW_ORDERS));
}



}
}
