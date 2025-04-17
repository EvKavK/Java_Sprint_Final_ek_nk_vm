package org.keyin.cli;

import org.fusesource.jansi.Ansi;
import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class MemberMenu extends _DynamicMenu {
    private final User currentUser;
    private final WorkoutClassService workoutClassService;
    private final MembershipService membershipService;
    
    // constructor
    public MemberMenu(Scanner scanner, User currentUser, WorkoutClassService workoutClassService, MembershipService membershipService) {
        super(scanner);
        this.currentUser = currentUser;
        this.workoutClassService = workoutClassService;
        this.membershipService = membershipService;
        
        String[] options = {
            "BROWSE WORKOUT CLASSES",
            "SEARCH CLASSES BY TARGET AREA",
            "VIEW MY MEMBERSHIP EXPENSES",
            "PURCHASE MEMBERSHIP",
            "EXIT"
        };

        // show menu
        showMenu(options, (int choice) -> {
            try {
                switch (choice) {
                    case 1 -> browseWorkoutClasses();
                    case 2 -> searchClassesByTarget();
                    case 3 -> viewMembershipExpenses();
                    case 4 -> purchaseMembership();
                    case 5 -> System.out.println(ansi()
                        .fgBright(Ansi.Color.YELLOW)
                        .a("RETURNING TO MAIN MENU...")
                        .reset());
                    default -> showError("INVALID CHOICE, TRY AGAIN");
                }
            } catch (SQLException e) {
                handleDatabaseError(e);
            }
        });
    }

    private void browseWorkoutClasses() throws SQLException {
        List<WorkoutClass> classes = workoutClassService.getAllClasses();
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== ALL WORKOUT CLASSES ====").reset());
        
        if (classes.isEmpty()) {
            System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("NO CLASSES AVAILABLE.").reset());
            return;
        }
        
        displayWorkoutClasses(classes);
    }
    
    private void searchClassesByTarget() throws SQLException {
        Scanner scanner = getScanner();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER TARGET AREA TO SEARCH: ").reset());
        String targetArea = scanner.nextLine();
        
        List<WorkoutClass> classes = workoutClassService.searchByTargetArea(targetArea);
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== CLASSES FOR TARGET AREA: " + targetArea + " ====").reset());
        
        if (classes.isEmpty()) {
            System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("NO CLASSES FOUND FOR THIS TARGET AREA.").reset());
            return;
        }
        
        displayWorkoutClasses(classes);
    }
    
    private void displayWorkoutClasses(List<WorkoutClass> classes) {
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
            String.format("%-5s %-20s %-15s %-10s %-10s %-10s",
                "ID", "NAME", "TARGET AREA", "REPS", "SETS", "DURATION")
        ).reset());
        
        for (WorkoutClass workoutClass : classes) {
            System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
                String.format("%-5d %-20s %-15s %-10s %-10s %-10s",
                    workoutClass.getID(), workoutClass.getName(),
                    workoutClass.getTargetArea(), workoutClass.getReps(),
                    workoutClass.getSets(), workoutClass.getSetDuration())
            ).reset());
        }
        
        Scanner scanner = getScanner();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("\nENTER CLASS ID TO VIEW DETAILS (0 TO SKIP): ").reset());
        try {
            int classId = Integer.parseInt(scanner.nextLine());
            if (classId > 0) {
                for (WorkoutClass workoutClass : classes) {
                    if (workoutClass.getID() == classId) {
                        displayClassDetails(workoutClass);
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            // skip if not number
        }
    }
    
    private void displayClassDetails(WorkoutClass workoutClass) {
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== CLASS DETAILS ====").reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a("NAME: " + workoutClass.getName()).reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a("DESCRIPTION: " + workoutClass.getDescription()).reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a("TARGET AREA: " + workoutClass.getTargetArea()).reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a("REPS: " + workoutClass.getReps()).reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a("SETS: " + workoutClass.getSets()).reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a("DURATION: " + workoutClass.getSetDuration()).reset());
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a("NOTES: " + workoutClass.getNotes()).reset());
    }
    
    private void viewMembershipExpenses() throws SQLException {
        List<Membership> myMemberships = membershipService.getUserMemberships(currentUser.getID());
        double totalExpenses = membershipService.calcUserExpenses(currentUser.getID());
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== MY MEMBERSHIPS ====").reset());
        
        if (myMemberships.isEmpty()) {
            System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("YOU HAVE NO MEMBERSHIPS.").reset());
            return;
        }
        
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
            String.format("%-5s %-15s %-12s %-12s %-10s %-10s",
                "ID", "TYPE", "START DATE", "END DATE", "STATUS", "PRICE($)")
        ).reset());
        
        for (Membership membership : myMemberships) {
            System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
                String.format("%-5d %-15s %-12s %-12s %-10s %-10.2f",
                    membership.getId(), membership.getType(),
                    membership.getStartDate(), membership.getEndDate(),
                    membership.getStatus(), membership.getPrice())
            ).reset());
        }
        
        System.out.println(ansi().fgBright(Ansi.Color.GREEN).a(
            "\nTOTAL EXPENSES: $" + String.format("%.2f", totalExpenses)
        ).reset());
    }
    
    private void purchaseMembership() throws SQLException {
        Scanner scanner = getScanner();
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== PURCHASE MEMBERSHIP ====").reset());
        
        String[] membershipTypes = {
            "STANDARD ($50/month)",
            "PREMIUM ($100/month)",
            "STUDENT ($40/month)",
            "SENIOR ($45/month)",
            "CANCEL"
        };
        
        double[] prices = {50.0, 100.0, 40.0, 45.0};
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("SELECT MEMBERSHIP TYPE:").reset());
        
        showMenu(membershipTypes, (int choice) -> {
            try {
                if (choice == 5) {
                    System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("PURCHASE CANCELLED.").reset());
                    return;
                }
                
                if (choice < 1 || choice > 4) {
                    showError("INVALID CHOICE!");
                    return;
                }
                
                System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER DURATION (MONTHS): ").reset());
                int duration;
                try {
                    duration = Integer.parseInt(scanner.nextLine());
                    if (duration <= 0) {
                        showError("DURATION MUST BE POSITIVE!");
                        return;
                    }
                } catch (NumberFormatException e) {
                    showError("INVALID DURATION FORMAT!");
                    return;
                }
                
                String type = membershipTypes[choice-1].split(" ")[0].toLowerCase();
                double price = prices[choice-1] * duration;
                
                membershipService.buyMembership(
                    currentUser.getID(), type, price, duration
                );
                
                showSuccess("MEMBERSHIP PURCHASED SUCCESSFULLY! TOTAL COST: $" + String.format("%.2f", price));
            } catch (SQLException e) {
                handleDatabaseError(e);
            }
        });
    }
} 