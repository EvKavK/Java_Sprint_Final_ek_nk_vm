package org.keyin.memberships;
import java.util.Date;

//*\
// This is class file that represents a membership
//
public class Membership {
    private int id;
    private int userID;
    private String type;
    private Date startDate;
    private Date endDate;
    private String status;
    private double price;

    // constructor
    public Membership(int id, int userID, String type, Date startDate, Date endDate, String status, double price) {
        this.id = id;
        this.userID = userID;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
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
    
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }   

    public String getStatus() {
        return status;
    }   

    public double getPrice() {
        return price;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", userID=" + userID +
                ", type='" + type + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                '}';
    }
}