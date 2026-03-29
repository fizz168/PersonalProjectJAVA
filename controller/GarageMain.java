
package controller;

import java.util.Scanner;
import exception.GarageException;
import user.CashierStaff;
import user.ManagerStaff;
import user.SellerStaff;
import user.Staff;

public class GarageMain {

    static Scanner sc = new Scanner(System.in);
    static Garage garage = new Garage("CADT Garage", "Phnom Penh");

    public static void main(String[] args) {
        int choice;

        do {
            // Show menu based on login status
            if (garage.getLoggedInStaff() == null) {
                printMainMenu();
            } else {
                printStaffMenu();
            }

            System.out.print("Choose: ");
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid choice. Please enter a number.");
                choice = -1;
                continue;
            }

            if (garage.getLoggedInStaff() == null) {
                handleMainMenu(choice);
            } else {
                handleStaffMenu(choice);
            }

        } while (true);
    }

    // =====================================================
    // MENU HANDLERS
    // =====================================================

    static void handleMainMenu(int choice) {
        switch (choice) {
            case 1 -> doLogin();
            case 0 -> {
                System.out.println("Goodbye!");
                sc.close();
                System.exit(0);
            }
            default -> System.out.println("[ERROR] Invalid choice.");
        }
    }
    static void handleStaffMenu(int choice) {
        Staff logged = (Staff) garage.getLoggedInStaff();

        // 🛡️ BLOCK UNAUTHORIZED NUMBERS
        if (logged instanceof SellerStaff && (choice == 1 || choice == 4 || choice == 7)) {
            System.out.println("[ERROR] Sellers are not authorized for this action.");
            return;
        }

        switch (choice) {
            case 1  -> doCreateStaff();     // Only Managers (Hidden from others)
            case 2  -> doCreateCustomer();  // Manager & Cashier
            case 3  -> garage.printCustomers();
            case 4  -> garage.printStaffs();
            case 5  -> doAddVehicle();
            case 6  -> garage.printVehicles();
            case 7  -> doReturnVehicle();
            case 8  -> doRentVehicle();
            case 9  -> garage.printOrders();
            case 10 -> doShowMyInfo();
            case 0  -> doLogout();
            default -> System.out.println("[ERROR] Invalid choice.");
        }
    }

    // =====================================================
    // AUTHENTICATION
    // =====================================================

    static void doLogin() {
        System.out.println("\n--- Staff Login ---");
        String username = InputValidator.readNonEmpty(sc, "Username: ");
        String password = InputValidator.readPassword(sc, "Password: ");

        garage.staffLogin(username, password);
        System.out.println((garage.getLastMessage().contains("success") ? "[OK] " : "[ERROR] ") + garage.getLastMessage());
    }

    static void doLogout() {
        garage.staffLogout();
        System.out.println("[OK] " + garage.getLastMessage());
    }

    // =====================================================
    // CORE OPERATIONS
    // =====================================================

    static void doCreateStaff() {
        System.out.println("\n--- Create Staff ---");
        try {
            // ✅ UNIQUENESS CHECK: ID and Phone
            String staffId = InputValidator.readUniqueStaffId(sc, garage, "Staff ID (5 digits): ");
            String fullName = InputValidator.readLettersOnly(sc, "Full Name: ");
            String phone    = InputValidator.readUniquePhone(sc, garage, "Phone (8-15 digits): ");
            
            String username = InputValidator.readNonEmpty(sc, "Username: ");
            String password = InputValidator.readConfirmedPassword(sc);
            
            String salaryStr = InputValidator.readValidSalary(sc, "Salary: ");
            float salary = Float.parseFloat(salaryStr);

            String position = InputValidator.readPosition(sc, "Position (Manager/Cashier/Seller): ");

            garage.createStaff(staffId, fullName, phone, username, password, salary, position);
            InputValidator.checkGarageError(garage.getLastMessage());
            System.out.println("[OK] " + garage.getLastMessage());

        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    static void doCreateCustomer() {
        System.out.println("\n--- Create Customer ---");
        try {
            // ✅ UNIQUENESS CHECK: ID and Phone
           String custId = InputValidator.readUniqueCustomerId(sc, garage, "Customer ID (5 digits): ");
            String fullName = InputValidator.readLettersOnly(sc, "Full Name: ");
            String phone    = InputValidator.readUniquePhone(sc, garage, "Phone: ");
            
            String gender   = InputValidator.readGender(sc, "Gender (M/F): ");
            String password = InputValidator.readConfirmedPassword(sc);
            double balance  = InputValidator.readValidBalance(sc, "Balance ($): ");

            garage.createCustomer(custId, fullName, phone, gender, password, balance);
            InputValidator.checkGarageError(garage.getLastMessage());
            System.out.println("[OK] " + garage.getLastMessage());

        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    static void doAddVehicle() {
        System.out.println("\n--- Add Vehicle ---");
        try {
            String plate = InputValidator.readNonEmpty(sc, "Number Plate: ");
            String brand = InputValidator.readNonEmpty(sc, "Brand: ");
            String model = InputValidator.readNonEmpty(sc, "Model: ");

            garage.addVehicle(plate, brand, model);
            InputValidator.checkGarageError(garage.getLastMessage());
            System.out.println("[OK] " + garage.getLastMessage());
        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    static void doReturnVehicle() {
        System.out.println("\n--- Return Vehicle ---");
        try {
            String plate = InputValidator.readExistingVehiclePlate(sc, garage , "Number Plate: ");
            garage.returnVehicle(plate);
            InputValidator.checkGarageError(garage.getLastMessage());
            System.out.println("[OK] " + garage.getLastMessage());
        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    static void doRentVehicle() {
        System.out.println("\n--- Rent Vehicle ---");
        try {
            String custId = InputValidator.readExistingCustomerId(sc, garage, "CustomerID: ");
            String plate  = InputValidator.readExistingVehiclePlate(sc, garage, "Vehicle Plate: ");
            int days      = InputValidator.readPositiveInt(sc, "Number of Days: ");

            garage.createOrder(custId, plate, days);
            InputValidator.checkGarageError(garage.getLastMessage());
            System.out.println("[OK] " + garage.getLastMessage());
        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    static void doShowMyInfo() {
        try {
            Staff logged = (Staff) garage.getLoggedInStaff();
            if (logged == null) throw new GarageException("No staff is logged in.");

            System.out.println("\n--- My Info ---");
            System.out.println("ID       : " + logged.getStaffId());
            System.out.println("Name     : " + logged.getFullName());
            System.out.println("Phone    : " + logged.getPhone());
            System.out.println("Username : " + logged.getUsername());
            System.out.println("Salary   : $" + logged.getSalary());

            if (logged instanceof ManagerStaff m) {
                System.out.println("Benefit  : $" + m.getBenefit());
                System.out.println("Total Pay: $" + m.getTotalPay());
            }
        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // =====================================================
    // UI DISPLAY
    // =====================================================

    static void printMainMenu() {
        System.out.println("\n========== CADT GARAGE ==========");
        System.out.println("1) Staff Login");
        System.out.println("0) Exit");
        System.out.println("==================================");
    }
  static void printStaffMenu() {
    Staff logged = (Staff) garage.getLoggedInStaff();
    String role = logged.getClass().getSimpleName().replace("Staff", "");
    
    // Header
    System.out.println("\n" + "━━━━━━━━━━━━━━━━━━━━━ " + "DASHBOARD [" + role.toUpperCase() + "]" + " ━━━━━━━━━━━━━━━━━━━━━" + "");
    System.out.println("User: " + logged.getFullName() + " | Status: Active");
    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

    // Dynamic Grid Logic
    if (logged instanceof ManagerStaff) {
        System.out.println(" [1] Hire Staff      [2] Create Customer [3] List Customers");
        System.out.println(" [4] Staff List      [5] Add Vehicle     [6] View Vehicles");
        System.out.println(" [9] Order History   [10] My Profile     [0] Logout");
    } 
    else if (logged instanceof CashierStaff) {
        System.out.println(" [2] New Customer    [3] List Customers  [7] Return Vehicle");
        System.out.println(" [9] Order History   [10] My Profile     [0] Logout");
    } 
    else if (logged instanceof SellerStaff) {
        System.out.println(" [6] View Vehicles   [8] Rent Vehicle    [9] Order History");
        System.out.println(" [10] My Profile     [0] Logout");
    }

    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
}
}