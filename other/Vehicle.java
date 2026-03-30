package other;

 public class Vehicle {

    private String vehicleNumberPlate;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;
    private String vehicleType;
    private static int totalVehicle = 0;

 public Vehicle(String vehicleNumberPlate, String brand, String model, double pricePerDay, String vehicleType) {
       setVehicleNumberPlate(vehicleNumberPlate);
       setBrand(brand);
       SetModel(model);
       setPricePerDay(pricePerDay);
       setVehicleType(vehicleType);
    }
public Vehicle(String vehicleNumberPlate, String brand, String model){
    this.vehicleNumberPlate = vehicleNumberPlate;
    this.brand = brand;
    this.model = model;
    this.pricePerDay = 25.00;
    this.isAvailable = true;
    this.vehicleType = "general";
}
public String getVehicleNumberPlate(){
    return vehicleNumberPlate;
}
public static int getTotalVehicle(){
    return totalVehicle;
}
public String getBrand(){
    return brand;
}
public void setVehicleNumberPlate(String vehicleNumberPlate){
    if(isBlank(vehicleNumberPlate)) this.vehicleNumberPlate = "UNKNOW";
    else this.vehicleNumberPlate = vehicleNumberPlate.trim();
}
public void setBrand(String Brand){
    if(isBlank(Brand)) this.brand = "UNKNOW";
    else this.brand = brand.trim();
}
public void SetModel(String model){
    if(isBlank(model)) this.model = "UNKNOW";
    else this.model = model.trim();
}
public void setVehicleType(String vehicleType){
    if(isBlank(vehicleType)) this.vehicleType = "UNKNOW";
    else this.vehicleType = vehicleType.trim();
}
public void setPricePerDay(double price){
    if(price > 0){
        this.pricePerDay = price;
    }else{
        System.out.println("Error");
    }
}  
public double calulatePrice(int rentalDay) {
      return pricePerDay * rentalDay;
}
public boolean isAvailable() {
        return isAvailable;
}
public double getPricePerDay(){
    return pricePerDay;
}
public void rent() {
    isAvailable = false;
}
public void returnCar() {
    isAvailable = true;
}
public String getModel(){
    return model;
}
public String getVehicleType(){
    return vehicleType;
}
private boolean isBlank(String s){
    return s == null || s.trim().isEmpty();
}

}
