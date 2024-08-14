package edu.sdccd.cisc191.workoutcalendar.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Workout {
    @Id
    protected String name;
    protected int calories_burned;

    public Workout(String name, int calories_burned) {
        this.name = name;
        this.calories_burned = calories_burned;
    }

    public Workout() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories_burned() {
        return calories_burned;
    }

    public void setCalories_burned(int calories_burned) {
        this.calories_burned = calories_burned;
    }
}
