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
    private UserService userService;
    @SuppressWarnings("unused")
    private MembershipService membershipService;
    @SuppressWarnings("unused")
    private WorkoutClassService workoutClassService;
    
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
                System.out.println(ansi().fgBright(Ansi.Color.RED).a("DATABASE ERROR: " + e.getMessage()).reset());
            }
        });
    }
    
    // constructor for direct use (to be removed after integration)
    public __GymCLI(Scanner scan) {
        super(scan);
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
                System.out.println(ansi().fgBright(Ansi.Color.RED).a("DATABASE ERROR: " + e.getMessage()).reset());
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
        
        if (userService == null) {
            System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a(
                "REGISTRATION NOT AVAILABLE."
            ).reset());
            return;
        }
        
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
        
        String[] roles = {
            "MEMBER",
            "TRAINER",
            "ADMIN",
            "CANCEL"
        };
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a(
            "\nSELECT ROLE:"
        ).reset());
        
        showMenu(roles, (int choice) -> {
            try {
                User user = null;
                switch (choice) {
                    case 1 -> // member
                        user = userService.registerMember(username, password, email, phone, address, city, province, postalCode);
                    case 2 -> // trainer
                        user = userService.registerTrainer(username, password, email, phone, address, city, province, postalCode);
                    case 3 -> // admin
                        user = userService.registerAdmin(username, password, email, phone, address, city, province, postalCode);
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
                System.out.println(ansi().fgBright(Ansi.Color.RED).a("DATABASE ERROR: " + e.getMessage()).reset());
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
        
        if (userService == null) {
            String[] roles = {
                "ADMIN",
                "TRAINER", 
                "MEMBER", 
                "EXIT"
            };
            
            showMenu(roles, (int choice) -> {
                switch (choice) {
                    case 1 -> {
                        System.out.println(ansi().eraseScreen().cursor(1, 1));
                        @SuppressWarnings("unused")
                        AdminMenu adminMenu = new AdminMenu(getScanner());
                    }
                    case 2 -> {
                        System.out.println(ansi().eraseScreen().cursor(1, 1));
                        @SuppressWarnings("unused")
                        TrainerMenu trainerMenu = new TrainerMenu(getScanner());
                    }
                    case 3 -> {
                        System.out.println(ansi().eraseScreen().cursor(1, 1));
                        @SuppressWarnings("unused")
                        MemberMenu memberMenu = new MemberMenu(getScanner());
                    }
                    case 4 -> {
                        System.out.println(ansi()
                            .fgBright(Ansi.Color.YELLOW)
                            .a("RETURNING TO MAIN...")
                            .reset());
                    }
                    default -> System.out.println(ansi()
                        .fgBright(Ansi.Color.RED)
                        .a("INVALID CHOICE, TRY AGAIN.")
                        .reset());
                }
            });
            return;
        }
        
        Scanner scanner = getScanner();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER USERNAME: ").reset());
        String username = scanner.nextLine();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER PASSWORD: ").reset());
        String password = scanner.nextLine();
        
        User user = userService.authUser(username, password);
        if (user != null) {
            System.out.println(ansi().fgBright(Ansi.Color.GREEN).a("LOGIN SUCCESSFUL! WELCOME " + user.getUsername()).reset());
            
            switch (user.getRole().toLowerCase()) {
                case "admin" -> {
                    System.out.println(ansi().eraseScreen().cursor(1, 1));
                    @SuppressWarnings("unused")
                    AdminMenu adminMenu = new AdminMenu(getScanner());
                }
                case "trainer" -> {
                    System.out.println(ansi().eraseScreen().cursor(1, 1));
                    @SuppressWarnings("unused")
                    TrainerMenu trainerMenu = new TrainerMenu(getScanner());
                }
                case "member" -> {
                    System.out.println(ansi().eraseScreen().cursor(1, 1));
                    @SuppressWarnings("unused")
                    MemberMenu memberMenu = new MemberMenu(getScanner());
                }
                default -> {
                    System.out.println(ansi().fgBright(Ansi.Color.RED).a("Unknown role: " + user.getRole()).reset());
                }
            }
        } else {
            System.out.println(ansi().fgBright(Ansi.Color.RED).a("LOGIN FAILED! INVALID CREDENTIALS.").reset());
        }
    }

    // main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        @SuppressWarnings("unused")
        __GymCLI gymCLI = new __GymCLI(scanner);
    }
}