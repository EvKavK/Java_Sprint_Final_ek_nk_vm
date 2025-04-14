package org.keyin.workoutclasses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassService {
    // Inject in the DAO to be able to use it in the service
    private final WorkoutClassDAO workoutDAO;

    public WorkoutClassService(WorkoutClassDAO workoutDAO) {
        this.workoutDAO = workoutDAO;
    }
    
    // new workout class
    public WorkoutClass createClass(String name, String desc, String target, String reps, String sets, String duration, String notes, int trainerID) throws SQLException {
        WorkoutClass workout = new WorkoutClass(
            0, // set by db
            name,
            desc,
            target,
            reps,
            sets,
            duration,
            notes,
            trainerID
        );
        
        return workoutDAO.createWorkoutClass(workout);
    }
    
    // update workout class
    public WorkoutClass updateClass(WorkoutClass workout) throws SQLException {
        return workoutDAO.updateWorkoutClass(workout);
    }
    
    // delete workout class
    public WorkoutClass deleteClass(WorkoutClass workout) throws SQLException {
        return workoutDAO.deleteWorkoutClass(workout);
    }
    
    // view workout classes
    public List<WorkoutClass> getAllClasses() throws SQLException {
        return workoutDAO.getAllWorkoutClasses();
    }
    
    // search workout classes by target area
    public List<WorkoutClass> searchByTargetArea(String target) throws SQLException {
        List<WorkoutClass> allClasses = workoutDAO.getAllWorkoutClasses();
        List<WorkoutClass> filtered = new ArrayList<>();
        
        for (WorkoutClass workout : allClasses) {
            if (workout.getTargetArea().toLowerCase().contains(target.toLowerCase())) {
                filtered.add(workout);
            }
        }
        
        return filtered;
    }
    
    // get classes by trainer
    public List<WorkoutClass> getClassesByTrainer(int trainerID) throws SQLException {
        return workoutDAO.getClassesByTrainerID(trainerID);
    }
}