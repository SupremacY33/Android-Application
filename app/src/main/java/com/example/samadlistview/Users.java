package com.example.samadlistview;

public class Users {
    String fullname, email, password, conform_password, address, imageid;

    public Users() {
    }

    public Users(String fullname, String email, String password, String conform_password, String address, String imageid) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.conform_password = conform_password;
        this.address = address;
        this.imageid = imageid;
    }


    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConform_password() {
        return conform_password;
    }

    public void setConform_password(String conform_password) {
        this.conform_password = conform_password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
