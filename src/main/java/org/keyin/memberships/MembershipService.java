package org.keyin.memberships;

import java.sql.SQLException;
import java.time.LocalDate;

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
            price
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
    public double calcUserExpenses(int userId) throws SQLException {
        // TODO: needs method in DAO to get all memberships
        return 0.0; // placeholder
    }
    

    // track revenue
    public double calcRevenue() throws SQLException {
        // TODO: needs method in DAO to get all memberships
        return 0.0; // placeholder
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
