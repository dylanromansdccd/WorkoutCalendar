package edu.sdccd.cisc191.workoutcalendar;

import edu.sdccd.cisc191.workoutcalendar.model.Cardio;
import edu.sdccd.cisc191.workoutcalendar.model.Lift;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.Set;

public class DayCalendar extends BorderPane {
    private Day day;
    private VBox cardio_display;
    private VBox lifts_display;
    private VBox workout_display;
    String border_color;


    public DayCalendar(Day day, int pref_height, int pref_width) {
        this.day = day;
        setPrefHeight(pref_height);
        setPrefWidth(pref_width);
        setStyle("-fx-border-color: black");
        border_color = "-fx-border-color: blue";
    }

    public void updateDate() {
        CalendarLabel date = new CalendarLabel();
        date.setPrefHeight(25);
        date.setPrefWidth(25);

        try {
            int day_of_month = day.getDayOfMonth();
            date.setText(String.valueOf(day_of_month));
        } catch (NullPointerException e) {
            date.setText("");
        }

        setTop(date);
    }

    public void updateCardio() {
        cardio_display = new VBox();

        CalendarLabel cardio_label = new CalendarLabel();
        cardio_label.setFont(new Font("Serif", 16));

        HBox stats_container = new HBox();

        CalendarLabel cardio_name = new CalendarLabel();
        CalendarLabel cardio_duration = new CalendarLabel();
        CalendarLabel cardio_calories = new CalendarLabel();

        try {
            if(day.cardio != null) {
                cardio_label.setText("Cardio:");
                Cardio cardio = day.getCardio();
                cardio_name.setText(cardio.getName());
                cardio_duration.setText(cardio.getDuration() + " Min");
                cardio_calories.setText(cardio.getCalories_burned() + " Cal");

                stats_container.setStyle(border_color);
            }
        } catch(NullPointerException e) {
            cardio_name.setText("");
            cardio_duration.setText("");
            cardio_calories.setText("");
        }

        stats_container.getChildren().add(cardio_name);
        stats_container.getChildren().add(cardio_duration);
        stats_container.getChildren().add(cardio_calories);

        cardio_display.getChildren().add(cardio_label);
        cardio_display.getChildren().add(stats_container);

        setLeft(cardio_display);
    }

    public void updateLifts() {
        lifts_display = new VBox();
            if(day != null) {
                DoublyLinkedList<Lift> lifts = day.lifts;
                Set<String> muscles_worked = new HashSet<String>();
                for(int i = 0; i < lifts.getLength(); i++) {
                    muscles_worked.add(lifts.get(i).getMuscle());
                }

                for(String muscle : muscles_worked) {
                    VBox muscle_group = new VBox();

                    CalendarLabel muscle_label = new CalendarLabel();
                    muscle_label.setText(muscle);
                    muscle_label.setFont(new Font("Serif", 16));

                    muscle_group.getChildren().add(muscle_label);

                    for(int i = 0; i < lifts.getLength(); i++) {
                        if (lifts.get(i).getMuscle() == muscle) {
                            Lift lift = lifts.get(i);
                            VBox lift_container = new VBox();
                            HBox stats_container = new HBox();

                            CalendarLabel lift_name = new CalendarLabel();
                            CalendarLabel lift_reps = new CalendarLabel();
                            CalendarLabel lift_sets = new CalendarLabel();
                            CalendarLabel lift_calories = new CalendarLabel();

                            lift_name.setText(lift.getName());
                            lift_reps.setText(lift.getReps() + " Reps");
                            lift_sets.setText(lift.getSets() + " Sets");
                            lift_calories.setText(lift.getCalories_burned() + " Cal");

                            stats_container.getChildren().add(lift_reps);
                            stats_container.getChildren().add(lift_sets);
                            stats_container.getChildren().add(lift_calories);


                            lift_container.getChildren().add(lift_name);
                            lift_container.getChildren().add(stats_container);

                            lift_container.setStyle(border_color);

                            muscle_group.getChildren().add(lift_container);
                        }
                    }
                    lifts_display.getChildren().add(muscle_group);
                }
            }
    }

    public void updateDay() {
        updateDate();
        updateCardio();
        updateLifts();

        workout_display = new VBox();
        workout_display.getChildren().add(cardio_display);
        workout_display.getChildren().add(lifts_display);

        setCenter(workout_display);
    }
}
