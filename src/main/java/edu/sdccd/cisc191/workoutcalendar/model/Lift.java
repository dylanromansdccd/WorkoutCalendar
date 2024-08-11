package edu.sdccd.cisc191.workoutcalendar.model;

import jakarta.persistence.Entity;

@Entity
public class Lift extends Workout{
    private String muscle;
    private int reps;
    private int sets;

    public Lift(String name, int calories_burned, String muscle, int reps, int sets) {
        super(name, calories_burned);

        this.muscle = muscle;
        this.reps = reps;
        this.sets = sets;
    }

    public Lift() {}

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "Lift{" +
                "name='" + name + '\'' +
                ", calories_burned=" + calories_burned +
                ", muscle='" + muscle + '\'' +
                ", reps=" + reps +
                ", sets=" + sets +
                '}';
    }
}
