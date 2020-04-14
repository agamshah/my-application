package com.example.vigourmortaltest;

public class DoctorUserHelperClass {
    String name,email,phone,password, displaydate, depart, strGender;
    public DoctorUserHelperClass(){

    }

    public DoctorUserHelperClass(String name, String email, String phone, String password, String displaydate, String depart, String strGender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.displaydate = displaydate;
        this.depart = depart;
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

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }
}
