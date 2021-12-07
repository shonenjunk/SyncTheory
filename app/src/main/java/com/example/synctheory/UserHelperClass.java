package com.example.synctheory;

public class UserHelperClass {

    String email, phoneNumber, password;
    boolean isElevatedUser;

    public UserHelperClass(){
    }


    public UserHelperClass(String email, String phoneNumber, String password, boolean isElevatedUser) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isElevatedUser = isElevatedUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isElevatedUser() {
        return isElevatedUser;
    }

    public void setElevatedUser(boolean elevatedUser) {
        isElevatedUser = elevatedUser;
    }
}
