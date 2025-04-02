package org.keyin.cli;

import java.util.Scanner;


public class __GymCLI extends _DynamicMenu {
    
    // constructor
    public __GymCLI(Scanner scan) {
        super(scan);
        String[] options = {
            "REGISTER", 
            "LOGIN",
            "EXIT"
        };


        // show menu
        showMenu(options, (int choice) -> {
            switch (choice) {
                case 1: registerUser(); 
                break;
                case 2: loginUser(); 
                break;
                case 3: System.out.println("EXITING"); 
                break;
                default:
                    System.out.println("INVALID CHOICE");
            }
        });
    }
    

    // register user
    private void registerUser() {
        System.out.println("REGISTER"); // TODO: add actual registration method
    }
    

    // login user
    private void loginUser() {
        String[] roles = {      // TODO: add actual role check based on _UserRoles enum
            "ADMIN",            // this is just a placeholder to test the menu system
            "TRAINER", 
            "MEMBER", 
            "EXIT"
        };

        showMenu(roles, (int choice) -> {
            switch (choice) {
                case 1:
                    new AdminMenu(getScanner());
                    break;
                case 2:
                    new TrainerMenu(getScanner());
                    break;
                case 3:
                    new MemberMenu(getScanner());
                    break;
                case 4:
                    System.out.println("EXITING");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
            }
        });
    }
    

    // main method
    public static void main(String[] args) {
        new __GymCLI(new Scanner(System.in));
    }
} 