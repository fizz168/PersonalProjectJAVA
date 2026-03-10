package controller;
import java.util.Scanner;


import user.CashierStaff;
import user.ManagerStaff;
import user.Staff;

public class GarageMain {

    public static void main(String[] args) {

    //     Scanner sc = new Scanner(System.in);
    //     Garage garage = new Garage("CADT Garage", "Phnom Penh");

    //     int choice;

    //     do {

    //         if (garage.getLoggedInStaff() == null) { // Not logged in
    //             printMainMenu();

    //             System.out.print("Choose: ");
    //             choice = sc.nextInt();
    //             sc.nextLine();

    //             switch (choice) {
    //                 case 1: {
    //                     System.out.print("Username: ");
    //                     String username = sc.nextLine();
    //                     System.out.print("Password: ");
    //                     String password = sc.nextLine();

    //                     garage.staffLogin(username, password);
    //                     System.out.println(garage.getLastMessage());
    //                     break;
    //                 }
    //                 case 2: {
    //                     garage.printVehicles();
    //                     break;
    //                 }
    //                 case 0: {
    //                     System.out.println("Goodbye!");
    //                     break;
    //                 }
    //                 default:
    //                     System.out.println("Invalid choice.");
    //             }

    //         } else { // Staff logged in
    //             printStaffMenu(garage);

    //             System.out.print("Choose: ");
    //             choice = sc.nextInt();
    //             sc.nextLine();

    //             switch (choice) {
    //                 case 1: { // Create Customer
    //                     System.out.print("Customer ID: ");
    //                     String customerId = sc.nextLine();
    //                     System.out.print("Full Name: ");
    //                     String fullName = sc.nextLine();
    //                     System.out.print("Phone: ");
    //                     String phone = sc.nextLine();
    //                     System.out.print("Gender: ");
    //                     String gender = sc.nextLine();
    //                     System.out.print("Password: ");
    //                     String password = sc.nextLine();
    //                     System.out.print("Initial Balance: ");
    //                     double balance = sc.nextDouble();
    //                     sc.nextLine();

    //                     garage.createCustomer(customerId, fullName, phone, gender, password, balance);
    //                     System.out.println(garage.getLastMessage());
    //                     break;
    //                 }

    //                 case 2: { // Add Vehicle
    //                     System.out.print("Number Plate: ");
    //                     String plate = sc.nextLine();
    //                     System.out.print("Brand: ");
    //                     String brand = sc.nextLine();
    //                     System.out.print("Model: ");
    //                     String model = sc.nextLine();

    //                     garage.addVehicle(plate, brand, model);
    //                     System.out.println(garage.getLastMessage());
    //                     break;
    //                 }

    //                 case 3: { // Make Vehicle Available
    //                     System.out.print("Vehicle Number Plate: ");
    //                     String plate = sc.nextLine();
    //                     garage.returnVehicle(plate); // mark available
    //                     System.out.println(garage.getLastMessage());
    //                     break;
    //                 }

    //                 case 4: { // Create Order (Rent Vehicle)
    //                     System.out.print("Customer ID: ");
    //                     String customerId = sc.nextLine();
    //                     System.out.print("Vehicle Number Plate: ");
    //                     String plate = sc.nextLine();
    //                     System.out.print("Quantity (Days): ");
    //                     int qty = sc.nextInt();
    //                     sc.nextLine();

    //                     garage.createOrder(customerId, plate, qty);
    //                     System.out.println(garage.getLastMessage());
    //                     break;
    //                 }

    //                 case 5: { // List Customers
    //                     garage.printCustomers();
    //                     break;
    //                 }

    //                 case 6: { // List Vehicles
    //                     garage.printVehicles();
    //                     break;
    //                 }

    //                 case 7: { // List Orders
    //                     garage.printOrders();
    //                     break;
    //                 }

    //                 case 8: { // Logout
    //                     garage.staffLogout();
    //                     System.out.println(garage.getLastMessage());
    //                     break;
    //                 }

    //                 case 0: {
    //                     System.out.println("Goodbye!");
    //                     break;
    //                 }

    //                 default:
    //                     System.out.println("Invalid choice.");
    //             }
    //         }

    //     } while (choice != 0);

    //     sc.close();
    // }

    // // ===== Menu printing in Main =====
    // private static void printMainMenu() {
    //     System.out.println("\n=== MAIN MENU (Not Logged In) ===");
    //     System.out.println("1) Staff Login");
    //     System.out.println("2) View Vehicles");
    //     System.out.println("0) Exit");
    // }

    // private static void printStaffMenu(Garage garage) {
    //     System.out.println("\n=== STAFF MENU (Logged In) ===");
    //     System.out.println("Logged in staff: " + garage.getLoggedInStaff().getFullName());
    //     System.out.println("1) Create Customer");
    //     System.out.println("2) Add Vehicle");
    //     System.out.println("3) Make Vehicle Available");
    //     System.out.println("4) Create Order (Rent Vehicle)");
    //     System.out.println("5) List Customers");
    //     System.out.println("6) List Vehicles");
    //     System.out.println("7) List Orders");
    //     System.out.println("8) Logout");
    //     System.out.println("0) Exit");
    // }
   Staff staff1 = new Staff("S001", "Alice Smith", "0123456789", "Female","password123");
   Staff staff2 = new Staff("S002", "Bob Johnson", "0987654321", "Male", "password456");
   CashierStaff cashier = new CashierStaff(staff1, 10000);
   ManagerStaff managerStaff = new ManagerStaff(staff2, 1500);
   Staff staff = new ManagerStaff(staff2, 1500);
   Object obj =staff;
   System.out.println(obj.getClass()); // need to make something different to test instanceof
// obj is the biggest datatype cannot cast to anything 
}
}