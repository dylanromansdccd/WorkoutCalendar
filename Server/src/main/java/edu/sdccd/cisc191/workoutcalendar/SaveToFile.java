package edu.sdccd.cisc191.workoutcalendar;


import com.google.gson.Gson;

public class SaveToFile {

    public String dayToJson(Day day) {
        Gson gson = new Gson();
        String day_json = gson.toJson(day);
        return day_json;
    }

    public Day jsonToDay(String json) {
        Gson gson = new Gson();
        Day day = gson.fromJson(json, Day.class);
        return day;
    }
}
