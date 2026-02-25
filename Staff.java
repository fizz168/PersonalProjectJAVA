public class Staff {

    private String nameStaff;
    private String staffId;
    private int staffPersonalId;
    private String staffPhoneNumber;
    private boolean active;
    private String username;
    private String password;

    public Staff(String nameStaff, String staffId,
                 int staffPersonalId, String staffPhoneNumber,
                String username, String password) {

        setNameStaff(nameStaff);
        setStaffId(staffId);
        setStaffPersonalId(staffPersonalId);
        setStaffPhoneNumber(staffPhoneNumber);
        this.active = true;
        setUsername(username);
        setPassword(password);
    }

  
    public String getNameStaff() { return nameStaff; }
    public String getStaffId() { return staffId; }
    public int getStaffPersonalId() { return staffPersonalId; }
    public String getStaffPhoneNumber() { return staffPhoneNumber; }
    public boolean isActive() { return active; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

  public boolean checkPassword(String input){
    return password != null && password.equals(input);
  }

public void setUsername(String username) {
    if (username == null || username.trim().isEmpty()) {
        this.username = "staff_" + this.staffId;
    } else {
        this.username = username.trim();
    }
}

public void setPassword(String password) {
    if (password == null || password.length() < 4) {
        this.password = "0000";
    } else {
        this.password = password;
    }
}


    public void setNameStaff(String nameStaff) {
        if (nameStaff == null || nameStaff.trim().isEmpty()) {
            this.nameStaff = "No Name";
        } else {
            this.nameStaff = nameStaff.trim();
        }
    }

    public void setStaffId(String staffId) {
        if (staffId == null || staffId.trim().isEmpty()) {
            this.staffId = "UNKNOWN";
        } else {
            this.staffId = staffId.trim();
        }
    }

    public void setStaffPersonalId(int staffPersonalId) {
        if (staffPersonalId <= 0) {
            this.staffPersonalId = 0;
        } else {
            this.staffPersonalId = staffPersonalId;
        }
    }

    public void setStaffPhoneNumber(String staffPhoneNumber) {
        if (staffPhoneNumber == null || staffPhoneNumber.length() < 8) {
            this.staffPhoneNumber = "00000000";
        } else {
            this.staffPhoneNumber = staffPhoneNumber;
        }
    }
}