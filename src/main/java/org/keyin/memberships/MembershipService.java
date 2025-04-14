package org.keyin.memberships;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

// Service class for membership handle all the business logic
// and only uses the DAO to interact with the database it does not have methods to do so
// you can inject in your dao to use in your service. An example will be in the code
public class MembershipService {

    // When you inject in the DAO you have access to all methods in it
    private final MembershipDAO memDAO;

    public MembershipService(MembershipDAO memDAO) {
        this.memDAO = memDAO;
    }

    // purchase membership
    public Membership buyMembership(int userID, String type, double price, int durMonths) throws SQLException {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(durMonths);
        
        Membership mem = new Membership(
            0, // set by db
            userID,
            type,
            startDate.toString(),
            endDate.toString(),
            "active",
            price,
            durMonths
        );
        
        return memDAO.createMembership(mem);
    }
    

    // renew membership
    public Membership renewMem(Membership mem, int addMonths) throws SQLException {
        LocalDate currEndDate = LocalDate.parse(mem.getEndDate());
        LocalDate newEndDate = currEndDate.plusMonths(addMonths);
        
        mem.setEndDate(newEndDate.toString());
        mem.setStatus("active");
        
        return memDAO.updateMembership(mem);
    }
    


    // view user expenses
    public double calcUserExpenses(int userID) throws SQLException {
        List<Membership> userMemberships = memDAO.getMembershipsByUserID(userID);
        double totalExpenses = 0.0;
        for (Membership membership : userMemberships) {
            totalExpenses += membership.getPrice();
        }
        return totalExpenses;
    }
    

    // track revenue
    public double calcRevenue() throws SQLException {
        List<Membership> allMemberships = memDAO.getAllMemberships();
        double totalRevenue = 0.0;
        for (Membership membership : allMemberships) {
            totalRevenue += membership.getPrice();
        }
        return totalRevenue;
    }
    
    // get all memberships
    public List<Membership> getAllMemberships() throws SQLException {
        return memDAO.getAllMemberships();
    }
    
    // get user memberships
    public List<Membership> getUserMemberships(int userID) throws SQLException {
        return memDAO.getMembershipsByUserID(userID);
    }
    

    // check if active membership
    public boolean isMemActive(Membership mem) {
        if (mem == null) {
            return false;
        }
        
        if (!"active".equalsIgnoreCase(mem.getStatus())) {
            return false;
        }
        
        LocalDate endDate = LocalDate.parse(mem.getEndDate());
        return !LocalDate.now().isAfter(endDate);
    }
}
