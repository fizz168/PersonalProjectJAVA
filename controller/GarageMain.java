
// package controller;

// import java.util.Scanner;
// import user.ManagerStaff;
// import user.Staff;

// public class GarageMain {

//     static Scanner sc = new Scanner(System.in);
//     static Garage garage = new Garage("CADT Garage", "Phnom Penh");

//     public static void main(String[] args) {

//         int choice;

//         do {
//             // show different menu based on login status
//             if (garage.getLoggedInStaff() == null) {
//                 printMainMenu();
//             } else {
//                 printStaffMenu();
//             }

//             System.out.print("Choose: ");

//             // =====================================================
//             // try-catch: user may type "abc" instead of a number
//             // =====================================================
//             try {
//                 choice = Integer.parseInt(sc.nextLine().trim());
//             } catch (NumberFormatException e) {
//                 System.out.println("[ERROR] Invalid choice. Please enter a number.");
//                 choice = -1;
//                 continue;
//             }

//             if (garage.getLoggedInStaff() == null) {
//                 handleMainMenu(choice);
//             } else {
//                 handleStaffMenu(choice);
//             }

//         } while (true);
//     }

//     // =====================================================
//     // MAIN MENU — not logged in
//     // =====================================================
//     static void handleMainMenu(int choice) {
//         switch (choice) {
//             case 1: doLogin();   break;
//             case 0: {
//                 System.out.println("Goodbye!");
//                 sc.close();
//                 System.exit(0);
//             }
//             default: System.out.println("[ERROR] Invalid choice.");
//         }
//     }

//     // =====================================================
//     // STAFF MENU — logged in
//     // =====================================================
//     static void handleStaffMenu(int choice) {
//         switch (choice) {
//             case 1: doCreateStaff();    break;
//             case 2: doCreateCustomer(); break;
//             case 3: garage.printCustomers(); break;
//             case 4: doAddVehicle();     break;
//             case 5: garage.printVehicles(); break;
//             case 6: doReturnVehicle();  break;
//             case 7: doRentVehicle();    break;
//             case 8: garage.printOrders(); break;
//             case 9: doShowMyInfo();     break;
//             case 0: doLogout();         break;
//             default: System.out.println("[ERROR] Invalid choice.");
//         }
//     }

//     // =====================================================
//     // LOGIN
//     // try-catch + throw + throws + finally
//     // =====================================================
//     static void doLogin() {
//         System.out.print("Username: ");
//         String username = sc.nextLine().trim();
//         System.out.print("Password: ");
//         String password = sc.nextLine().trim();

//         try {
//             loginSafe(username, password);
//             System.out.println("[OK] " + garage.getLastMessage());

//         } catch (GarageException e) {
//             System.out.println("[ERROR] " + e.getMessage());

//         } finally {
//             // finally always runs — whether login succeeded or failed
//             System.out.println("   (Login attempt finished)");
//         }
//     }

//     // throws GarageException — declares it may throw
//     static void loginSafe(String username, String password) throws GarageException {

//         // throw if empty
//         if (username.isEmpty()) {
//             throw new GarageException("Username cannot be empty.");
//         }
//         if (password.isEmpty()) {
//             throw new GarageException("Password cannot be empty.");
//         }

//         garage.staffLogin(username, password);
//         String msg = garage.getLastMessage();

//         if (!msg.contains("success")) {
//             throw new GarageException(msg);
//         }
//     }

//     // =====================================================
//     // LOGOUT
//     // =====================================================
//     static void doLogout() {
//         garage.staffLogout();
//         System.out.println("[OK] " + garage.getLastMessage());
//     }

//     // =====================================================
//     // CREATE STAFF
//     // try-catch NumberFormatException → throw GarageException
//     // =====================================================
//     static void doCreateStaff() {
//         System.out.println("\n--- Create Staff ---");
//         try {
//             System.out.print("Staff ID: ");
//             String staffId = sc.nextLine().trim();

//             System.out.print("Full Name: ");
//             String fullName = sc.nextLine().trim();

//             System.out.print("Phone: ");
//             String phone = sc.nextLine().trim();

//             System.out.print("Username: ");
//             String username = sc.nextLine().trim();

//             System.out.print("Password: ");
//             String password = sc.nextLine().trim();

