package edu.sdccd.cisc191.workoutcalendar;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class CalendarLabel extends Label {
    public static Insets LABEL_PADDING = new Insets(5);

    public CalendarLabel() {
        setPadding(LABEL_PADDING);
    }
}
