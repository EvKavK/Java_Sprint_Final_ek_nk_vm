package org.keyin.memberships;

//*\
// This is class file that represents a membership
//
public class Membership {
    private int id;
    private int userID;
    private String type;
    private String startDate;
    private String endDate;
    private String status;
    private double price;
    private int durMonths;

    // constructor
    public Membership(int id, int userID, String type, String startDate, String endDate, String status, double price, int durMonths) {
        this.id = id;
        this.userID = userID;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.durMonths = durMonths;
    }   

    
    // getters
    public int getId() {
        return id;
    }
    
    public int getUserID() {
        return userID;
    }

    public String getType() {
        return type;
    }
    
    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }   

    public String getStatus() {
        return status;
    }   

    public double getPrice() {
        return price;
    }

    public int getDurMonths() {
        return durMonths;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(int durMonths) {
        this.durMonths = durMonths;
    }
    
    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", userID=" + userID +
                ", type='" + type + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", duration='" + durMonths + '\'' +
                '}';
    }
}