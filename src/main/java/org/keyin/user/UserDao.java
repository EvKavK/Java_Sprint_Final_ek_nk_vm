package org.keyin.user;

import org.keyin.database.DatabaseConnection;

import java.sql.*;

public class UserDao {

    // CREATE
    public User createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, first, last, email, dob, phone, address, city, province, postalCode, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getFirst());
                pstmt.setString(4, user.getLast());
                pstmt.setString(5, user.getEmail());
                pstmt.setDate(6, new java.sql.Date(user.getDob().getTime()));
                pstmt.setString(7, user.getPhone());
                pstmt.setString(8, user.getAddress());
                pstmt.setString(9, user.getCity());
                pstmt.setString(10, user.getProvince());
                pstmt.setString(11, user.getPostalCode());
                pstmt.setString(12, user.getRole());

                int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return user;
            }
        }
        return null;
    }

    // READ
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("first"),
                            rs.getString("last"),
                            rs.getString("email"),
                            rs.getDate("dob"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("province"),
                            rs.getString("postalCode"),
                            rs.getString("role")

                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public User updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, first = ?, last = ?, email = ?, dob = ?, phone = ?, address = ?, city = ?, province = ?, postalCode = ?, role = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirst());
            pstmt.setString(4, user.getLast());
            pstmt.setString(5, user.getEmail());
            pstmt.setDate(6, new java.sql.Date(user.getDob().getTime()));
            pstmt.setString(7, user.getPhone());
            pstmt.setString(8, user.getAddress());
            pstmt.setString(9, user.getCity());
            pstmt.setString(10, user.getProvince());
            pstmt.setString(11, user.getPostalCode());
            pstmt.setString(12, user.getRole());
            pstmt.setInt(13, user.getID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return user;
            }
        }
        return null;
    }

    // DELETE
    public User deleteUser (User user) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, user.getID());
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    return user;
                }
        }
        return null;
    }
}