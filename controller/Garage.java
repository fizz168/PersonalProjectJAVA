
package controller;

import java.util.ArrayList;
import other.Customer;
import other.Order;
import other.Vehicle;
import user.CashierStaff;
import user.IStaff;
import user.ManagerStaff;
import user.SellerStaff;
import user.Staff;
import user.StaffFilter;   

public class Garage {

    public static final String CREATE_STAFF     = "CREATE_STAFF";
    public static final String CREATE_CUSTOMER  = "CREATE_CUSTOMER";
    public static final String CREATE_MENU_ITEM = "CREATE_MENU_ITEM";
    public static final String SET_MENU_ITEM_AVAILABILITY = "SET_MENU_ITEM_AVAILABILITY";
    public static final String CREATE_ORDER     = "CREATE_ORDER";
    public static final String VIEW_CUSTOMERS   = "VIEW_CUSTOMERS";
    public static final String VIEW_ORDERS      = "VIEW_ORDERS";
    public static final String UPDATE_ORDER_STATUS = "UPDATE_ORDER_STATUS";

    private String garageName;
    private String garageAddress;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Staff> staffs;
    private ArrayList<Order> orders;
    private ArrayList<Customer> customers;
    private Staff loggedInStaff;
    private String lastMessage;

    // =========================
    // CONSTRUCTOR
    // =========================
    public Garage(String garageName, String garageAddress) {
        setGarageName(garageName);
        setGarageAddress(garageAddress);

        vehicles  = new ArrayList<>();
        staffs    = new ArrayList<>();
        orders    = new ArrayList<>();
        customers = new ArrayList<>();
        loggedInStaff = null;

        seedDefaultAdmin();
        lastMessage = "Garage created. Default staff: admin / 1234";
    }

    // =========================
    // GETTERS / SETTERS
    // =========================
    public String getGarageName()    { return garageName; }
    public String getGarageAddress() { return garageAddress; }
    public String getLastMessage()   { return lastMessage; }
    public IStaff getLoggedInStaff() { return loggedInStaff; }

    public void setGarageName(String garageName) {
        if (isBlank(garageName)) this.garageName = "CADT Garage";
        else this.garageName = garageName.trim();
    }

    public void setGarageAddress(String garageAddress) {
        if (isBlank(garageAddress)) this.garageAddress = "Unknown";
        else this.garageAddress = garageAddress.trim();
    }

