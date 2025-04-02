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

    public Membership(int id, int userID, String type, String startDate, String endDate, String status, double price) {
        this.id = id;
        this.userID = userID;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
    }   
}