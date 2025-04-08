package org.keyin.cli;

import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.ansi;

public class __GymCLI extends _DynamicMenu {
    
    // constructor
    public __GymCLI(Scanner scan) {
        super(scan);
        showWelcomeBanner();
        String[] options = {
            "REGISTER", 
            "LOGIN",
            "EXIT"
        };

        showMenu(options, (int choice) -> {
            switch (choice) {
                case 1: registerUser(); 
                break;
                case 2: loginUser(); 
                break;
                case 3: 
                    System.out.println(ansi()
                        .fgBright(Ansi.Color.CYAN)
                        .a("Thank you for using the Gym Management System!")
                        .reset()); 
                break;
                default:
                    System.out.println(ansi()
                        .fgBright(Ansi.Color.RED)
                        .a("Invalid choice, please try again.")
                        .reset());
            }
        });
    }
    
    private void showWelcomeBanner() {
        System.out.println(ansi().eraseScreen().cursor(1, 1));
        showLogo();
        System.out.print(ansi().fgBright(Ansi.Color.YELLOW).a("\n\n                        Initializing").reset());
        sleep(800);
        System.out.print(ansi().cursorUp(2).eraseLine());
        System.out.println(ansi().fgBright(Ansi.Color.GREEN).a("\n\n                        System Ready").reset());
        sleep(1000);
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        showLogo();
    }

    // New method to show the FITNESS logo
    private void showLogo() {
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a(
            "\n" +
            "╔══════════════════════════════════════════════════════════╗\n" +
            "║  ███████╗██╗████████╗███╗   ██╗███████╗███████╗███████╗  ║\n" +
            "║  ██╔════╝██║╚══██╔══╝████╗  ██║██╔════╝██╔════╝██╔════╝  ║\n" +
            "║  █████╗  ██║   ██║   ██╔██╗ ██║█████╗  ███████╗███████╗  ║\n" +
            "║  ██╔══╝  ██║   ██║   ██║╚██╗██║██╔══╝  ╚════██║╚════██║  ║\n" +
            "║  ██║     ██║   ██║   ██║ ╚████║███████╗███████║███████║  ║\n" +
            "║  ╚═╝     ╚═╝   ╚═╝   ╚═╝  ╚═══╝╚══════╝╚══════╝╚══════╝  ║\n" +
            "║                                                          ║\n" +
            "║                    GYM MANAGEMENT SYSTEM                 ║\n" +
            "╚══════════════════════════════════════════════════════════╝\n" +
            "\n"
        ).reset());
    }

    // register user
    private void registerUser() {
        System.out.println(ansi().eraseScreen().cursor(1, 1));
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a(
            "\n" +
            "╔════════════════════════════════════╗\n" +
            "║         USER REGISTRATION          ║\n" +
            "║      ┌─────────────────────┐       ║\n" +
            "║      │  NEW MEMBER SIGNUP  │       ║\n" +
            "║      └─────────────────────┘       ║\n" +
            "╚════════════════════════════════════╝\n"
        ).reset());
        // TODO: add actual registration method
    }

    // login user
    private void loginUser() {
        String[] roles = {      // TODO: add actual role check based on _UserRoles enum
            "ADMIN",            // this is just a placeholder to test the menu system
            "TRAINER", 
            "MEMBER", 
            "EXIT"
        };

        System.out.println(ansi().eraseScreen().cursor(1, 1));
        showLogo();
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a(
            "╔══════════════════════════════════════╗\n" +
            "║             LOGIN PORTAL             ║\n" +
            "║      ┌────────────────────────┐      ║\n" +
            "║      │ Select Your Role Below │      ║\n" +
            "║      └────────────────────────┘      ║\n" +
            "╚══════════════════════════════════════╝\n" 
        ).reset());

        showMenu(roles, (int choice) -> {
            switch (choice) {
                case 1:
                    System.out.println(ansi().eraseScreen().cursor(1, 1));
                    new AdminMenu(getScanner());
                    break;
                case 2:
                    System.out.println(ansi().eraseScreen().cursor(1, 1));
                    new TrainerMenu(getScanner());
                    break;
                case 3:
                    System.out.println(ansi().eraseScreen().cursor(1, 1));
                    new MemberMenu(getScanner());
                    break;
                case 4:
                    System.out.println(ansi()
                        .fgBright(Ansi.Color.YELLOW)
                        .a("Returning to main...")
                        .reset());
                    sleep(1000);
                    break;
                default:
                    System.out.println(ansi()
                        .fgBright(Ansi.Color.RED)
                        .a("Invalid choice, please try again.")
                        .reset());
            }
        });
    }

    // helper method for any delays used in the program
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // main method
    public static void main(String[] args) {
        new __GymCLI(new Scanner(System.in));
    }
}