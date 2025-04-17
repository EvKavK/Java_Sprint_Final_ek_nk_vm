package org.keyin.cli;

import org.fusesource.jansi.Ansi;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class TrainerMenu extends _DynamicMenu {
    private final User currentUser;
    private final WorkoutClassService workoutClassService;
    private final MembershipService membershipService;
    
    // constructor
    public TrainerMenu(Scanner scanner, User currentUser, WorkoutClassService workoutClassService, MembershipService membershipService) {
        super(scanner);
        this.currentUser = currentUser;
        this.workoutClassService = workoutClassService;
        this.membershipService = membershipService;
        
        String[] options = {
            "CREATE WORKOUT CLASS",
            "VIEW MY CLASSES",
            "UPDATE WORKOUT CLASS",
            "DELETE WORKOUT CLASS",
            "PURCHASE MEMBERSHIP",
            "EXIT"
        };

        // show menu
        showMenu(options, (int choice) -> {
            try {
                switch (choice) {
                    case 1 -> createWorkoutClass();
                    case 2 -> viewMyClasses();
                    case 3 -> updateWorkoutClass();
                    case 4 -> deleteWorkoutClass();
                    case 5 -> purchaseMembership();
                    case 6 -> System.out.println(ansi()
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

    private void createWorkoutClass() throws SQLException {
        Scanner scanner = getScanner();
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== CREATE WORKOUT CLASS ====").reset());
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER CLASS NAME: ").reset());
        String name = scanner.nextLine();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER DESCRIPTION: ").reset());
        String description = scanner.nextLine();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER TARGET AREA: ").reset());
        String targetArea = scanner.nextLine();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER REPS: ").reset());
        String reps = scanner.nextLine();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER SETS: ").reset());
        String sets = scanner.nextLine();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER DURATION: ").reset());
        String duration = scanner.nextLine();
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NOTES: ").reset());
        String notes = scanner.nextLine();
        
        // create class w trainer ID
        workoutClassService.createClass(
            name, description, targetArea, reps, sets, duration, notes, currentUser.getID()
        );
        
        showSuccess("WORKOUT CLASS CREATED SUCCESSFULLY!");
    }
    
    private void viewMyClasses() throws SQLException {
        List<WorkoutClass> myClasses = workoutClassService.getClassesByTrainer(currentUser.getID());
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== MY WORKOUT CLASSES ====").reset());
        
        if (myClasses.isEmpty()) {
            System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("YOU HAVE NO CLASSES YET.").reset());
            return;
        }
        
        System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
            String.format("%-5s %-20s %-30s %-15s",
                "ID", "NAME", "DESCRIPTION", "TARGET AREA")
        ).reset());
        
        for (WorkoutClass workoutClass : myClasses) {
            System.out.println(ansi().fgBright(Ansi.Color.WHITE).a(
                String.format("%-5d %-20s %-30s %-15s",
                    workoutClass.getID(), workoutClass.getName(),
                    workoutClass.getDescription(), workoutClass.getTargetArea())
            ).reset());
        }
    }
    
    private void updateWorkoutClass() throws SQLException {
        viewMyClasses(); // show classes
        
        Scanner scanner = getScanner();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("\nENTER CLASS ID TO UPDATE: ").reset());
        int classId;
        try {
            classId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            showError("INVALID ID FORMAT!");
            return;
        }
        
        List<WorkoutClass> myClasses = workoutClassService.getClassesByTrainer(currentUser.getID());
        WorkoutClass classToUpdate = null;
        
        for (WorkoutClass workoutClass : myClasses) {
            if (workoutClass.getID() == classId) {
                classToUpdate = workoutClass;
                break;
            }
        }
        
        if (classToUpdate == null) {
            showError("CLASS NOT FOUND OR NOT OWNED BY YOU!");
            return;
        }
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== UPDATE WORKOUT CLASS ====").reset());
        System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("(LEAVE BLANK TO KEEP CURRENT VALUE)").reset());
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NEW NAME [" + classToUpdate.getName() + "]: ").reset());
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            classToUpdate.setName(name);
        }
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NEW DESCRIPTION [" + classToUpdate.getDescription() + "]: ").reset());
        String description = scanner.nextLine();
        if (!description.trim().isEmpty()) {
            classToUpdate.setDescription(description);
        }
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NEW TARGET AREA [" + classToUpdate.getTargetArea() + "]: ").reset());
        String targetArea = scanner.nextLine();
        if (!targetArea.trim().isEmpty()) {
            classToUpdate.setTargetArea(targetArea);
        }
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NEW REPS [" + classToUpdate.getReps() + "]: ").reset());
        String reps = scanner.nextLine();
        if (!reps.trim().isEmpty()) {
            classToUpdate.setReps(reps);
        }
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NEW SETS [" + classToUpdate.getSets() + "]: ").reset());
        String sets = scanner.nextLine();
        if (!sets.trim().isEmpty()) {
            classToUpdate.setSets(sets);
        }
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NEW DURATION [" + classToUpdate.getSetDuration() + "]: ").reset());
        String duration = scanner.nextLine();
        if (!duration.trim().isEmpty()) {
            classToUpdate.setSetDuration(duration);
        }
        
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("ENTER NEW NOTES [" + classToUpdate.getNotes() + "]: ").reset());
        String notes = scanner.nextLine();
        if (!notes.trim().isEmpty()) {
            classToUpdate.setNotes(notes);
        }
        
        workoutClassService.updateClass(classToUpdate);
        showSuccess("WORKOUT CLASS UPDATED SUCCESSFULLY!");
    }
    
    private void deleteWorkoutClass() throws SQLException {
        viewMyClasses(); // show classes
        
        Scanner scanner = getScanner();
        System.out.print(ansi().fgBright(Ansi.Color.WHITE).a("\nENTER CLASS ID TO DELETE: ").reset());
        int classId;
        try {
            classId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            showError("INVALID ID FORMAT!");
            return;
        }
        
        List<WorkoutClass> myClasses = workoutClassService.getClassesByTrainer(currentUser.getID());
        WorkoutClass classToDelete = null;
        
        for (WorkoutClass workoutClass : myClasses) {
            if (workoutClass.getID() == classId) {
                classToDelete = workoutClass;
                break;
            }
        }
        
        if (classToDelete == null) {
            showError("CLASS NOT FOUND OR NOT OWNED BY YOU!");
            return;
        }
        
        // confirmation
        System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a(
            "ARE YOU SURE YOU WANT TO DELETE CLASS '" + classToDelete.getName() + "'? (Y/N): "
        ).reset());
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("Y")) {
            workoutClassService.deleteClass(classToDelete);
            showSuccess("WORKOUT CLASS DELETED SUCCESSFULLY!");
        } else {
            System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("DELETE OPERATION CANCELLED.").reset());
        }
    }
    
    private void purchaseMembership() throws SQLException {
        Scanner scanner = getScanner();
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("\n==== PURCHASE MEMBERSHIP ====").reset());
        
        String[] membershipTypes = {
            "STANDARD ($50/month)",
            "PREMIUM ($100/month)",
            "TRAINER SPECIAL ($75/month)",
            "CANCEL"
        };
        
        double[] prices = {50.0, 100.0, 75.0};
        
        System.out.println(ansi().fgBright(Ansi.Color.CYAN).a("SELECT MEMBERSHIP TYPE:").reset());
        
        showMenu(membershipTypes, (int choice) -> {
            try {
                if (choice == 4) {
                    System.out.println(ansi().fgBright(Ansi.Color.YELLOW).a("PURCHASE CANCELLED.").reset());
                    return;
                }
                
                if (choice < 1 || choice > 3) {
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