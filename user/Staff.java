// package user;
// public class Staff {

//     private String nameStaff;
//     private String staffId;
//     private int staffPersonalId;
//     private String staffPhoneNumber;
//     private boolean active;
//     private String username;
//     private String password;

//     public Staff(String nameStaff, String staffId,
//                  int staffPersonalId, String staffPhoneNumber,
//                 String username, String password) {

//         setNameStaff(nameStaff);
//         setStaffId(staffId);
//         setStaffPersonalId(staffPersonalId);
//         setStaffPhoneNumber(staffPhoneNumber);
//         this.active = true;
//         setUsername(username);
//         setPassword(password);
//     }

  
//     public String getNameStaff() { return nameStaff; }
//     public String getStaffId() { return staffId; }
//     public int getStaffPersonalId() { return staffPersonalId; }
//     public String getStaffPhoneNumber() { return staffPhoneNumber; }
//     public boolean isActive() { return active; }
//     public String getUsername() { return username; }
//     public String getPassword() { return password; }

//   public boolean checkPassword(String input){
//     return password != null && password.equals(input);
//   }

// public void setUsername(String username) {
//     if (username == null || username.trim().isEmpty()) {
//         this.username = "staff_" + this.staffId;
//     } else {
//         this.username = username.trim();
//     }
// }

// public void setPassword(String password) {
//     if (password == null || password.length() < 4) {
//         this.password = "0000";
//     } else {
//         this.password = password;
//     }
// }


//     public void setNameStaff(String nameStaff) {
//         if (nameStaff == null || nameStaff.trim().isEmpty()) {
//             this.nameStaff = "No Name";
//         } else {
//             this.nameStaff = nameStaff.trim();
//         }
//     }

//     public void setStaffId(String staffId) {
//         if (staffId == null || staffId.trim().isEmpty()) {
//             this.staffId = "UNKNOWN";
//         } else {
//             this.staffId = staffId.trim();
//         }
//     }

//     public void setStaffPersonalId(int staffPersonalId) {
//         if (staffPersonalId <= 0) {
//             this.staffPersonalId = 0;
//         } else {
//             this.staffPersonalId = staffPersonalId;
//         }
//     }

//     public void setStaffPhoneNumber(String staffPhoneNumber) {
//         if (staffPhoneNumber == null || staffPhoneNumber.length() < 8) {
//             this.staffPhoneNumber = "00000000";
//         } else {
//             this.staffPhoneNumber = staffPhoneNumber;
//         }
//     }


//     @Override
//     public String toString() {
//         return "Staff [nameStaff=" + nameStaff + ", staffId=" + staffId + ", staffPersonalId=" + staffPersonalId
//                 + ", staffPhoneNumber=" + staffPhoneNumber + ", active=" + active + ", username=" + username
//                 + ", password=" + password + "]";
//     }




//     @Override
//     public boolean equals(Object obj) {
//        Staff s1 = (Staff) obj; 
//        if(s1.staffPhoneNumber.equals(staffPhoneNumber)){
//         return true;
//        }
//        return false;
//     }
    
// }
package user;

public class Staff implements IStaff {

    // ====== Fields ======
    private String staffId;
    private String fullName;
    private String phone;
    private String username;
    private String password;
    private boolean active;

    // ====== Constructor ======
    public Staff(String staffId, String fullName, String phone,
                 String username, String password) {
        setStaffId(staffId);
        setFullName(fullName);
        setPhone(phone);
        setUsername(username);
        setPassword(password);
        this.active = true;
    }

    // ====== Default can() — plain Staff has no permissions ======
    @Override
    public boolean can(String action) {
        return false;
    }

    // ====== Getters ======
    public String getStaffId()  { return staffId; }
    public String getFullName() { return fullName; }
    public String getPhone()    { return phone; }
    public String getUsername() { return username; }
    public boolean isActive()   { return active; }

    protected String getPassword() { return password; }

    // ====== Business Logic ======
    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }

    // ====== Setters with Validation ======
    public void setStaffId(String staffId) {
        if (isBlank(staffId)) this.staffId = "UNKNOWN";
        else this.staffId = staffId.trim();
    }

    public void setFullName(String fullName) {
        if (isBlank(fullName)) this.fullName = "No Name";
        else this.fullName = fullName.trim();
    }

    public void setPhone(String phone) {
        String p = (phone == null) ? "" : phone.trim();
        if (!isDigits(p) || p.length() < 8 || p.length() > 15)
            this.phone = "00000000";
        else
            this.phone = p;
    }

    public void setUsername(String username) {
        if (isBlank(username)) this.username = "staff_" + this.staffId;
        else this.username = username.trim();
    }

    public void setPassword(String password) {
        String pw = (password == null) ? "" : password;
        if (pw.length() < 4) this.password = "0000";
        else this.password = pw;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // ====== Helpers ======
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
        return "S{" +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", active=" + active +
                '}';
    }

    // ====== equals (by phone, matching teacher) ======
    @Override
    public boolean equals(Object obj) {
        Staff s1 = (Staff) obj;
        if (s1.phone.equals(phone)) {
            return true;
        }
        return false;
    }
}