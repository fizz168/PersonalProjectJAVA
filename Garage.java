// import java.util.ArrayList;

// public class Garage {

//     private String garageName;
//     private String garageAddress;
//     private ArrayList<Vehicle> vehicles;
//     private ArrayList<IStaff> staffs;
//     private ArrayList<Order> orders;
//     private ArrayList<Customer> customers;
//     private IStaff loggedInStaff;
//     private String lastMessage;

//     public Garage(String garageName, String garageAddress) {
//         setGarageName(garageName);
//         setGarageAddress(garageAddress);

//         vehicles = new ArrayList<>();
//         staffs = new ArrayList<>();
//         orders = new ArrayList<>();
//         customers = new ArrayList<>();
//         loggedInStaff = null;

//         seedDefaultAdmin();
//         lastMessage = "Garage created. Default staff: admin / 1234";
//     }

//     public String getGarageName() { return garageName; }
//     public String getGarageAddress() { return garageAddress; }
//     public String getLastMessage() { return lastMessage; }
//     public IStaff getLoggedInStaff() { return loggedInStaff; }

//     public void setGarageName(String garageName) {
//         if (isBlank(garageName)) this.garageName = "CADT Garage";
//         else this.garageName = garageName.trim();
//     }

//     public void setGarageAddress(String garageAddress) {
//         if (isBlank(garageAddress)) this.garageAddress = "Unknown";
//         else this.garageAddress = garageAddress.trim();
//     }

//     private void setLastMessage(String msg) { lastMessage = msg; }

//     private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }

//     private void seedDefaultAdmin() {
//         // Using your original Staff interface
//         ManagerStaff admin = new ManagerStaff("S001", "Admin", "010000000", "admin", "1234", "Manager");
//         staffs.add(admin);
//     }

//     private boolean requireStaffLogin() {
//         if (loggedInStaff == null) {
//             setLastMessage("Action denied: staff must login first.");
//             return false;
//         }
//         if (!loggedInStaff.isActive()) {
//             loggedInStaff = null;
//             setLastMessage("Action denied: staff is inactive (auto logout).");
//             return false;
//         }
//         return true;
//     }

//     private boolean requirePermission(String action) {
//         return requireStaffLogin();
//         // Can later add action-specific permissions
//     }

//     public void staffLogin(String username, String password) {
//         if (isBlank(username) || password == null) {
//             setLastMessage("Login failed: missing username/password.");
//             return;
//         }

//         for (IStaff s : staffs) {
//             if (s.getUsername().equalsIgnoreCase(username.trim())) {
//                 if (!s.isActive()) {
//                     setLastMessage("Login failed: staff is inactive.");
//                     return;
//                 }
//                 if (!s.checkPassword(password)) {
//                     setLastMessage("Login failed: wrong password.");
//                     return;
//                 }

//                 loggedInStaff = s;
//                 setLastMessage("Login success. Welcome " + s.getFullName() + "!");
//                 return;
//             }
//         }
//         setLastMessage("Login failed: username not found.");
//     }

//     public void staffLogout() {
//         loggedInStaff = null;
//         setLastMessage("Logged out successfully.");
//     }

//     // =========================
//     // Customer Management
//     // =========================
//    public void createCustomer(String customerId, String fullName, String phone,
//                            String password, double balance) {

//     if (!requirePermission("CREATE_CUSTOMER")) return;

//     for (Customer c : customers) {
//         if (c.getCustomerPersonalId().equalsIgnoreCase(customerId)) {
//             setLastMessage("Customer ID exists.");
//             return;
//         }
//         if (c.getCustomerPhoneNumber().equals(phone)) {
//             setLastMessage("Phone already used.");
//             return;
//         }
//     }

//     // Correct constructor usage:
//     customers.add(new Customer(customerId, fullName, phone, "Unknown", password, balance));
//     setLastMessage("Customer created successfully.");
// }

//     public void printCustomers() {
//         for (Customer c : customers) {
//             System.out.println(c);
//         }
//     }

//     // =========================
//     // Vehicle Management
//     // =========================
//     public void addVehicle(String id, String model, Customer owner) {
//         if (!requirePermission("CREATE_VEHICLE")) return;
//         vehicles.add(new Vehicle(id, model, owner));
//         setLastMessage("Vehicle added successfully.");
//     }

//     public void printVehicles() {
//         for (Vehicle v : vehicles) System.out.println(v);
//     }

//     // =========================
//     // Order Management
//     // =========================
//     public void createOrder(String orderId, Customer customer, Vehicle vehicle, String desc, double cost) {
//         if (!requirePermission("CREATE_ORDER")) return;
//         orders.add(new Order(orderId, customer, vehicle, desc, cost));
//         setLastMessage("Order created successfully.");
//     }

//     public void printOrders() {
//         for (Order o : orders) System.out.println(o);
//     }

// }
import java.util.ArrayList;

public class Garage {
    public static final String CREATE_STAFF = "CREATE_STAFF";
    public static final String CREATE_CUSTOMER = "CREATE_CUSTOMER";
    public static final String CREATE_MENU_ITEM = "CREATE_MENU_ITEM";
    public static final String SET_MENU_ITEM_AVAILABILITY = "SET_MENU_ITEM_AVAILABILITY";
    public static final String CREATE_ORDER = "CREATE_ORDER";
    public static final String VIEW_CUSTOMERS = "VIEW_CUSTOMERS";
    public static final String VIEW_ORDERS = "VIEW_ORDERS";
    public static final String UPDATE_ORDER_STATUS = "UPDATE_ORDER_STATUS";

