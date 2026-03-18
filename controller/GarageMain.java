
package controller;


import java.sql.ResultSet;
import java.sql.SQLException;

// import user.CashierStaff;
import user.ManagerStaff;


public class GarageMain {

    public static void main(String[] args) {
    // connection = getConnection();
    // ResultSet resultSet = executeQuery("SELECT first_name FROM employees;");
    // try {
    //     while (resultSet.next()){
    //         System.out.println(resultSet.getString("first_name")));
    //     }
    // } catch(SQLException e) {
    //     e.printStackTrace();
    // }
  

        Garage garage = new Garage("CADT Garage", "Phnom Penh");

        // =====================================================
        // SETUP — login and create some staff + customers + vehicles
        // =====================================================
        garage.staffLogin("admin", "1234");
        garage.createStaff("S002", "Alice",   "012000001", "alice",   "1234", 1000, "Cashier");
        garage.createStaff("S003", "Charlie", "012000002", "charlie", "1234", 800,  "Seller");
        garage.createCustomer("C001", "David",  "011111111", "Male",   "pass1", 500);
        garage.createCustomer("C002", "Sopha",  "011111112", "Female", "pass2", 200);
        garage.addVehicle("ABC-001", "Toyota", "Camry");
        garage.addVehicle("ABC-002", "Honda",  "Civic");
        garage.returnVehicle("ABC-001"); // make available
        garage.returnVehicle("ABC-002"); // make available
        garage.staffLogout();

     
        // TEST 3: Anonymous Inner Class — filter active staff
       
        System.out.println("\n================================================");
        System.out.println("TEST 3: Anonymous Inner Class");
        System.out.println("================================================");
        garage.printActiveStaff();

        
        // TEST 4: Lambda Expression — multiple filters
      
        System.out.println("\n================================================");
        System.out.println("TEST 4: Lambda Expressions");
        System.out.println("================================================");

        // Lambda 1: active staff
        garage.printStaffByFilter(
            s -> s.isActive(),
            "Active Staff"
        );

        // Lambda 2: salary > 1500
        garage.printStaffByFilter(
            s -> s.getSalary() > 1500,
            "Staff with Salary > 1500"
        );

        // Lambda 3: managers only
        garage.printStaffByFilter(
            s -> s instanceof ManagerStaff,
            "Managers Only"
        );
         
    }
}