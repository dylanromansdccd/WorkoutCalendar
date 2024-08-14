package edu.sdccd.cisc191.workoutcalendar;

import edu.sdccd.cisc191.workoutcalendar.model.Cardio;
import edu.sdccd.cisc191.workoutcalendar.model.Lift;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WorkoutCalendarApplicationTests {

    @Test
    public void testModule1() {
        Day first_day = new Day(3, 1);
        int[][] test_month = {{0, 0, 1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10, 11, 12,},
                {13, 14, 15, 16, 17, 18, 19,},
                {20, 21, 22, 23, 24, 25, 26,},
                {27, 28, 29, 30, 0, 0, 0,},};

        Month september = new Month("September", 30, first_day);
        september.populateMonth();

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 7; j++) {
                try {
                    assertEquals(september.getDays()[i][j].getDayOfMonth(), test_month[i][j]);
                } catch(NullPointerException e) {
                    assertEquals(0, test_month[i][j]);
                }
            }
        }
    }

    @Test
    public void testModule3() {
        Lift bench = new Lift("Bench", 100, "Chest", 8, 4);
        Lift incline_bench = new Lift("Incline Bench", 100, "Chest", 8, 3);
        Lift chest_fly = new Lift("Chest Fly", 94, "Chest", 8, 4);
        Lift tricep_pushdown = new Lift("Tricep Pushdown", 70, "Triceps", 10, 4);
        Lift skull_crushers = new Lift("Skull Crushers", 30, "Triceps", 10, 4);

        DoublyLinkedList<Lift> lifts = new DoublyLinkedList<>();

        lifts.add(bench);
        lifts.add(incline_bench);
        lifts.add(chest_fly);
        lifts.add(tricep_pushdown);
        lifts.add(skull_crushers);

        Cardio run = new Cardio("Run", 30, 360);

        Day monday = new Day(1, 1, lifts, run);

        assertEquals(monday.getDayOfMonth(), 1);
        assertEquals(monday.getDayOfWeek(), 1);
        assertEquals(monday.getLifts().get(2), chest_fly);
        assertEquals(monday.getCardio(), run);
    }

    @Test
    public void testModule4() throws IOException {
        SaveToFile save = new SaveToFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("september.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("september.txt"));
        } catch(FileNotFoundException e) {
            File myObj = new File("september.txt");
            myObj.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader("september.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("september.txt"));

        Day first_day = new Day(1, 1);

        String json = save.dayToJson(first_day);

        writer.write(json);

        writer.close();

        String new_json = reader.readLine();

        Day day_test = save.jsonToDay(new_json);

        assertEquals(day_test.getDayOfMonth(), first_day.getDayOfMonth());
    }

    @Test
    public void testModule5and7() {
        Day first_day = new Day(3, 1);
        Month september = new Month("September", 30, first_day);
        september.populateMonth();

        assertEquals(september.indexOf(11).get("row"), 1);
        assertEquals(september.indexOf(11).get("col"), 5);
    }

    @Test
    public void testModule6() {
        DoublyLinkedList<Lift> lifts = new DoublyLinkedList<>();

        Lift bench = new Lift("Bench", 100, "Chest", 8, 4);
        Lift incline_bench = new Lift("Incline Bench", 100, "Chest", 8, 3);
        Lift chest_fly = new Lift("Chest Fly", 94, "Chest", 8, 4);
        Lift tricep_pushdown = new Lift("Tricep Pushdown", 70, "Triceps", 10, 4);
        Lift skull_crushers = new Lift("Skull Crushers", 30, "Triceps", 10, 4);

        Lift barbell_row = new Lift("Barbell Row", 102, "Back", 8, 4);

        Lift[] primitive_lifts_array = {bench, incline_bench, barbell_row, tricep_pushdown, skull_crushers};

        lifts.add(primitive_lifts_array);

        assertEquals(lifts.get(2), barbell_row);

        lifts.remove(barbell_row);

        assertEquals(lifts.get(2), tricep_pushdown);

        lifts.add(chest_fly, 2);

        assertEquals(lifts.get(1), incline_bench);
        assertEquals(lifts.get(2), chest_fly);
        assertEquals(lifts.get(3), tricep_pushdown);
    }
}
