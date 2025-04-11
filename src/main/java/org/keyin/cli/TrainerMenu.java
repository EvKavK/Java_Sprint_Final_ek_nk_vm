package org.keyin.cli;

import java.util.Scanner;


public class TrainerMenu extends _DynamicMenu {
    
    // constructor
    public TrainerMenu(Scanner scanner) {
        super(scanner);
        String[] options = {
            "OPTION 1",
            "OPTION 2", 
            "OPTION 3"
        };

        // show menu
        showMenu(options, (int choice) -> {
            System.out.println("TRAINER MENU");
            switch (choice) {
                case 1 ->
                    System.out.println("OPTION 1 SELECTED");
                case 2 ->
                    System.out.println("OPTION 2 SELECTED");
                case 3 ->
                    System.out.println("OPTION 3 SELECTED");
            }
        });
    }
} 