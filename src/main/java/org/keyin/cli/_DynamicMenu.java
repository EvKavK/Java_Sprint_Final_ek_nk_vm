package org.keyin.cli;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.IntConsumer;

import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.ansi;
import org.fusesource.jansi.AnsiConsole;

public abstract class _DynamicMenu {
    private final Scanner scan;
    
    protected _DynamicMenu(Scanner scan) {
        this.scan = Objects.requireNonNull(scan, "ERR: NULL SCANNER");
        AnsiConsole.systemInstall();
    }

    protected void showMenu(String[] opts, IntConsumer handler) {
        while (true) {
            System.out.print(ansi().eraseScreen().cursor(1, 1));
            
            System.out.println(ansi()
                .fgBright(Ansi.Color.CYAN)
                .a("╔══════════════════════════════╗")
                .reset());
            System.out.println(ansi()
                .fgBright(Ansi.Color.CYAN)
                .a("║     GYM MANAGEMENT SYSTEM    ║")
                .reset());
            System.out.println(ansi()
                .fgBright(Ansi.Color.CYAN)
                .a("╚══════════════════════════════╝")
                .reset());
            System.out.println();

            for (int i = 0; i < opts.length; i++) {
                System.out.println(ansi()
                    .fgBright(Ansi.Color.GREEN)
                    .a(" [" + (i + 1) + "] ")
                    .fgBright(Ansi.Color.WHITE)
                    .a(opts[i])
                    .reset());
            }
            
            System.out.println(ansi()
                .fgBright(Ansi.Color.YELLOW)
                .a("\nEnter option: ")
                .reset());

            try {
                int input = Integer.parseInt(scan.nextLine().trim());
                if (input > 0 && input <= opts.length) {
                    handler.accept(input);
                    break;
                }
                showError("Invalid option selected");
            } catch (NumberFormatException e) {
                showError("Please enter a valid number");
            }
        }
    }

    protected void showError(String message) {
        System.out.println(ansi()
            .fgBright(Ansi.Color.RED)
            .a("Error: " + message)
            .reset());
    }

    protected void showSuccess(String message) {
        System.out.println(ansi()
            .fgBright(Ansi.Color.GREEN)
            .a("Sucess: " + message)
            .reset());
    }


    protected Scanner getScanner() {
        return scan;
    }
}
