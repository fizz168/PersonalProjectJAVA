 public class Customer {
    private String customerPersonalId;
    private String customerName;
    private String customerPhoneNumber;
    private String gender;
   
    private String password;
    private double balance;
    private boolean isMember;

 public Customer(String customerPersonalId, String customerName, String customerPhonenumber, String gender,  String password, double balance) {
        setCustomerPersonalId(customerPersonalId);
        setCustomerName(customerName);
        setCustomerPhoneNumber(customerPhonenumber);
        setGender(gender);
        setPassword(password);
        setBalance(balance);
        this.isMember = true;
    }
public String getCustomerPersonalId(){
    return customerPersonalId;
}
public String getCustomerName(){
    return customerName;
}
public String getCustomerPhoneNumber(){
    return customerPhoneNumber;
}
public String getGender(){
    return gender;
}
public boolean isMember(){
    return isMember;
}
public double getBalance(){
    return balance;

}
public boolean checkPassword(String input){
    return password != null && password.equals(input);
}
public void setCustomerPersonalId(String customerPersonalId){
    if(isBlank(customerPersonalId)) this.customerPersonalId = "UNKNOW";
    else this.customerPersonalId = customerPersonalId.trim();
}
public void setCustomerName(String customerName){
    if(isBlank(customerName)) this.customerName = "unknow";
    else this.customerName = customerName.trim();
}
public void setCustomerPhoneNumber(String customerPhoneNumber){
  String p = (customerPhoneNumber == null) ? "" : customerPhoneNumber.trim();
        if (!isDigits(p) || p.length() < 8 || p.length() > 15) this.customerPhoneNumber = "00000000";
        else this.customerPhoneNumber = p;
}
public void setGender(String gender){
    if(isBlank(gender)) this.gender = "unknow";
    else this.gender = gender.trim();
}
public void setPassword(String password){
   String pw = (password == null) ? "" : password;
   if(pw.length() < 4) this.password = "0000";
   else this.password = pw;
}
public void setBalance(double balance){
    if(balance < 0) this.balance = 0;
    else this.balance = balance;

}

private boolean isBlank(String s){
    return s == null || s.trim().isEmpty();
}
 private boolean isDigits(String s) {
        if (isBlank(s)) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

}
