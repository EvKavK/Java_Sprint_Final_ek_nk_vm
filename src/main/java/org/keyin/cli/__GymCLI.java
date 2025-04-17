package org.keyin.cli;

import java.sql.SQLException;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassService;

import static org.fusesource.jansi.Ansi.ansi;

public class __GymCLI extends _DynamicMenu {
    private final UserService userService;
    private final MembershipService membershipService;
    private final WorkoutClassService workoutClassService;
    
    // constructor
    public __GymCLI(Scanner scan, UserService userService, MembershipService membershipService, WorkoutClassService workoutClassService) {
        super(scan);
        this.userService = userService;
        this.membershipService = membershipService;
        this.workoutClassService = workoutClassService;
        
        showWelcomeBanner();
        String[] options = {
            "REGISTER", 
            "LOGIN",
            "EXIT"
        };

        showMenu(options, (int choice) -> {
            try {
                switch (choice) {
                    case 1 -> registerUser();
                    case 2 -> loginUser();
                    case 3 -> System.out.println(ansi()
                        .fgBright(Ansi.Color.CYAN)
                        .a("THANK YOU FOR USING THE GYM MANAGEMENT SYSTEM!")
                        .reset());
                    default -> System.out.println(ansi()
                        .fgBright(Ansi.Color.RED)
                        .a("INVALID CHOICE, TRY AGAIN.")
                        .reset());
                }
            } catch (SQLException e) {
                handleDatabaseError(e);
            }
        });
    }
    
    private void showWelcomeBanner() {
        System.out.println(ansi().eraseScreen().cursor(1, 1));
        showLogo();
        System.out.print(ansi().fgBright(Ansi.Color.YELLOW).a("\n\n                        INITIALIZING").reset());
        System.out.print(ansi().cursorUp(2).eraseLine());
        System.out.println(ansi().fgBright(Ansi.Color.GREEN).a("\n\n                        SYSTEM READY").reset());
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        showLogo();
    }

    // New method to show the FITNESS logo
    private void showLogo() {
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("""
            
            ╔══════════════════════════════════════════════════════════╗
            ║  ███████╗██╗████████╗███╗   ██╗███████╗███████╗███████╗  ║
            ║  ██╔════╝██║╚══██╔══╝████╗  ██║██╔════╝██╔════╝██╔════╝  ║
            ║  █████╗  ██║   ██║   ██╔██╗ ██║█████╗  ███████╗███████╗  ║
            ║  ██╔══╝  ██║   ██║   ██║╚██╗██║██╔══╝  ╚════██║╚════██║  ║
            ║  ██║     ██║   ██║   ██║ ╚████║███████╗███████║███████║  ║
            ║  ╚═╝     ╚═╝   ╚═╝   ╚═╝  ╚═══╝╚══════╝╚══════╝╚══════╝  ║
            ║                                                          ║
            ║                    GYM MANAGEMENT SYSTEM                 ║
            ╚══════════════════════════════════════════════════════════╝
            
            """).reset());
    }

    // register user
    private void registerUser() throws SQLException {
        System.out.println(ansi().eraseScreen().cursor(1, 1));
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("""
            
            ╔════════════════════════════════════╗
            ║         USER REGISTRATION          ║
            ║      ┌─────────────────────┐       ║
            ║      │  NEW MEMBER SIGNUP  │       ║
            ║      └─────────────────────┘       ║
            ╚════════════════════════════════════╝
            """).reset());
        
        Scanner scanner = getScanner();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER USERNAME: ").reset());
        String username = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER PASSWORD: ").reset());
        String password = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER EMAIL: ").reset());
        String email = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER PHONE: ").reset());
        String phone = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER ADDRESS: ").reset());
        String address = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER CITY: ").reset());
        String city = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER PROVINCE: ").reset());
        String province = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER POSTAL CODE: ").reset());
        String postalCode = scanner.nextLine();
        
        String[] roleOptions = {
            _UserRoles.MEMBER.name(),
            _UserRoles.TRAINER.name(),
            _UserRoles.ADMIN.name(),
            "CANCEL"
        };
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a(
            "\nSELECT ROLE:"
        ).reset());
        
        showMenu(roleOptions, (int choice) -> {
            try {
                User user = null;
                switch (choice) {
                    case 1 -> // member
                        user = userService.registerUser(username, password, email, phone, address, city, province, postalCode, _UserRoles.MEMBER);
                    case 2 -> // trainer
                        user = userService.registerUser(username, password, email, phone, address, city, province, postalCode, _UserRoles.TRAINER);
                    case 3 -> // admin
                        user = userService.registerUser(username, password, email, phone, address, city, province, postalCode, _UserRoles.ADMIN);
                    case 4 -> { // cancel
                        System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("REGISTRATION CANCELLED.").reset());
                        return;
                    }
                }
                
                if (user != null) {
                    System.out.println(ansi().fgBright(Ansi.Color.GREEN).a("USER REGISTERED SUCCESSFULLY!").reset());
                } else {
                    System.out.println(ansi().fgBright(Ansi.Color.RED).a("REGISTRATION FAILED!").reset());
                }
            } catch (SQLException e) {
                handleDatabaseError(e);
            }
        });
    }

    // login user
    private void loginUser() throws SQLException {
        System.out.println(ansi().eraseScreen().cursor(1, 1));
        showLogo();
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("""
            ╔══════════════════════════════════════╗
            ║             LOGIN PORTAL             ║
            ║      ┌────────────────────────┐      ║
            ║      │ Enter Your Credentials │      ║
            ║      └────────────────────────┘      ║
            ╚══════════════════════════════════════╝
            """).reset());
        
        Scanner scanner = getScanner();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER USERNAME: ").reset());
        String username = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER PASSWORD: ").reset());
        String password = scanner.nextLine();
        
        User user = userService.authUser(username, password);
        if (user != null) {
            System.out.println(ansi().fgBright(Ansi.Color.GREEN).a("LOGIN SUCCESSFUL! WELCOME " + user.getUsername()).reset());
            
            _UserRoles role = _UserRoles.fromDbValue(user.getRole());
            if (role != null) {
                switch (role) {
                    case ADMIN -> {
                        System.out.println(ansi().eraseScreen().cursor(1, 1));
                        @SuppressWarnings("unused")
                        AdminMenu adminMenu = new AdminMenu(getScanner(), userService, membershipService);
                    }
                    case TRAINER -> {
                        System.out.println(ansi().eraseScreen().cursor(1, 1));
                        @SuppressWarnings("unused")
                        TrainerMenu trainerMenu = new TrainerMenu(getScanner(), user, workoutClassService, membershipService);
                    }
                    case MEMBER -> {
                        System.out.println(ansi().eraseScreen().cursor(1, 1));
                        @SuppressWarnings("unused")
                        MemberMenu memberMenu = new MemberMenu(getScanner(), user, workoutClassService, membershipService);
                    }
                }
            } else {
                System.out.println(ansi().fgBright(Ansi.Color.RED).a("Unknown role: " + user.getRole()).reset());
            }
        } else {
            System.out.println(ansi().fgBright(Ansi.Color.RED).a("LOGIN FAILED! INVALID CREDENTIALS.").reset());
        }
    }
}