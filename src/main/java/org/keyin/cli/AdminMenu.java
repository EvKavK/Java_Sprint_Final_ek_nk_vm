package org.keyin.cli;

import java.util.Scanner;


public class AdminMenu extends _DynamicMenu {
    
    // constructor
    public AdminMenu(Scanner scanner) {
        super(scanner);
        String[] options = {
            "OPTION 1",
            "OPTION 2", 
            "OPTION 3"
        };

        // show menu
        showMenu(options, (int choice) -> {
            System.out.println("ADMIN MENU");
            switch (choice) {
                case 1:
                    System.out.println("OPTION 1 SELECTED");
                    break;
                case 2:
                    System.out.println("OPTION 2 SELECTED");
                    break;
                case 3:
                    System.out.println("OPTION 3 SELECTED");
                    break;
            }
        });
    }
} 