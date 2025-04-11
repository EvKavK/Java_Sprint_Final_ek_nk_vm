package org.keyin;

import org.keyin.cli.__GymCLI;
import org.keyin.memberships.MembershipDAO;
import org.keyin.memberships.MembershipService;
import org.keyin.user.UserDao;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassDAO;
import org.keyin.workoutclasses.WorkoutClassService;

import java.sql.SQLException;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) throws SQLException {
        // DAO init
        UserDao userDao = new UserDao();
        MembershipDAO membershipDAO = new MembershipDAO();
        WorkoutClassDAO workoutClassDAO = new WorkoutClassDAO();
        
        // services init
        UserService userService = new UserService(userDao);
        MembershipService membershipService = new MembershipService(membershipDAO);
        WorkoutClassService workoutService = new WorkoutClassService(workoutClassDAO);

        // start CLI with try-with-resources
        try (Scanner scanner = new Scanner(System.in)) {
            @SuppressWarnings("unused")
            __GymCLI gymCLI = new __GymCLI(scanner, userService, membershipService, workoutService);
        }
    }
}
