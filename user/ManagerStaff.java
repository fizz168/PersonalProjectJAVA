// package user;
// public class ManagerStaff extends Staff implements IStaff {

//     // ====== Fields ======
//     private String staffId;
//     private String fullName;
//     private String phone;
//     private String username;
//     private String password;
//     private String position;
//     private boolean active;
//     private float salary;
    


//     // ====== Constructor ======
//   public ManagerStaff(Staff s, float salary) {
//    super(s.getNameStaff(), s.getStaffId());
//   }

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

//     // ====== toString ======
//     @Override
//     public String toString() {
//         return "ManagerStaff{" +
//                 "staffId='" + staffId + '\'' +
//                 ", fullName='" + fullName + '\'' +
//                 ", phone='" + phone + '\'' +
//                 ", username='" + username + '\'' +
//                 ", position='" + position + '\'' +
//                 ", active=" + active +
//                 '}';
//     }
// }
package user;

public class ManagerStaff extends CashierStaff  {

    private float benefit;

    // ====== Constructor — takes a Staff object + salary (teacher's pattern) ======
    public ManagerStaff(Staff s, float salary) {
        super(s, salary); // call CashierStaff constructor to set Staff fields and salary
        System.out.println("ManagerStaff created: " + this);
    }

    // ====== Manager can do everything ======
    @Override
    public boolean can(String action) {
        return true;
    }

    // ====== Salary Getter/Setter ======
    public float getBenefit() {
        return benefit;
    }

    public void setSalary(float benefit) {
         if (benefit < 1000) {
            System.out.println("error: need more salary");
        } else {
            this.benefit = benefit;
        }
       
    }

    // ====== toString ======
    @Override
    public String toString() {
        return super.toString() + "ManagerStaff [\"Position: Manager benefit=" + benefit + "]";
    }

    // ====== equals ======
    @Override
    public boolean equals(Object obj) {
        ManagerStaff other = (ManagerStaff) obj;
        if (!super.equals(obj)) {
            return false;
        } else {
            if (Float.floatToIntBits(benefit) != Float.floatToIntBits(other.benefit)) {
                return false;
            }
        }
        return true;
    }
}