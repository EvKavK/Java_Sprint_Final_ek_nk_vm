package org.keyin.user;

//*
// This is the parent class for all users, There are 3 types of users: Trainer, Member, and Admin
//
// *//
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String role;


    // constructor
    public User(int id,String username, String password, String email, String phone, String address, String city, String province, String postalCode, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.role = role;
    }


    // getters
    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }   
    
    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getRole() {
        return role;
    }
    
    
    // setters
    public void setID(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }   

    public void setProvince(String province) {
        this.province = province;
    }   
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