//             System.out.print("Salary: ");
//             String salaryStr = sc.nextLine().trim();

//             System.out.print("Position (Manager/Cashier/Seller): ");
//             String position = sc.nextLine().trim();

//             // validate empty fields
//             if (staffId.isEmpty() || fullName.isEmpty() || salaryStr.isEmpty()) {
//                 throw new GarageException("Staff ID, Name, and Salary cannot be empty.");
//             }

//             // parse salary — catch NumberFormatException
//             float salary;
//             try {
//                 salary = Float.parseFloat(salaryStr);
//             } catch (NumberFormatException e) {
//                 throw new GarageException("Salary must be a number. You entered: '" + salaryStr + "'");
//             }

//             // throw if negative
//             if (salary < 0) {
//                 throw new GarageException("Salary cannot be negative.");
//             }

//             garage.createStaff(staffId, fullName, phone, username, password, salary, position);
//             String msg = garage.getLastMessage();

//             if (msg.contains("denied") || msg.contains("Cannot") || msg.contains("Unknown")) {
//                 throw new GarageException(msg);
//             }

//             System.out.println("[OK] " + msg);

//         } catch (GarageException e) {
//             System.out.println("[ERROR] " + e.getMessage());
//         } finally {
//             System.out.println("   (Create staff attempt finished)");
//         }
//     }

//     // =====================================================
//     // CREATE CUSTOMER
//     // try-catch + throw + throws + finally
//     // =====================================================
//     static void doCreateCustomer() {
//         System.out.println("\n--- Create Customer ---");
//         try {
//             System.out.print("Customer ID: ");
//             String custId = sc.nextLine().trim();

//             System.out.print("Full Name: ");
//             String fullName = sc.nextLine().trim();

//             System.out.print("Phone: ");
//             String phone = sc.nextLine().trim();

//             System.out.print("Gender: ");
//             String gender = sc.nextLine().trim();

//             System.out.print("Password: ");
//             String password = sc.nextLine().trim();

//             System.out.print("Balance ($): ");
//             String balStr = sc.nextLine().trim();

//             if (custId.isEmpty() || fullName.isEmpty() || balStr.isEmpty()) {
//                 throw new GarageException("Customer ID, Name, and Balance cannot be empty.");
//             }

//             double balance;
//             try {
//                 balance = Double.parseDouble(balStr);
//             } catch (NumberFormatException e) {
//                 throw new GarageException("Balance must be a number. You entered: '" + balStr + "'");
//             }

//             if (balance < 0) {
//                 throw new GarageException("Balance cannot be negative.");
//             }

//             garage.createCustomer(custId, fullName, phone, gender, password, balance);
//             String msg = garage.getLastMessage();

//             if (msg.contains("denied") || msg.contains("exists") || msg.contains("Cannot")) {
//                 throw new GarageException(msg);
//             }

//             System.out.println("[OK] " + msg);

//         } catch (GarageException e) {
//             System.out.println("[ERROR] " + e.getMessage());
//         } finally {
//             System.out.println("   (Create customer attempt finished)");
//         }
//     }

//     // =====================================================
//     // ADD VEHICLE
//     // =====================================================
//     static void doAddVehicle() {
//         System.out.println("\n--- Add Vehicle ---");
//         try {
//             System.out.print("Number Plate: ");
//             String plate = sc.nextLine().trim();

//             System.out.print("Brand: ");
//             String brand = sc.nextLine().trim();

//             System.out.print("Model: ");
//             String model = sc.nextLine().trim();

//             if (plate.isEmpty() || brand.isEmpty() || model.isEmpty()) {
//                 throw new GarageException("Plate, Brand, and Model cannot be empty.");
//             }

//             garage.addVehicle(plate, brand, model);
//             String msg = garage.getLastMessage();

//             if (msg.contains("denied") || msg.contains("exists")) {
//                 throw new GarageException(msg);
//             }

//             System.out.println("[OK] " + msg);

//         } catch (GarageException e) {
//             System.out.println("[ERROR] " + e.getMessage());
//         }
//     }

//     // =====================================================
//     // RETURN VEHICLE
//     // =====================================================
//     static void doReturnVehicle() {
//         System.out.println("\n--- Return Vehicle ---");
//         try {
//             System.out.print("Number Plate: ");
//             String plate = sc.nextLine().trim();

