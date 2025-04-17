package org.keyin.user;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // CREATE
    public User createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, phone, address, city, province, postalCode, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getPhone());
                pstmt.setString(5, user.getAddress());
                pstmt.setString(6, user.getCity());
                pstmt.setString(7, user.getProvince());
                pstmt.setString(8, user.getPostalCode());
                pstmt.setString(9, user.getRole());

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
//        DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
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

    // get all users
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postalCode"),
                        rs.getString("role")
                    );
                    users.add(user);
                }
            }
        return users;
    }

    // UPDATE
    public User updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, phone = ?, address = ?, city = ?, province = ?, postalCode = ?, role = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getCity());
            pstmt.setString(7, user.getProvince());
            pstmt.setString(8, user.getPostalCode());
            pstmt.setString(9, user.getRole());
            pstmt.setInt(10, user.getID());

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