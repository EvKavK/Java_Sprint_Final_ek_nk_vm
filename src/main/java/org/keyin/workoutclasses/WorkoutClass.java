package org.keyin.workoutclasses;

public class WorkoutClass {
    private int id;
    private String name;
    private String description;
    private String targetArea;
    private String reps;
    private String sets;
    private String setDuration;
    private String notes;

    // constructor
    public WorkoutClass(int id, String name, String description, String targetArea, String reps, String sets, String setDuration, String notes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.targetArea = targetArea;
        this.reps = reps;
        this.sets = sets;
        this.setDuration = setDuration;
        this.notes = notes;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTargetArea() {
        return targetArea;
    }

    public String getReps() {
        return reps;
    }
    
    public String getSets() {
        return sets;
    }

    public String getSetDuration() {
        return setDuration;
    }

    public String getNotes() {
        return notes;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetArea(String targetArea) {
        this.targetArea = targetArea;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }
    
    public void setSetDuration(String setDuration) {
        this.setDuration = setDuration;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "WorkoutClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", targetArea='" + targetArea + '\'' +
                ", reps='" + reps + '\'' +
                ", sets='" + sets + '\'' +
                ", setDuration='" + setDuration + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}