//             if (plate.isEmpty()) {
//                 throw new GarageException("Plate cannot be empty.");
//             }

//             garage.returnVehicle(plate);
//             String msg = garage.getLastMessage();

//             if (msg.contains("not found") || msg.contains("denied")) {
//                 throw new GarageException(msg);
//             }

//             System.out.println("[OK] " + msg);

//         } catch (GarageException e) {
//             System.out.println("[ERROR] " + e.getMessage());
//         }
//     }

//     // =====================================================
//     // RENT VEHICLE
//     // try-catch NumberFormatException on days input
//     // =====================================================
//     static void doRentVehicle() {
//         System.out.println("\n--- Rent Vehicle ---");
//         try {
//             System.out.print("Customer ID: ");
//             String custId = sc.nextLine().trim();

//             System.out.print("Vehicle Plate: ");
//             String plate = sc.nextLine().trim();

//             System.out.print("Number of Days: ");
//             String daysStr = sc.nextLine().trim();

//             if (custId.isEmpty() || plate.isEmpty() || daysStr.isEmpty()) {
//                 throw new GarageException("Customer ID, Plate, and Days cannot be empty.");
//             }

//             // parse days — user might type "abc"
//             int days;
//             try {
//                 days = Integer.parseInt(daysStr);
//             } catch (NumberFormatException e) {
//                 throw new GarageException("Days must be a whole number. You entered: '" + daysStr + "'");
//             }

//             if (days <= 0) {
//                 throw new GarageException("Days must be at least 1.");
//             }

//             garage.createOrder(custId, plate, days);
//             String msg = garage.getLastMessage();

//             if (msg.contains("Cannot") || msg.contains("denied") || msg.contains("insufficient")) {
//                 throw new GarageException(msg);
//             }

//             System.out.println("[OK] " + msg);

//         } catch (GarageException e) {
//             System.out.println("[ERROR] " + e.getMessage());
//         } finally {
//             System.out.println("   (Rent attempt finished)");
//         }
//     }

//     // =====================================================
//     // SHOW MY INFO
//     // instanceof to safely access ManagerStaff fields
//     // =====================================================
//     static void doShowMyInfo() {
//         try {
//             Staff logged = (Staff) garage.getLoggedInStaff();
//             if (logged == null) {
//                 throw new GarageException("No staff is logged in.");
//             }

//             System.out.println("\n--- My Info ---");
//             System.out.println("ID       : " + logged.getStaffId());
//             System.out.println("Name     : " + logged.getFullName());
//             System.out.println("Phone    : " + logged.getPhone());
//             System.out.println("Username : " + logged.getUsername());
//             System.out.println("Salary   : $" + logged.getSalary());
//             System.out.println("Active   : " + logged.isActive());

//             // instanceof — safe check before casting
//             if (logged instanceof ManagerStaff) {
//                 ManagerStaff m = (ManagerStaff) logged;
//                 System.out.println("Benefit  : $" + m.getBenefit());
//                 System.out.println("Total Pay: $" + m.getTotalPay());
//             }

//         } catch (GarageException e) {
//             System.out.println("[ERROR] " + e.getMessage());
//         }
//     }

//     // =====================================================
//     // MENUS
//     // =====================================================
//     static void printMainMenu() {
//         System.out.println("\n========== CADT GARAGE ==========");
//         System.out.println("1) Staff Login");
//         System.out.println("0) Exit");
//         System.out.println("==================================");
//     }

//     static void printStaffMenu() {
//         Staff logged = (Staff) garage.getLoggedInStaff();
//         System.out.println("\n========== STAFF MENU ==========");
//         System.out.println("Logged in: " + logged.getFullName()
//                 + " | Salary: $" + logged.getSalary());
//         System.out.println("---------------------------------");
//         System.out.println("1) Create Staff");
//         System.out.println("2) Create Customer");
//         System.out.println("3) List Customers");
//         System.out.println("4) Add Vehicle");
//         System.out.println("5) List Vehicles");
//         System.out.println("6) Return Vehicle");
//         System.out.println("7) Rent Vehicle");
//         System.out.println("8) List Orders");
//         System.out.println("9) My Info");
//         System.out.println("0) Logout");
//         System.out.println("=================================");
//     }
// }
package controller;

