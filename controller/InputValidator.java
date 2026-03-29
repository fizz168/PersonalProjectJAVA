package controller;

import java.util.Scanner;
import java.io.Console;
import exception.GarageException;

public class InputValidator {

    // =====================================================
    // UNIQUE VALIDATORS (ID & PHONE)
    // =====================================================

    /**
     * Checks if ID is 5 digits AND not already in the Garage (Staff or Customer)
     */
    public static String readUnique5DigitId(Scanner sc, Garage garage, String prompt) {
        while (true) {
            String id = read5DigitId(sc, prompt);
            // Check Garage for duplicates (Requires public methods in Garage.java)
            if (garage.findStaffById(id) == null && garage.findCustomerById(id) == null) {
                return id;
            }
            System.out.println("[ERROR] ID '" + id + "' is already registered. Try another.");
        }
    }

    /**
     * Checks if Phone is valid AND not already taken by anyone
     */
    public static String readUniquePhone(Scanner sc, Garage garage, String prompt) {
        while (true) {
            String phone = readValidPhone(sc, prompt);
            // Requires public isPhoneTaken method in Garage.java
            if (!garage.isPhoneTaken(phone)) {
                return phone;
            }
            System.out.println("[ERROR] Phone '" + phone + "' is already in use.");
        }
    }

    // =====================================================
    // CORE VALIDATION METHODS
    // =====================================================

    public static String read5DigitId(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("^\\d{5}$")) return input;
            System.out.println("[ERROR] ID must be exactly 5 numbers.");
        }
    }

    public static String readValidPhone(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("^\\d{8,15}$")) return input;
            System.out.println("[ERROR] Phone must be 8-15 digits.");
        }
    }

    public static String readLettersOnly(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("^[a-zA-Z\\s]+$")) return input;
            System.out.println("[ERROR] Numbers and symbols are not allowed.");
        }
    }

    public static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("[ERROR] This field cannot be empty.");
        }
    }

    public static String readConfirmedPassword(Scanner sc) {
        while (true) {
            String p1 = readPassword(sc, "Enter Password: ");
            String p2 = readPassword(sc, "Confirm Password: ");
            if (!p1.isEmpty() && p1.equals(p2)) return p1;
            System.out.println("[ERROR] Passwords do not match or are empty.");
        }
    }

    public static String readPassword(Scanner sc, String prompt) {
        System.out.print(prompt);
        Console console = System.console();
        if (console != null) {
            return new String(console.readPassword());
        }
        return sc.nextLine().trim();
    }

    public static String readGender(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("male") || input.equals("female") || input.equals("m") || input.equals("f")) {
                if (input.equals("m")) return "Male";
                if (input.equals("f")) return "Female";
                return input.substring(0, 1).toUpperCase() + input.substring(1);
            }
            System.out.println("[ERROR] Use Male/Female or M/F.");
        }
    }

    public static String readPosition(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("manager") || input.equals("cashier") || input.equals("seller")) {
                return input.substring(0, 1).toUpperCase() + input.substring(1);
            }
            System.out.println("[ERROR] Position must be Manager, Cashier, or Seller.");
        }
    }

    public static String readValidSalary(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("^\\d+$")) return input;
            System.out.println("[ERROR] Salary must be a whole number.");
        }
    }

    public static double readValidBalance(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                if (val >= 0) return val;
                System.out.println("[ERROR] Balance cannot be negative.");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Enter a valid number (e.g. 10.50).");
            }
        }
    }

    public static int readPositiveInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val > 0) return val;
                System.out.println("[ERROR] Must be at least 1.");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Enter a whole number.");
            }
        }
    }

    public static void checkGarageError(String msg) throws GarageException {
        String[] keywords = {"denied", "Cannot", "Unknown", "exists", "insufficient", "not found"};
        for (String k : keywords) {
            if (msg.contains(k)) throw new GarageException(msg);
        }
    }
    /**
     * ✅ Only checks if ID exists in the STAFF list
     */
    public static String readUniqueStaffId(Scanner sc, Garage garage, String prompt) {
        while (true) {
            String id = read5DigitId(sc, prompt);
            if (garage.findStaffById(id) == null) {
                return id;
            }
            System.out.println("[ERROR] Staff ID '" + id + "' already exists.");
        }
    }

    /**
     * ✅ Only checks if ID exists in the CUSTOMER list
     */
    public static String readUniqueCustomerId(Scanner sc, Garage garage, String prompt) {
        while (true) {
            String id = read5DigitId(sc, prompt);
            if (garage.findCustomerById(id) == null) {
                return id;
            }
            System.out.println("[ERROR] Customer ID '" + id + "' already exists.");
        }
    }

//// RENT //////
// for renting vehicle, we only need to check if the customer ID exists in the system, not if it's unique. So we can reuse this method to read an existing customer ID.
   public static String readExistingCustomerId(Scanner sc, Garage garage, String prompt){
    while (true) {
        System.out.println(prompt);
        String id = sc.nextLine().trim();
        if(id.isEmpty()) continue;
        if(garage.findCustomerById(id) != null){    
            return id;
        }
        System.out.println("[ERROR] Customer ID '" + id + "' not found. Try again.");      
    }
   }
public static String readExistingVehiclePlate(Scanner sc, Garage garage, String prompt){
    while (true) {
        System.out.println(prompt);
        String plate = sc.nextLine().trim();
        if(plate.isEmpty()) continue;
        if(garage.findVehicleByNumberPlate(plate) != null){    
            return plate;
        }
        System.out.println("[ERROR] Vehicle Plate '" + plate + "' not found. Try again.");      
    }
   }
}