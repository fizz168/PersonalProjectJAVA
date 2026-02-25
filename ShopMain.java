// // public class Main {
// //     public static void main(String[] args) {
// //         // Setup: Array of Objects + Counter [cite: 236, 237, 238, 239]
// //         CarPoolingSystem system = new CarPoolingSystem("CADT", 10, 10);
// //         Vehicle v2 = new Vehicle("hellp", "nx21", 3.5, "1923", "nia"); //normal constructor
// //         Vehicle v1 = new Vehicle("x21", "TOY", "nigga"); // constructor overloading 
// //         system.addVehicle(v1);
// //         system.addVehicle(v2);

// //         Customer c1 = new Customer("C01", "Sokha", "0962618205");

// //         // F1) Primitive Copy Proof [cite: 260, 261]
// //         // Copy a primitive, modify the copy, original remains unchanged
// //         System.out.println("F1) Primitive copy proof");
// //         double originalPrice = v1.pricePerDay; 
// //         double copiedPrice = originalPrice; 
// //         copiedPrice = 100.0;
// //         System.out.println("Original Price field: " + v1.pricePerDay); // Stays 50.0
// //         System.out.println("Copied Price variable: " + copiedPrice);   // Becomes 100.0

// //         // F2) Reference Copy Proof [cite: 262, 263]
// //         // Two variables reference same object; change is visible everywhere
// //         System.out.println("\nF2) Reference copy proof (same object)");
// //         Vehicle vRef = v1; 
// //         vRef.brand = "Lexus"; // Modifying through the reference
// //         System.out.println("Original v1 brand: " + v1.brand); // Reflects "Lexus"
// //         System.out.println("Reference vRef brand: " + vRef.brand);

// //         // F3) Array Stores References Proof [cite: 264, 265]
// //         // Objects inside arrays reflect later modifications
// //         System.out.println("\nF3) Array stores references proof");
   
// //         // F1) Primitive Copy Proof [cite: 260, 261]
// //         // Copy a primitive, modify the copy, original remains unchanged     v1.pricePerDay = 75.0; 
// //         System.out.println("Price in system array: " + system.inventory[0].pricePerDay); // Reflects 75.0

// //         // F4) Snapshot Behavior [cite: 266, 267]
// //         // Stored snapshot values do not change after the original object changes
// //         System.out.println("\nF4) Snapshot proof (Requirement)");
// //         Rental r1 = new Rental(v1, c1, 3); // Snapshot taken at current price (75.0)
// //         v1.pricePerDay = 999.0; // Change original object price
// //         System.out.println("Vehicle price changed to: " + v1.pricePerDay);
// //         System.out.println("Rental record price (Snapshot): " + r1.priceAtBooking); // Stays 75.0

        

// //         // Null Safety Demo [cite: 249, 250, 251, 253]
// //         System.out.println("\n--- Null Safety Check ---");
// //         Vehicle found = system.findVehicle("NON-EXISTENT");
// //         if (found == null) {
// //             System.out.println("Vehicle not found safely (Returned null).");
// //         }
// //     }
// // }

// // public class Main {
// //     static void main(String[] args) {
// //         Garage g = new Garage("CADT Auto", 10);

// //         Vehicle v = new Vehicle("M12", "Pagani", "Huayra Roadster BC", 500.00, "Car");
// //         g.addVehicle(v);
// //         Customer c = new Customer("C001", "Nhean Omra", "098733402", "male");
// //         Staff s = new Staff("Lim Vinchay", "S99", 10101, "098765432");
// //         Rental r = new Rental(v, c, 3, 5001);
// //             System.out.println("--- Rental Receipt ---");
// //             System.out.println("Customer: " + c.getCustomerName());
// //             System.out.println("Phone number : " + c.getCustomerPhoneNumber());
// //             System.out.println("Vehicle: " + v.getVehicleNumberPlate());
// //             System.out.println("Total Days : " + r.getDays());
// //             System.out.println("Status: Vehicle is now rented.");
// //             System.out.println("Staff info : " + s.getNameStaff());
// //             System.out.println("Price per day :" + v.getPricePerDay());
// //             System.out.println("brand: " + v.getBrand());
// //             System.out.println("model: " + v.getModel());
// //     System.out.println("-----------------------------");
// //         double originalPrice = v.getPricePerDay();
// //         double copyPrice = originalPrice;
// //         copyPrice = 6.7;
// //         System.out.println("Original price: " + originalPrice);
// //         System.out.println("Prize copy: " + copyPrice);

// //         Vehicle vR = v;
// //         vR.setPricePerDay(50.5);
// //         System.out.println("Original price: " + v.getPricePerDay());
// //         System.out.println("Copy price: " + vR.getPricePerDay());

// //         System.out.println("Price in system: " + g.getVehicleAt(0).getPricePerDay());

// //         v.setPricePerDay(100.5);
// //         System.out.println("Vehicle prize Change to: " + v.getPricePerDay());
// //         System.out.println("Vehicle original prize: " + r.getPriceAtBooking());

// //         Vehicle found = g.findVehicle("Non Existent");
// //         if(found == null){
// //             System.out.println("Vehicle is not found");
// //         } 
// //     }
// // }
// import java.util.Scanner;
// public class ShopMain {

//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);
//         CoffeeShop shop = new CoffeeShop("CADT Cafe", "Phnom Penh");

//         int choice;

//         do {

//             if (!shop.isStaffLoggedIn()) {

//                 printMainMenu();