    private String garageName;
    private String garageAddress;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<IStaff> staffs;
    private ArrayList<Order> orders;
    private ArrayList<Customer> customers;
    private IStaff loggedInStaff;
    private String lastMessage;

    public Garage(String garageName, String garageAddress) {
        setGarageName(garageName);
        setGarageAddress(garageAddress);

        vehicles = new ArrayList<>();
        staffs = new ArrayList<>();
        orders = new ArrayList<>();
        customers = new ArrayList<>();
        loggedInStaff = null;

        seedDefaultAdmin();
        lastMessage = "Garage created. Default staff: admin / 1234";
    }

    // =========================
    // GETTERS / SETTERS
    // =========================
    public String getGarageName() { return garageName; }
    public String getGarageAddress() { return garageAddress; }
    public String getLastMessage() { return lastMessage; }
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
    // STAFF MANAGEMENT
    // =========================
    private void seedDefaultAdmin() {
        ManagerStaff admin = new ManagerStaff("S001", "Admin", "010000000", "admin", "1234", "Manager");
        staffs.add(admin);
    }

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
        return requireStaffLogin(); // later can add action-specific permissions
    }

    public void staffLogin(String username, String password) {
        if (isBlank(username) || password == null) {
            setLastMessage("Login failed: missing username/password.");
            return;
        }
        for (IStaff s : staffs) {
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
    // CUSTOMER MANAGEMENT
    // =========================
    public void createCustomer(String customerId, String fullName, String phone, String gender, String password, double balance) {
        if (!requirePermission("CREATE_CUSTOMER")) return;

        for (Customer c : customers) {
            if (c.getCustomerPersonalId().equalsIgnoreCase(customerId)) {
                setLastMessage("Customer ID exists.");
                return;
            }
            if (c.getCustomerPhoneNumber().equals(phone)) {
                setLastMessage("Phone already used.");
                return;
            }
        }

        customers.add(new Customer(customerId, fullName, phone, gender, password, balance));
        setLastMessage("Customer created successfully.");
    }

    public void printCustomers() {
        System.out.println("\n--- Customers (" + customers.size() + ") ---");
        if (customers.isEmpty()) {
            System.out.println("No customers.");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.println((i + 1) + ") " + c.getCustomerPersonalId() + " | " + c.getCustomerName() + " | " + c.getCustomerPhoneNumber());
        }
    }

    private Customer findCustomerById(String customerId) {
        if (isBlank(customerId)) return null;
        for (Customer c : customers) {
            if (c.getCustomerPersonalId().equalsIgnoreCase(customerId.trim())) return c;
        }
        return null;
    }

    // =========================
    // VEHICLE MANAGEMENT
    // =========================
    public void addVehicle(String numberPlate, String brand, String model) {
        if (!requirePermission("CREATE_VEHICLE")) return;
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumberPlate().equalsIgnoreCase(numberPlate)) {
                setLastMessage("Vehicle number plate already exists.");
                return;
            }
        }
        vehicles.add(new Vehicle(numberPlate, brand, model));
        setLastMessage("Vehicle added successfully.");
    }

    public void printVehicles() {
        System.out.println("\n--- Vehicles (" + vehicles.size() + ") ---");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v.getVehicleNumberPlate() + " | " + v.getBrand() + " | " + v.getModel()
                    + " | Price/Day: $" + v.getPricePerDay() + " | Type: " + v.getVehicleType()
                    + " | Available: " + (v.isAvalible() ? "Yes" : "No"));
        }
    }

    private Vehicle findVehicleByNumberPlate(String numberPlate) {
        if (isBlank(numberPlate)) return null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumberPlate().equalsIgnoreCase(numberPlate.trim())) return v;
        }
        return null;
    }

    // =========================
    // ORDER MANAGEMENT
    // =========================
    public void createOrder(String customerId, String vehicleNumberPlate, int quantity) {
        if (!requirePermission("CREATE_ORDER")) return;

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            setLastMessage("Cannot create order: customer not found.");
            return;
        }

        Vehicle vehicle = findVehicleByNumberPlate(vehicleNumberPlate);
        if (vehicle == null) {
            setLastMessage("Cannot create order: vehicle not found.");
            return;
        }

        if (!vehicle.isAvalible()) {
            setLastMessage("Cannot create order: vehicle is not available.");
            return;
        }

        if (quantity <= 0) {
            setLastMessage("Cannot create order: quantity must be at least 1.");
            return;
        }

        double totalCost = vehicle.getPricePerDay() * quantity;
        if (customer.getBalance() < totalCost) {
            setLastMessage("Cannot create order: insufficient balance.");
            return;
        }

        customer.setBalance(customer.getBalance() - totalCost);

        String orderId = "ORD" + (orders.size() + 1);
        Order newOrder = new Order(orderId, customer, vehicle, quantity, loggedInStaff);
        orders.add(newOrder);

        vehicle.rent(); // mark vehicle as rented

        setLastMessage("Order created successfully: " + orderId);
    }

    public void printOrders() {
        System.out.println("\n--- Orders (" + orders.size() + ") ---");
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        for (Order o : orders) {
            System.out.println("OrderID: " + o.getOrderId() + " | Customer: " + o.getCustomer().getCustomerName()
                    + " | Vehicle: " + o.getItem().getBrand() + " " + o.getItem().getModel()
                    + " | Qty: " + o.getQuantity() + " | Total: $" + o.getTotalPrice()
                    + " | Created by: " + (o.getCreatedBy() != null ? o.getCreatedBy().getFullName() : "Unknown"));
        }
    }
}