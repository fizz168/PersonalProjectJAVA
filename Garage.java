import java.util.ArrayList;

public class Garage {

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

    private void seedDefaultAdmin() {
        // Using your original Staff interface
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
        return requireStaffLogin();
        // Can later add action-specific permissions
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
    // Customer Management
    // =========================
   public void createCustomer(String customerId, String fullName, String phone,
                           String password, double balance) {

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

    // Correct constructor usage:
    customers.add(new Customer(customerId, fullName, phone, "Unknown", password, balance));
    setLastMessage("Customer created successfully.");
}

    public void printCustomers() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    // =========================
    // Vehicle Management
    // =========================
    public void addVehicle(String id, String model, Customer owner) {
        if (!requirePermission("CREATE_VEHICLE")) return;
        vehicles.add(new Vehicle(id, model, owner));
        setLastMessage("Vehicle added successfully.");
    }

    public void printVehicles() {
        for (Vehicle v : vehicles) System.out.println(v);
    }

    // =========================
    // Order Management
    // =========================
    public void createOrder(String orderId, Customer customer, Vehicle vehicle, String desc, double cost) {
        if (!requirePermission("CREATE_ORDER")) return;
        orders.add(new Order(orderId, customer, vehicle, desc, cost));
        setLastMessage("Order created successfully.");
    }

    public void printOrders() {
        for (Order o : orders) System.out.println(o);
    }

}