//                 System.out.print("Choose: ");
//                 choice = sc.nextInt();
//                 sc.nextLine();

//                 switch (choice) {

//                     case 1: {
//                         System.out.print("Username: ");
//                         String username = sc.nextLine();

//                         System.out.print("Password: ");
//                         String password = sc.nextLine();

//                         shop.staffLogin(username, password);
//                         System.out.println(shop.getLastMessage());
//                         break;
//                     }

//                     case 2: {
//                         shop.printMenuItems();
//                         break;
//                     }

//                     case 0: {
//                         System.out.println("Goodbye!");
//                         break;
//                     }

//                     default:
//                         System.out.println("Invalid choice.");
//                 }

//             } else {

//                 printStaffMenu(shop);

//                 System.out.print("Choose: ");
//                 choice = sc.nextInt();
//                 sc.nextLine();

//                 switch (choice) {

//                     case 1: { // Create Staff
//                         System.out.print("Staff ID: ");
//                         String staffId = sc.nextLine();

//                         System.out.print("Full Name: ");
//                         String fullName = sc.nextLine();

//                         System.out.print("Phone: ");
//                         String phone = sc.nextLine();

//                         System.out.print("Username: ");
//                         String username = sc.nextLine();

//                         System.out.print("Password: ");
//                         String password = sc.nextLine();

//                         System.out.print("Position: ");
//                         String position = sc.nextLine();

//                         shop.createStaff(staffId, fullName, phone, username, password, position);
//                         System.out.println(shop.getLastMessage());
//                         break;
//                     }

//                     case 2: { // Create Customer
//                         System.out.print("Customer ID: ");
//                         String customerId = sc.nextLine();

//                         System.out.print("Full Name: ");
//                         String fullName = sc.nextLine();

//                         System.out.print("Phone: ");
//                         String phone = sc.nextLine();

//                         System.out.print("Password: ");
//                         String password = sc.nextLine();

//                         System.out.print("Initial Balance: ");
//                         double balance = sc.nextDouble();
//                         sc.nextLine();

//                         shop.createCustomer(customerId, fullName, phone, password, balance);
//                         System.out.println(shop.getLastMessage());
//                         break;
//                     }

//                     case 3: { // Create Menu Item
//                         System.out.print("Item ID: ");
//                         String itemId = sc.nextLine();

//                         System.out.print("Item Name: ");
//                         String name = sc.nextLine();

//                         System.out.print("Category: ");
//                         String category = sc.nextLine();

//                         System.out.print("Size (S/M/L/None): ");
//                         String size = sc.nextLine();

//                         System.out.print("Price: ");
//                         double price = sc.nextDouble();
//                         sc.nextLine();

//                         System.out.print("Available? (1=Yes, 0=No): ");
//                         int a = sc.nextInt();
//                         sc.nextLine();

//                         boolean available = (a == 1);

//                         shop.createMenuItem(itemId, name, category, size, price, available);
//                         System.out.println(shop.getLastMessage());
//                         break;
//                     }

//                     case 4: { // Set Menu Item Availability
//                         System.out.print("Item ID: ");
//                         String itemId = sc.nextLine();

//                         System.out.print("Available? (1=Yes, 0=No): ");
//                         int a = sc.nextInt();
//                         sc.nextLine();

//                         boolean available = (a == 1);

//                         shop.setMenuItemAvailability(itemId, available);
//                         System.out.println(shop.getLastMessage());
//                         break;
//                     }

//                     case 5: { // Create Order
//                         System.out.print("Customer phone: ");
//                         String phone = sc.nextLine();

//                         System.out.print("Menu item ID: ");
//                         String itemId = sc.nextLine();

//                         System.out.print("Quantity: ");
//                         int qty = sc.nextInt();
//                         sc.nextLine();

//                         shop.createOrder(phone, itemId, qty);
//                         System.out.println(shop.getLastMessage());
//                         break;
//                     }

//                     case 6: { // List Customers
//                         shop.printCustomers();
//                         break;
//                     }

//                     case 7: { // List Menu Items
//                         shop.printMenuItems();
//                         break;
//                     }

//                     case 8: { // List Orders
//                         shop.printOrders();
//                         break;
//                     }

//                     case 9: { // Logout
//                         shop.staffLogout();
//                         System.out.println(shop.getLastMessage());
//                         break;
//                     }

//                     case 0: {
//                         System.out.println("Goodbye!");
//                         break;
//                     }

//                     default:
//                         System.out.println("Invalid choice.");
//                 }
//             }

//         } while (choice != 0);

//         sc.close();
//     }

//     // ===== Menu printing in Main (easy to customize later) =====
//     private static void printMainMenu() {
//         System.out.println("\n=== MAIN MENU (Not Logged In) ===");
//         System.out.println("1) Staff Login");
//         System.out.println("2) View Menu Items");
//         System.out.println("0) Exit");
//     }

//     private static void printStaffMenu(CoffeeShop shop) {
//         System.out.println("\n=== STAFF MENU (Logged In) ===");
//         System.out.println("Logged in staff: " + shop.getLoggedInStaff());
//         System.out.println("1) Create Staff");
//         System.out.println("2) Create Customer");
//         System.out.println("3) Create Menu Item");
//         System.out.println("4) Set Menu Item Availability");
//         System.out.println("5) Create Order");
//         System.out.println("6) List Customers");
//         System.out.println("7) List Menu Items");
//         System.out.println("8) List Orders");
//         System.out.println("9) Logout");
//         System.out.println("0) Exit");
//     }
// }