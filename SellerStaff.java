// public interface SellerStaff implements IStaff{
//     private String staffId;
//     private String fullName;
//     private String phone;
//     private String username;
//     private String password;
//     private String position;
//     private boolean active;

//     // ====== Constructor ======
//     public SellerStaff(String staffId, String fullName, String phone,
//                         String username, String password, String position) {

//         setStaffId(staffId);
//         setFullName(fullName);
//         setPhone(phone);
//         setUsername(username);
//         setPassword(password);
//         setPosition(position);

//         this.active = true;
//     }

//     // ====== Interface Implementation ======
//     @Override
//     public boolean can(String action) {
//         // Manager can do everything
//         return true;
//     }

//     // ====== Getters ======
//     public String getStaffId() { return staffId; }
//     public String getFullName() { return fullName; }
//     public String getPhone() { return phone; }
//     public String getUsername() { return username; }
//     public String getPosition() { return position; }
//     public boolean isActive() { return active; }

//     // ====== Business Logic ======
//     public boolean checkPassword(String input) {
//         return password != null && password.equals(input);
//     }

//     // ====== Setters with Validation ======
//     public void setStaffId(String staffId) {
//         this.staffId = isBlank(staffId) ? "UNKNOWN" : staffId.trim();
//     }

//     public void setFullName(String fullName) {
//         this.fullName = isBlank(fullName) ? "No Name" : fullName.trim();
//     }

//     public void setPhone(String phone) {
//         String p = (phone == null) ? "" : phone.trim();
//         if (!isDigits(p) || p.length() < 8 || p.length() > 15)
//             this.phone = "00000000";
//         else
//             this.phone = p;
//     }

//     public void setUsername(String username) {
//         this.username = isBlank(username) ? "staff_" + this.staffId
//                                           : username.trim();
//     }

//     public void setPassword(String password) {
//         String pw = (password == null) ? "" : password;
//         this.password = (pw.length() < 4) ? "0000" : pw;
//     }

//     public void setPosition(String position) {
//         this.position = isBlank(position) ? "Manager" : position.trim();
//     }

//     public void setActive(boolean active) {
//         this.active = active;
//     }

//     // ====== Helper Methods ======
//     private boolean isBlank(String s) {
//         return s == null || s.trim().isEmpty();
//     }

//     private boolean isDigits(String s) {
//         if (isBlank(s)) return false;
//         for (int i = 0; i < s.length(); i++) {
//             char c = s.charAt(i);
//             if (c < '0' || c > '9') return false;
//         }
//         return true;
//     }

// }
public class SellerStaff implements IStaff {

    // ====== Fields ======
    private String staffId;
    private String fullName;
    private String phone;
    private String username;
    private String password;
    private String position;
    private boolean active;

    // ====== Constructor ======
    public SellerStaff(String staffId, String fullName, String phone,
                       String username, String password, String position) {

        setStaffId(staffId);
        setFullName(fullName);
        setPhone(phone);
        setUsername(username);
        setPassword(password);
        setPosition(position);

        this.active = true;
    }

    // ====== Permission Logic ======
    @Override
    public boolean can(String action) {
        if(action.equals(Garage.CREATE_MENU_ITEM) || action.equals(Garage.VIEW_ORDERS) || action.equals(Garage.UPDATE_ORDER_STATUS))
        {
            return true;
        }
        // TODO Auto-generated method stub
        return false;
     
        
    }
    // ====== Getters ======
    public String getStaffId() { return staffId; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getUsername() { return username; }
    public String getPosition() { return position; }
    public boolean isActive() { return active; }

    // ====== Business Logic ======
    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }

    // ====== Setters with Validation ======
    public void setStaffId(String staffId) {
        this.staffId = isBlank(staffId) ? "UNKNOWN" : staffId.trim();
    }

    public void setFullName(String fullName) {
        this.fullName = isBlank(fullName) ? "No Name" : fullName.trim();
    }

    public void setPhone(String phone) {
        String p = (phone == null) ? "" : phone.trim();
        if (!isDigits(p) || p.length() < 8 || p.length() > 15)
            this.phone = "00000000";
        else
            this.phone = p;
    }

    public void setUsername(String username) {
        this.username = isBlank(username) ? "staff_" + this.staffId
                                          : username.trim();
    }

    public void setPassword(String password) {
        String pw = (password == null) ? "" : password;
        this.password = (pw.length() < 4) ? "0000" : pw;
    }

    public void setPosition(String position) {
        this.position = isBlank(position) ? "Seller" : position.trim();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // ====== Helper Methods ======
    private boolean isBlank(String s) {
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

    // ====== toString ======
    @Override
    public String toString() {
        return "SellerStaff{" +
                "staffId='" + staffId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", position='" + position + '\'' +
                ", active=" + active +
                '}';
    }
}