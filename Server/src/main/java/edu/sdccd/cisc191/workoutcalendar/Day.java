package edu.sdccd.cisc191.workoutcalendar;

import edu.sdccd.cisc191.workoutcalendar.model.Cardio;
import edu.sdccd.cisc191.workoutcalendar.model.Lift;

public class Day {
    private int day_of_week;
    private int day_of_month;
    DoublyLinkedList<Lift> lifts;
    Cardio cardio;

    public Day(int day_of_week, int day_of_month) {
        this.day_of_week = day_of_week;
        this.day_of_month = day_of_month;
        lifts = new DoublyLinkedList<Lift>();
    }

    public Day(int day_of_week, int day_of_month, DoublyLinkedList<Lift> lifts, Cardio cardio) {
        this.day_of_week = day_of_week;
        this.day_of_month = day_of_month;
        this.lifts = lifts;
        this.cardio = cardio;
    }

    public int getDayOfWeek() {
        return day_of_week;
    }

    public int getDayOfMonth() {
        return day_of_month;
    }

    public DoublyLinkedList getLifts() {
        return lifts;
    }

    public void setLifts(DoublyLinkedList lifts) {
        this.lifts = lifts;
    }

    public Cardio getCardio() {
        return cardio;
    }

    public void setCardio(Cardio cardio) {
        this.cardio = cardio;
    }

    public void addLift(Lift lift) {
        lifts.add(lift);
    }
}
