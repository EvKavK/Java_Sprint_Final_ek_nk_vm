package org.keyin.cli;

import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.Objects;

// dynamic menu template for role-specific menus
public abstract class _DynamicMenu {
    private final Scanner scan;
    
    // constructor
    public _DynamicMenu(Scanner scan) {
        this.scan = Objects.requireNonNull(scan, "ERR: NULL SCANNER");
    }
    

    // show menu with options and handler
    protected void showMenu(String[] opts, IntConsumer handler) {
        
        // loop until all options shown
        while (true) {
            for (int i = 0; i < opts.length; i++) {
                System.out.println((i + 1) + ". " + opts[i]);
            }
            
            // get user input
            try {
                int input = Integer.parseInt(scan.nextLine().trim());
                if (input > 0 && input <= opts.length) {
                    handler.accept(input);
                    break;
                }
                System.out.println("ERR: INVALID OPTION");
            } catch (NumberFormatException e) {
                System.out.println("ERR: INVALID INPUT");
            }
        }
    }


    // scanner getter for subclasses
    protected Scanner getScanner() {
        return scan;
    }
}
