package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

// DAOs are responsible for handling the interactions with the database
public class MembershipDAO {

    // CREATE
    public Membership createMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (userID, type, startDate, endDate, status, price, durMonths) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, membership.getUserID());
                pstmt.setString(2, membership.getType());
                Date startDate = Date.valueOf(membership.getStartDate());
                Date endDate = Date.valueOf(membership.getEndDate());
                pstmt.setDate(3, startDate);
                pstmt.setDate(4, endDate);
                pstmt.setString(5, membership.getStatus());
                pstmt.setDouble(6, membership.getPrice());
                pstmt.setInt(7, membership.getDurMonths());

                int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return membership;
            }
        }
        return null;
    }

    // READ
    public Membership getMembershipById(int id) throws SQLException {
        String sql = "SELECT * FROM memberships WHERE id = ?";
//      DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Membership(
                            rs.getInt("id"),
                            rs.getInt("userID"),
                            rs.getString("type"),
                            rs.getString("startDate"),
                            rs.getString("endDate"),
                            rs.getString("status"),
                            rs.getDouble("price"),
                            rs.getInt("durMonths")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public Membership updateMembership(Membership membership) throws SQLException {
        String sql = "UPDATE memberships SET userID = ?, type = ?, startDate = ?, endDate = ?, status = ?, price = ?, durMonths = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, membership.getUserID());
                pstmt.setString(2, membership.getType());
                Date startDate = Date.valueOf(membership.getStartDate());
                Date endDate = Date.valueOf(membership.getEndDate());
                pstmt.setDate(3, startDate);
                pstmt.setDate(4, endDate);
                pstmt.setString(5, membership.getStatus());
                pstmt.setDouble(6, membership.getPrice());
                pstmt.setInt(7, membership.getDurMonths());
                pstmt.setInt(8, membership.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return membership;
            }
        }
        return null;
    }

    // DELETE
    public Membership deleteMembership (Membership membership) throws SQLException {
        String sql = "DELETE FROM memberships WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, membership.getId());
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    return membership;
                }
        }
        return null;
    }

    // get all memberships
    public List<Membership> getAllMemberships() throws SQLException {
        String sql = "SELECT * FROM memberships";
        List<Membership> memberships = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Membership membership = new Membership(
                    rs.getInt("id"),
                    rs.getInt("userID"),
                    rs.getString("type"),
                    rs.getString("startDate"),
                    rs.getString("endDate"),
                    rs.getString("status"),
                    rs.getDouble("price"),
                    rs.getInt("durMonths")
                );
                memberships.add(membership);
            }
        }
        return memberships;
    }
    
    // get memberships by user ID
    public List<Membership> getMembershipsByUserID(int userID) throws SQLException {
        String sql = "SELECT * FROM memberships WHERE userID = ?";
        List<Membership> memberships = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Membership membership = new Membership(
                        rs.getInt("id"),
                        rs.getInt("userID"),
                        rs.getString("type"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("status"),
                        rs.getDouble("price"),
                        rs.getInt("durMonths")
                    );
                    memberships.add(membership);
                }
            }
        }
        return memberships;
    }
}
