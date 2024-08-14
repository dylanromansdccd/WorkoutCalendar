package edu.sdccd.cisc191.workoutcalendar.model;

import jakarta.persistence.Entity;

@Entity
public class Cardio extends Workout{
    private int duration;

    public Cardio(String name, int calories_burned, int duration) {
        super(name, calories_burned);
        this.duration = duration;
    }

    public Cardio() {}

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Cardio{" +
                "name='" + name + '\'' +
                ", calories_burned=" + calories_burned +
                ", duration=" + duration +
                '}';
    }
}