    private void setLastMessage(String msg) { lastMessage = msg; }
    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }

    // =========================
    // SEED DEFAULT ADMIN
    // =========================
    private void seedDefaultAdmin() {
        ManagerStaff admin = new ManagerStaff("S001", "Admin", "010000000", "admin", "1234", 2000);
        admin.setBenefit(500);
        staffs.add(admin);
    }

    // =========================
    // LOGIN / LOGOUT
    // =========================
    private boolean requireStaffLogin() {
        if (loggedInStaff == null) {
            setLastMessage("Action denied: staff must login first.");
            return false;
        }
        if (!loggedInStaff.isActive()) {
            loggedInStaff = null;
            setLastMessage("Action denied: staff is inactive (auto logout).");
            return false;
        }
        return true;
    }

    private boolean requirePermission(String action) {
        if (!requireStaffLogin()) return false;
        if (!loggedInStaff.can(action)) {
            setLastMessage("Permission denied: you cannot perform [" + action + "]");
            return false;
        }
        return true;
    }

    public void staffLogin(String username, String password) {
        if (isBlank(username) || password == null) {
            setLastMessage("Login failed: missing username/password.");
            return;
        }
        for (Staff s : staffs) {
            if (s.getUsername().equalsIgnoreCase(username.trim())) {
                if (!s.isActive()) {
                    setLastMessage("Login failed: staff is inactive.");
                    return;
                }
                if (!s.checkPassword(password)) {
                    setLastMessage("Login failed: wrong password.");
                    return;
                }
                loggedInStaff = s;
                setLastMessage("Login success. Welcome " + s.getFullName() + "!");
                return;
            }
        }
        setLastMessage("Login failed: username not found.");
    }

    public void staffLogout() {
        loggedInStaff = null;
        setLastMessage("Logged out successfully.");
    }

    // =========================
    // STAFF MANAGEMENT
    // =========================
    public void createStaff(String staffId, String fullName, String phone,
                            String username, String password, float salary, String position) {

        if (!requirePermission(CREATE_STAFF)) return;

        if (isBlank(staffId) || isBlank(username)) {
            setLastMessage("Cannot create staff: staffId/username is empty.");
            return;
        }

        // duplicate username check
        for (Staff s : staffs) {
            if (s.getUsername().equalsIgnoreCase(username.trim())) {
                setLastMessage("Cannot create staff: username already exists.");
                return;
            }
        }

        if (position.equalsIgnoreCase("Manager")) {
            ManagerStaff m = new ManagerStaff(staffId, fullName, phone, username, password, salary);
            staffs.add(m);
            setLastMessage("Manager [" + fullName + "] created successfully.");

        } else if (position.equalsIgnoreCase("Cashier")) {
            staffs.add(new CashierStaff(staffId, fullName, phone, username, password, salary));
            setLastMessage("Cashier [" + fullName + "] created successfully.");

        } else if (position.equalsIgnoreCase("Seller")) {
            staffs.add(new SellerStaff(staffId, fullName, phone, username, password, salary));
            setLastMessage("Seller [" + fullName + "] created successfully.");

        } else {
            setLastMessage("Unknown position: " + position + ". Use Manager/Cashier/Seller.");
        }
    }

    // =========================
    // CUSTOMER MANAGEMENT
    // =========================
    public void createCustomer(String customerId, String fullName, String phone,
                               String gender, String password, double balance) {

        if (!requirePermission(CREATE_CUSTOMER)) return;

        for (Customer c : customers) {
            if (c.getCustomerPersonalId().equalsIgnoreCase(customerId)) {
                setLastMessage("Customer ID already exists.");
                return;
            }
            if (c.getCustomerPhoneNumber().equals(phone)) {
                setLastMessage("Phone number already used.");
                return;
            }
        }

        customers.add(new Customer(customerId, fullName, phone, gender, password, balance));
        setLastMessage("Customer [" + fullName + "] created successfully.");
    }

    public void printCustomers() {
        System.out.println("\n--- Customers (" + customers.size() + ") ---");
        if (customers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i + 1) + ") "
                    + "ID: " + c.getCustomerPersonalId()
                    + " | Name: " + c.getCustomerName()
                    + " | Phone: " + c.getCustomerPhoneNumber()
                    + " | Balance: $" + c.getBalance());
        }
    }

    // private Customer findCustomerById(String customerId) {
    //     if (isBlank(customerId)) return null;
    //     for (Customer c : customers) {
    //         if (c.getCustomerPersonalId().equalsIgnoreCase(customerId.trim())) return c;
    //     }
    //     return null;
    // }

    // =========================
    // VEHICLE MANAGEMENT
    // =========================
    public void addVehicle(String numberPlate, String brand, String model) {
        if (!requirePermission(CREATE_STAFF)) return;
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumberPlate().equalsIgnoreCase(numberPlate)) {
                setLastMessage("Vehicle number plate already exists.");
                return;
            }
        }
        vehicles.add(new Vehicle(numberPlate, brand, model));
        setLastMessage("Vehicle [" + brand + " " + model + "] added successfully.");
    }

    public void printVehicles() {
        System.out.println("\n--- Vehicles (" + vehicles.size() + ") ---");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles.");
            return;
        }
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            System.out.println((i + 1) + ") "
                    + "Plate: " + v.getVehicleNumberPlate()
                    + " | " + v.getBrand() + " " + v.getModel()
                    + " | $" + v.getPricePerDay() + "/day"
                    + " | Type: " + v.getVehicleType()
                    + " | Available: " + (v.isAvalible() ? "✅ Yes" : "❌ No (Rented)"));
        }
    }

    public Vehicle findVehicleByNumberPlate(String numberPlate) {
        if (isBlank(numberPlate)) return null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumberPlate().equalsIgnoreCase(numberPlate.trim())) return v;
        }
        return null;
    }
    /**
     * ✅ NEW: Finds a vehicle by its plate. 
     * Make sure this is PUBLIC so InputValidator can use it.
     */
   
    public void returnVehicle(String vehicleNumberPlate) {
        if (!requireStaffLogin()) return;
        Vehicle v = findVehicleByNumberPlate(vehicleNumberPlate);
        if (v == null) {
            setLastMessage("Vehicle not found.");
            return;
        }
        v.returnCar();
        setLastMessage("Vehicle [" + v.getVehicleNumberPlate() + "] is now available for rent.");
    }

    // =========================
    // ORDER / RENTAL MANAGEMENT
    // =========================
    public void createOrder(String customerId, String vehicleNumberPlate, int days) {

        if (!requirePermission(CREATE_ORDER)) return;

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            setLastMessage("Cannot rent: customer not found.");
            return;
        }

        Vehicle vehicle = findVehicleByNumberPlate(vehicleNumberPlate);
        if (vehicle == null) {
            setLastMessage("Cannot rent: vehicle not found.");
            return;
        }

        if (!vehicle.isAvalible()) {
            setLastMessage("Cannot rent: vehicle [" + vehicleNumberPlate + "] is already rented.");
            return;
        }

        if (days <= 0) {
            setLastMessage("Cannot rent: number of days must be at least 1.");
            return;
        }

        double totalCost = vehicle.getPricePerDay() * days;
        if (customer.getBalance() < totalCost) {
            setLastMessage("Cannot rent: insufficient balance. "
                    + "Need $" + totalCost + " but only have $" + customer.getBalance());
            return;
        }

        customer.setBalance(customer.getBalance() - totalCost);

        String orderId = "ORD" + (orders.size() + 1);
        Order newOrder = new Order(orderId, customer, vehicle, days, loggedInStaff);
        orders.add(newOrder);

        vehicle.rent();

        setLastMessage("Rental successful! Order [" + orderId + "] | "
                + customer.getCustomerName() + " rented " + vehicle.getBrand() + " " + vehicle.getModel()
                + " for " + days + " day(s) | Total: $" + totalCost
                + " | Remaining balance: $" + customer.getBalance());
    }

    public void printOrders() {
        System.out.println("\n--- Rental Orders (" + orders.size() + ") ---");
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            System.out.println((i + 1) + ") "
                    + "Order: " + o.getOrderId()
                    + " | Customer: " + o.getCustomer().getCustomerName()
                    + " | Vehicle: " + o.getItem().getBrand() + " " + o.getItem().getModel()
                    + " | Days: " + o.getQuantity()
                    + " | Total: $" + o.getTotalPrice()
                    + " | Staff: " + (o.getCreatedBy() != null ? o.getCreatedBy().getFullName() : "Unknown"));
        }
    }
    // 1. Fixes the error in GarageMain (case 4)
    public void printStaffs() {
        System.out.println("\n--- Staff List (" + staffs.size() + ") ---");
        if (staffs.isEmpty()) {
            System.out.println("No staff members found.");
            return;
        }
        for (int i = 0; i < staffs.size(); i++) {
            Staff s = staffs.get(i);
            System.out.println((i + 1) + ") ID: " + s.getStaffId() 
                + " | Name: " + s.getFullName() 
                + " | Role: " + s.getClass().getSimpleName());
        }
    }

    // 2. Fixes the error in InputValidator (Line 20)
    public Staff findStaffById(String staffId) {
        if (staffId == null) return null;
        for (Staff s : staffs) {
            if (s.getStaffId().equalsIgnoreCase(staffId.trim())) return s;
        }
        return null;
    }

    // 3. Fixes the visibility error in InputValidator (Line 20)
    // Make sure your existing findCustomerById is changed from 'private' to 'public'
    public Customer findCustomerById(String customerId) {
        if (customerId == null) return null;
        for (Customer c : customers) {
            if (c.getCustomerPersonalId().equalsIgnoreCase(customerId.trim())) return c;
        }
        return null;
    }

    // 4. Fixes the error in InputValidator (Line 34)
    public boolean isPhoneTaken(String phone) {
        if (phone == null) return false;
        String p = phone.trim();
        // Check staff list
        for (Staff s : staffs) {
            if (s.getPhone().equals(p)) return true;
        }
        // Check customer list
        for (Customer c : customers) {
            if (c.getCustomerPhoneNumber().equals(p)) return true;
        }
        return false;
    }


    // TASK 3: Anonymous Inner Class   
 
    public void printActiveStaff() {
        System.out.println("\n--- Active Staff (Anonymous Inner Class) ---");

        // Anonymous inner class — no separate file needed
        // behavior defined right here, used once
        StaffFilter filter = new StaffFilter() {
            @Override
            public boolean test(Staff s) {
                return s.isActive();
            }
        };

        for (Staff s : staffs) {
            if (filter.test(s)) {
                System.out.println("  " + s.getFullName()
                        + " | " + s.getUsername()
                        + " | Salary: $" + s.getSalary());
            }
        }
    }

  
    // TASK 4: Lambda Expression      
  
    public void printStaffByFilter(StaffFilter filter, String label) {
        System.out.println("\n--- " + label + " ---");
        for (Staff s : staffs) {
            if (filter.test(s)) {
                System.out.println("  " + s.getFullName()
                        + " | " + s.getUsername()
                        + " | Salary: $" + s.getSalary());
            }
        }
    }

    @Override
    public String toString() {
        return "Garage{name=" + garageName + ", address=" + garageAddress
                + ", staffs=" + staffs.size()
                + ", customers=" + customers.size()
                + ", vehicles=" + vehicles.size()
                + ", orders=" + orders.size() + "}";
    }

}