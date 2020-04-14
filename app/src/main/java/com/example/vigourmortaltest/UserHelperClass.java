package com.example.vigourmortaltest;

public class UserHelperClass {

    String name,email,phone,password, displaydate, blood, strGender;

    public UserHelperClass(){

    }

    public UserHelperClass(String name, String email, String phone, String password, String displaydate, String blood, String strGender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.displaydate = displaydate;
        this.blood = blood;
        this.strGender = strGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplaydate() {
        return displaydate;
    }

    public void setDisplaydate(String displaydate) {
        this.displaydate = displaydate;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }
}
