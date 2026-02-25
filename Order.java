// class Rental {
//     Vehicle vehicle;   // reference type [cite: 234]
//     Customer customer; // reference type [cite: 234]
//     int days;          // primitive type [cite: 233]
//     double priceAtBooking; // SNAPSHOT: primitive copy [cite: 255]

//     public Rental(Vehicle vehicle, Customer customer, int days) {
//         this.vehicle = vehicle;
//         this.customer = customer;
//         this.days = days;
//         // COPY the value here to create the snapshot [cite: 256, 257]
//         this.priceAtBooking = vehicle.pricePerDay; 
//     }
// }
//  public class Rental {

//     private Vehicle vehicle;
//     private Customer customer;
//     private int days;
//     private int recordId;
//     private double priceAtBooking;

// public Rental(Vehicle vehicle, Customer customer, int days, int recordId) {
//         this.vehicle = vehicle;
//         this.customer = customer;
//         this.days = days;
//         this.recordId = recordId;
//         this.priceAtBooking = vehicle.getPricePerDay();
//     }
// public Vehicle getVehicle(){
//     return vehicle;
// }
// public Customer getCustomer(){
//     return customer;
// }
// public int getDays(){
//     return days;
// }
// public int getRecordId(){
//     return recordId;
// }
// public double getPriceAtBooking(){
//     return priceAtBooking;
// }
// }

public class Order {

    private String orderId;
    private Customer customer;
    private Vehicle item;
    private int quantity;
    private double totalPrice;

    private IStaff createdBy;     // staff who created the order
    private boolean paid;        // for now: true when order is created (because we deduct balance)

    public Order(String orderId, Customer customer, Vehicle item, int quantity, IStaff createdBy) {
        setOrderId(orderId);
        setCustomer(customer);
        setItem(item);
        setQuantity(quantity);
        setCreatedBy(createdBy);

        calculateTotal();
        this.paid = true;
    }

    // ===== Getters =====
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Vehicle getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public IStaff getCreatedBy() { return createdBy; }
    public boolean isPaid() { return paid; }

    // ===== Setters =====
    public void setOrderId(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) this.orderId = "UNKNOWN";
        else this.orderId = orderId.trim();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItem(Vehicle item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) this.quantity = 1;
        else this.quantity = quantity;
    }

    public void setCreatedBy(IStaff createdBy) {
        this.createdBy = createdBy;
    }

    // ===== Methods =====
    public void calculateTotal() {
        if (item == null) {
            totalPrice = 0;
        } else {
            totalPrice = item.getPricePerDay() * quantity;
        }
    }

   
}