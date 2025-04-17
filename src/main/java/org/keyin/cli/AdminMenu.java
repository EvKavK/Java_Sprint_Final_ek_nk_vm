package org.keyin.cli;

import org.fusesource.jansi.Ansi;
import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class AdminMenu extends _DynamicMenu {
    private final UserService userService;
    private final MembershipService membershipService;
    
    // constructor
    public AdminMenu(Scanner scanner, UserService userService, MembershipService membershipService) {
        super(scanner);
        this.userService = userService;
        this.membershipService = membershipService;
        
        String[] options = {
            "VIEW ALL USERS",
            "DELETE USER",
            "VIEW MEMBERSHIPS & REVENUE",
            "EXIT"
        };

        // show menu
        showMenu(options, (int choice) -> {
            try {
                switch (choice) {
                    case 1 -> viewAllUsers();
                    case 2 -> deleteUser();
                    case 3 -> viewMembershipsAndRevenue();
                    case 4 -> System.out.println(ansi()
                        .fgBright(Ansi.Color.YELLOW)
                        .a("RETURNING TO MAIN MENU...")
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
    
    private void viewAllUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== ALL USERS ====").reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
            String.format("%-5s %-15s %-10s %-25s %-15s",
                "ID", "USERNAME", "ROLE", "EMAIL", "PHONE")
        ).reset());
        
        for (User user : users) {
            System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
                String.format("%-5d %-15s %-10s %-25s %-15s",
                    user.getID(), user.getUsername(), user.getRole(), 
                    user.getEmail(), user.getPhone())
            ).reset());
        }
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\nTOTAL USERS: " + users.size()).reset());
    }
    
    private void deleteUser() throws SQLException {
        Scanner scanner = getScanner();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER USERNAME TO DELETE: ").reset());
        String username = scanner.nextLine();
        
        User user = userService.getUserByName(username);
        if (user == null) {
            System.out.println(ansi().fgBright(Ansi.Color.RED).a("USER NOT FOUND!").reset());
            return;
        }
        
        // ask for confirmation
        System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a(
            "ARE YOU SURE YOU WANT TO DELETE " + username + "? (Y/N): "
        ).reset());
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("Y")) {
            userService.deleteUser(user);
            System.out.println(ansi().fgBright(Ansi.Color.GREEN).a("USER DELETED SUCCESSFULLY!").reset());
        } else {
            System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("DELETE OPERATION CANCELLED.").reset());
        }
    }
    
    private void viewMembershipsAndRevenue() throws SQLException {
        List<Membership> memberships = membershipService.getAllMemberships();
        double totalRevenue = membershipService.calcRevenue();
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== ALL MEMBERSHIPS ====").reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
            String.format("%-5s %-8s %-15s %-12s %-12s %-10s %-10s",
                "ID", "USER ID", "TYPE", "START DATE", "END DATE", "STATUS", "PRICE($)")
        ).reset());
        
        for (Membership membership : memberships) {
            System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
                String.format("%-5d %-8d %-15s %-12s %-12s %-10s %-10.2f",
                    membership.getId(), membership.getUserID(), membership.getType(),
                    membership.getStartDate(), membership.getEndDate(),
                    membership.getStatus(), membership.getPrice())
            ).reset());
        }
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a(
            "\nTOTAL MEMBERSHIPS: " + memberships.size()
        ).reset());
        System.out.println(ansi().fgBright(Ansi.Color.GREEN).a(
            "TOTAL REVENUE: $" + String.format("%.2f", totalRevenue)
        ).reset());
    }
}