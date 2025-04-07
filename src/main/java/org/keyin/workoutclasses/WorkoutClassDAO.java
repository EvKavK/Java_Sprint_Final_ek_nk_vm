package org.keyin.workoutclasses;

import org.keyin.database.DatabaseConnection;
import org.keyin.user.User;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class WorkoutClassDAO {

    // CREATE
    public WorkoutClass createUser(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO workoutClasses (name, description, targetArea, reps, sets, setDuration, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, workoutClass.getName());
                pstmt.setString(2, workoutClass.getDescription());;
                pstmt.setString(3, workoutClass.getTargetArea());
                pstmt.setString(4, workoutClass.getReps());
                pstmt.setString(5, workoutClass.getSets());
                pstmt.setString(6, workoutClass.getSetDuration());
                pstmt.setString(7, workoutClass.getNotes());

                int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return workoutClass;
            }
        }
        return null;
    }

    // READ
    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        String sql = "SELECT * FROM workoutClasses";
        List<WorkoutClass> workoutClasses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("targetArea"),
                        rs.getString("reps"),
                        rs.getString("sets"),
                        rs.getString("setDuration"),
                        rs.getString("notes")
                );
                workoutClasses.add(workoutClass);
            }
        }
        return workoutClasses;
    }

    // UPDATE
    public WorkoutClass updateWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "UPDATE workoutClasses SET name = ?, description = ?, targetArea = ?, reps = ?, sets = ?, setDuration = ?, notes = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, workoutClass.getName());
                pstmt.setString(2, workoutClass.getDescription());;
                pstmt.setString(3, workoutClass.getTargetArea());
                pstmt.setString(4, workoutClass.getReps());
                pstmt.setString(5, workoutClass.getSets());
                pstmt.setString(6, workoutClass.getSetDuration());
                pstmt.setString(7, workoutClass.getNotes());
                pstmt.setInt(8, workoutClass.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return workoutClass;
            }
        }
        return null;
    }

    // DELETE
    public WorkoutClass deleteWorkoutClass (WorkoutClass workoutClass) throws SQLException {
        String sql = "DELETE FROM workoutClasses WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, workoutClass.getId());
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    return workoutClass;
                }
        }
        return null;
    }
}