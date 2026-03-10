
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