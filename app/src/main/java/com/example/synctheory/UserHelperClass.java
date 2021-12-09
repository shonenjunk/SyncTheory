package com.example.synctheory;

//helper class that helps encode newly added users to the proper JSON format required by Firebase
public class UserHelperClass {

    //User data
    String email, phoneNumber, password;
    //whether User is Teacher (true) or not (false)
    boolean isElevatedUser;

    //empty constructor, required
    public UserHelperClass(){
    }

    //constructor called to create the user object based on the user's entered  sign up information
    public UserHelperClass(String email, String phoneNumber, String password, boolean isElevatedUser) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isElevatedUser = isElevatedUser;
    }


    //general getters and setters for each of the Users various attributes (unused)
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