import java.io.Console;
import java.util.Scanner;
import user.ManagerStaff;
import user.Staff;

public class GarageMain {

    static Scanner sc = new Scanner(System.in);
    static Garage garage = new Garage("CADT Garage", "Phnom Penh");

    public static void main(String[] args) {

        int choice;

        do {
            // show different menu based on login status
            if (garage.getLoggedInStaff() == null) {
                printMainMenu();
            } else {
                printStaffMenu();
            }

            System.out.print("Choose: ");

            // =====================================================
            // try-catch: user may type "abc" instead of a number
            // =====================================================
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
    // MAIN MENU — not logged in
    // =====================================================
    static void handleMainMenu(int choice) {
        switch (choice) {
            case 1: doLogin(); break;
            case 0: {
                System.out.println("Goodbye!");
                sc.close();
                System.exit(0);
            }
            default: System.out.println("[ERROR] Invalid choice.");
        }
    }

    // =====================================================
    // STAFF MENU — logged in
    // =====================================================
    static void handleStaffMenu(int choice) {
        switch (choice) {
            case 1:  doCreateStaff();         break;
            case 2:  doCreateCustomer();      break;
            case 3:  garage.printCustomers(); break;
            case 4:  garage.printStaffs();    break;  // ✅ ADDED — list staff
            case 5:  doAddVehicle();          break;
            case 6:  garage.printVehicles();  break;
            case 7:  doReturnVehicle();       break;
            case 8:  doRentVehicle();         break;
            case 9:  garage.printOrders();    break;
            case 10: doShowMyInfo();          break;
            case 0:  doLogout();              break;
            default: System.out.println("[ERROR] Invalid choice.");
        }
    }

    // =====================================================
    // LOGIN
    // try-catch + throw + throws + finally
    // =====================================================
    static void doLogin() {
        System.out.print("Username: ");
        String username = sc.nextLine().trim();
        String password = readPassword("Password: "); // ✅ CHANGED — hidden input

        try {
            loginSafe(username, password);
            System.out.println("[OK] " + garage.getLastMessage());

        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());

        } finally {
            // finally always runs — whether login succeeded or failed
            System.out.println("   (Login attempt finished)");
        }
    }

    // throws GarageException — declares it may throw
    static void loginSafe(String username, String password) throws GarageException {

        // throw if empty
        if (username.isEmpty()) {
            throw new GarageException("Username cannot be empty.");
        }
        if (password.isEmpty()) {
            throw new GarageException("Password cannot be empty.");
        }

        garage.staffLogin(username, password);
        String msg = garage.getLastMessage();

        if (!msg.contains("success")) {
            throw new GarageException(msg);
        }
    }

    // =====================================================
    // LOGOUT
    // =====================================================
    static void doLogout() {
        garage.staffLogout();
        System.out.println("[OK] " + garage.getLastMessage());
    }

    static void doCreateStaff() {
    System.out.println("\n--- Create Staff ---");
    try {
        // 1. Force 5-digit ID (Uses your read5DigitId method)
        String staffId = read5DigitId("Staff ID (5 digits): ");

        // 2. Force Letters-only Name (Uses your readLettersOnly method)
        String fullName = readLettersOnly("Full Name: ");

        // 3. Keep standard input for Phone and Username
        System.out.print("Phone: ");
        String phone = read12DigitPhone("Phone (8-15 digits): ");

        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        // 4. Force Double Password (Uses your readConfirmedPassword method)
        String password = readConfirmedPassword();

        // 5. Salary Validation (Keep your existing try-catch logic)
        String salaryStr = readValidSalary("Salary (integers only): ");
        float salary = Float.parseFloat(salaryStr);
        try {
            salary = Float.parseFloat(salaryStr);
        } catch (NumberFormatException e) {
            throw new GarageException("Salary must be a number. You entered: '" + salaryStr + "'");
        }

        if (salary < 0) {
            throw new GarageException("Salary cannot be negative.");
        }

        System.out.print("Position (Manager/Cashier/Seller): ");
        String position = sc.nextLine().trim();

        // Send the validated data to the Garage
        garage.createStaff(staffId, fullName, phone, username, password, salary, position);
        String msg = garage.getLastMessage();

        if (msg.contains("denied") || msg.contains("Cannot") || msg.contains("Unknown")) {
            throw new GarageException(msg);
        }

        System.out.println("[OK] " + msg);

    } catch (GarageException e) {
        System.out.println("[ERROR] " + e.getMessage());
    } finally {
        System.out.println("   (Create staff attempt finished)");
    }
}

    // =====================================================
    // CREATE CUSTOMER
    // try-catch + throw + throws + finally
    // =====================================================
  static void doCreateCustomer() {
    System.out.println("\n--- Create Customer ---");
    try {
        String custId = read5DigitId("Customer ID (5 digits): ");
        String fullName = readLettersOnly("Full Name: ");
        String phone = read12DigitPhone("Phone (8-15 digits): ");

        System.out.print("Gender: ");
        String gender = sc.nextLine().trim();

        // Use your confirmed password method here too for safety
        String password = readConfirmedPassword();

        // ✅ THE FIX: Loops until a valid positive number is entered
        double balance = readValidBalance("Balance ($): ");

        garage.createCustomer(custId, fullName, phone, gender, password, balance);
        
        String msg = garage.getLastMessage();
        if (msg.contains("denied") || msg.contains("exists") || msg.contains("Cannot")) {
            throw new GarageException(msg);
        }

        System.out.println("[OK] " + msg);

    } catch (GarageException e) {
        System.out.println("[ERROR] " + e.getMessage());
    } finally {
        System.out.println("   (Create customer attempt finished)");
    }
}
    // =====================================================
    // ADD VEHICLE
    // =====================================================
    static void doAddVehicle() {
        System.out.println("\n--- Add Vehicle ---");
        try {
            System.out.print("Number Plate: ");
            String plate = sc.nextLine().trim();

            System.out.print("Brand: ");
            String brand = sc.nextLine().trim();

            System.out.print("Model: ");
            String model = sc.nextLine().trim();

            if (plate.isEmpty() || brand.isEmpty() || model.isEmpty()) {
                throw new GarageException("Plate, Brand, and Model cannot be empty.");
            }

            garage.addVehicle(plate, brand, model);
            String msg = garage.getLastMessage();

            if (msg.contains("denied") || msg.contains("exists")) {
                throw new GarageException(msg);
            }

            System.out.println("[OK] " + msg);

        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // =====================================================
    // RETURN VEHICLE
    // =====================================================
    static void doReturnVehicle() {
        System.out.println("\n--- Return Vehicle ---");
        try {
            System.out.print("Number Plate: ");
            String plate = sc.nextLine().trim();

            if (plate.isEmpty()) {
                throw new GarageException("Plate cannot be empty.");
            }

            garage.returnVehicle(plate);
            String msg = garage.getLastMessage();

            if (msg.contains("not found") || msg.contains("denied")) {
                throw new GarageException(msg);
            }

            System.out.println("[OK] " + msg);

        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // =====================================================
    // RENT VEHICLE
    // try-catch NumberFormatException on days input
    // =====================================================
    static void doRentVehicle() {
        System.out.println("\n--- Rent Vehicle ---");
        try {
            System.out.print("Customer ID: ");
            String custId = read5DigitId("5 didgit");

            System.out.print("Vehicle Plate: ");
            String plate = read12DigitPhone(custId);

            System.out.print("Number of Days: ");
            String daysStr = sc.nextLine().trim();

            if (custId.isEmpty() || plate.isEmpty() || daysStr.isEmpty()) {
                throw new GarageException("Customer ID, Plate, and Days cannot be empty.");
            }

            // parse days — user might type "abc"
            int days;
            try {
                days = Integer.parseInt(daysStr);
            } catch (NumberFormatException e) {
                throw new GarageException("Days must be a whole number. You entered: '" + daysStr + "'");
            }

            if (days <= 0) {
                throw new GarageException("Days must be at least 1.");
            }

            garage.createOrder(custId, plate, days);
            String msg = garage.getLastMessage();

            if (msg.contains("Cannot") || msg.contains("denied") || msg.contains("insufficient")) {
                throw new GarageException(msg);
            }

            System.out.println("[OK] " + msg);

        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        } finally {
            System.out.println("   (Rent attempt finished)");
        }
    }

    // =====================================================
    // SHOW MY INFO
    // instanceof to safely access ManagerStaff fields
    // =====================================================
    static void doShowMyInfo() {
        try {
            Staff logged = (Staff) garage.getLoggedInStaff();
            if (logged == null) {
                throw new GarageException("No staff is logged in.");
            }

            System.out.println("\n--- My Info ---");
            System.out.println("ID       : " + logged.getStaffId());
            System.out.println("Name     : " + logged.getFullName());
            System.out.println("Phone    : " + logged.getPhone());
            System.out.println("Username : " + logged.getUsername());
            System.out.println("Salary   : $" + logged.getSalary());
            System.out.println("Active   : " + logged.isActive());

            // instanceof — safe check before casting
            if (logged instanceof ManagerStaff) {
                ManagerStaff m = (ManagerStaff) logged;
                System.out.println("Benefit  : $" + m.getBenefit());
                System.out.println("Total Pay: $" + m.getTotalPay());
            }

        } catch (GarageException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // =====================================================
    // READ PASSWORD — hides input in real terminal
    // Returns hidden input if Console available,
    // falls back to Scanner in IDE
    // =====================================================
    
    static String readPassword(String prompt) {
        System.out.print(prompt);
        Console console = System.console();
        if (console != null) {
            // real terminal — password is hidden
            return new String(console.readPassword());
        } else {
            // IDE fallback — password visible (Console not supported in IDE)
            return sc.nextLine().trim();
        }
    }
    // 1. Put this in GarageMain to force Letters-only
private static String readLettersOnly(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.matches("^[a-zA-Z\\s]+$")) {
            return input;
        }
        System.out.println("[ERROR] Numbers and symbols are not allowed. Try again.");
    }
}

// 2. Put this in GarageMain for the 5-digit ID
private static String read5DigitId(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.matches("^\\d{5}$")) {
            return input;
        }
        System.out.println("[ERROR] ID must be exactly 5 numbers. Try again.");
    }
}

// 3. Put this in GarageMain for the double password
private static String readConfirmedPassword() {
    while (true) {
        String p1 = readPassword("Enter Password: ");
        String p2 = readPassword("Confirm Password: ");
        if (!p1.isEmpty() && p1.equals(p2)) {
            return p1;
        }
        System.out.println("[ERROR] Passwords do not match. Try again.");
    }
}
private static String read12DigitPhone(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.matches("^\\d{8,15}$")) {
            return input;
        }
        System.out.println("[ERROR] Phone must be 8-15 digits. Try again.");
    }
}
private static String readValidSalary(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        
        // Regex: ^\d+$ checks for 1 or more digits only
        if (input.matches("^\\d+$")) {
            return input;
        }
        
        System.out.println("[ERROR] Salary must be a whole number (integers only). Try again.");
    }
}
private static double readValidBalance(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        try {
            double value = Double.parseDouble(input);
            if (value >= 0) {
                return value; // Success
            }
            System.out.println("[ERROR] Balance cannot be negative.");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Balance must be a number (e.g., 50 or 10.50).");
        }
    }
}

    // =====================================================
    // MENUS
    // =====================================================
    static void printMainMenu() {
        System.out.println("\n========== CADT GARAGE ==========");
        System.out.println("1) Staff Login");
        System.out.println("0) Exit");
        System.out.println("==================================");
    }

    static void printStaffMenu() {
        Staff logged = (Staff) garage.getLoggedInStaff();
        System.out.println("\n========== STAFF MENU ==========");
        System.out.println("Logged in: " + logged.getFullName()
                + " | Salary: $" + logged.getSalary());
        System.out.println("---------------------------------");
        System.out.println("1)  Create Staff");
        System.out.println("2)  Create Customer");
        System.out.println("3)  List Customers");
        System.out.println("4)  List Staffs");
        System.out.println("5)  Add Vehicle");
        System.out.println("6)  List Vehicles");
        System.out.println("7)  Return Vehicle");
        System.out.println("8)  Rent Vehicle");
        System.out.println("9)  List Orders");
        System.out.println("10) My Info");
        System.out.println("0)  Logout");
        System.out.println("=================================");
    }
}