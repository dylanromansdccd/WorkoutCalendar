package edu.sdccd.cisc191.workoutcalendar;

import edu.sdccd.cisc191.workoutcalendar.model.Lift;

import java.util.HashMap;
import java.util.Map;

public class Month {
    private String name;
    private int number_of_days;
    private Day[][] month;
    private int[] dates_array;
    private Day first_day;

    public Month(String name, int number_of_days, Day[][] month, int[] dates_array, Day first_day) {
        this.name = name;
        this.number_of_days = number_of_days;
        this.month = month;
        this.dates_array = dates_array;
        this.first_day = first_day;
    }

    public Month(String name, int number_of_days, Day first_day) {
        this.name = name;
        this.number_of_days = number_of_days;
        this.first_day = first_day;
    }

    public Month(String name, Day[][] month, int[] dates_array) {
        this.name = name;
        this.month = month;
        this.dates_array = dates_array;
    }

    public String getName() {
        return name;
    }

    public Day[][] getMonth() {
        return month;
    }

    /**
     * uses data from the first day to populate the rest of the month with empty day objects
     */
    public void populateMonth() {
        month = new Day[5][7];
        month[0][first_day.getDayOfWeek() - 1] = first_day;

        //counts current day of the month, increases as more days are populated
        int k = 2;

        //populate first week, days before first day are null
        for (int j = first_day.getDayOfWeek(); j < 7; j++) {
            Day day = new Day(j + 1, k);
           month[0][j] = day;
           k++;
        }

        //populate the middle weeks in the month with empty days
        for (int i = 1; i < 4; i++) {
            for(int j = 0; j < 7; j++) {
                Day day = new Day(j + 1, k);
                month[i][j] = day;
                k++;
            }
        }

        //k is used for the loop, so l is created outside the loop and increases as k increases
        int l = 0;
        //populate the last week with empty days, extra days are left null
        for (; k <= number_of_days; k++) {
            Day day = new Day(l + 1, k);
            month[4][l] = day;
            l++;
        }

        dates_array = new int[35];
        int current_index = 0;

        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 7; col++) {
                try {
                    dates_array[current_index] = month[row][col].getDayOfMonth();
                } catch(NullPointerException e) {
                    dates_array[current_index] = 0;
                }
                current_index++;
            }
        }
    }

    /**
     * fills an array with every day's day of the month value
     * @return array wtih ever day_of_month value
     */
    public Day[][] getDays() {
        Day[][] days = new Day[5][7];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                try {
                    days[i][j] = month[i][j];
                } catch (NullPointerException e) {
                    days[i][j] = null;
                }
            }
        }
        return days;
    }

    /**
     * user callable method of indexOf, calls a recrsive method with specified values
     * @param day
     * @return
     */
    public Map<String, Integer> indexOf(int day) {
        Map<String, Integer> pairs = new HashMap<String, Integer>();
        int array_index = indexOf(day, 0, 35);
        int row = array_index / 7;
        int column = array_index % 7;

        pairs.put("row", row);
        pairs.put("col", column);

        return pairs;
    }

    /**
     *
     * @param day
     * @param high
     * @param low
     * @return index of desired day
     */
    private int indexOf(int day, int low, int high) {
        int mid = low + (high - low) / 2;

        if (day == dates_array[mid]) {
            return mid;
        } else if (day < dates_array[mid]) {
            return indexOf(day, low, mid - 1);
        } else {
            return indexOf(day, mid + 1, high);
        }
    }

    public int getNumber_of_days() {
        return number_of_days;
    }

    /**
     * adds lifts to specific day in month
     * @param pairs
     * @param lift
     */
    public void addLift(Map<String, Integer>pairs, Lift lift) {
        month[pairs.get("row")][pairs.get("col")].addLift(lift);
    }
}
