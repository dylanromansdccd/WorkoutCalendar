package edu.sdccd.cisc191.workoutcalendar;

import edu.sdccd.cisc191.workoutcalendar.model.Cardio;
import edu.sdccd.cisc191.workoutcalendar.model.Lift;
import edu.sdccd.cisc191.workoutcalendar.services.CardioService;
import edu.sdccd.cisc191.workoutcalendar.services.LiftService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class WorkoutCalendarApplication extends Application {
    private static Month month;
    int day_height;
    int day_width;

    public ConfigurableApplicationContext springContext;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SaveToFile save = new SaveToFile();
        boolean setup = true;

        do {
            System.out.println("Read From File y/n?");
            String answer = scanner.nextLine();

            if(Objects.equals(answer, "y")) {
                System.out.println("Enter File Name (include txt)");
                String filename = scanner.nextLine();

                System.out.println("Enter Month Name");
                String month_name = scanner.nextLine();

                System.out.println("Enter Month # of Days");
                String month_length = scanner.nextLine();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filename));
                    String json = reader.readLine();

                    Day day = save.jsonToDay(json);

                    month = new Month(month_name, Integer.parseInt(month_length), day);

                    setup = false;
                    break;
                } catch(FileNotFoundException e) {
                    System.out.println("File Not Found :c");
                } catch(Exception e) {
                    System.out.println("Incorrect File :c");
                }
            } else if(Objects.equals(answer, "n")) {
                System.out.println("Enter Month Name");
                String month_name = scanner.nextLine();

                System.out.println("Enter Month # of Days");
                String month_length = scanner.nextLine();

                System.out.println("Enter First Day of Month (1 - 7)");
                String first_day = scanner.nextLine();

                System.out.println("Enter File Name");
                String filename = scanner.nextLine();

                try {
                    Day day = new Day(Integer.parseInt(first_day), 1);
                    month = new Month(month_name, Integer.parseInt(month_length), day);

                    File file = new File(filename);
                    if (file == null) {
                        file.createNewFile();
                    }

                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

                    String json = save.dayToJson(day);

                    writer.write(json);
                    writer.close();

                    setup = false;
                    break;

                    } catch (Exception e) {
                    System.out.println("Invalid Values");
                }
            }
        } while(setup == true);

        launch(WorkoutCalendarApplication.class, args);
    }

    @Override
    public void stop() throws Exception {
        springContext = SpringApplication.run(WorkoutCalendarApplication.class);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(WorkoutCalendarApplication.class);

        LiftService liftService =  springContext.getBean(LiftService.class);
        CardioService cardioService = springContext.getBean(CardioService.class);

        Lift bench = new Lift("Bench", 100, "Chest", 8, 4);
        Lift incline_bench = new Lift("Incline Bench", 100, "Chest", 8, 3);
        Lift chest_fly = new Lift("Chest Fly", 94, "Chest", 8, 4);
        Lift tricep_pushdown = new Lift("Tricep Pushdown", 70, "Triceps", 10, 4);
        Lift skull_crushers = new Lift("Skull Crushers", 30, "Triceps", 10, 4);

        Lift row = new Lift("Barbell Row", 102, "Back", 8, 4);
        Lift cable_row = new Lift("Cable Row", 94, "Back", 8, 3);
        Lift pulldown = new Lift("Lat Pulldown", 102, "Back", 8, 3);
        Lift curls = new Lift("Curls", 70, "Biceps", 10, 4);
        Lift reverse_curls = new Lift("Reverse Curls", 70, "Forearms", 10, 4);

        Lift squat = new Lift("Squat", 147, "Legs", 7, 4);
        Lift deadliift = new Lift("Deadlift", 152, "Legs", 5, 4);
        Lift lunges = new Lift("Lunges", 138, "Legs", 7, 4);
        Lift shoulder_press = new Lift("Shoulder Press", 70, "Shoulders", 10, 4);
        Lift lateral_raises = new Lift("Lateral Raises", 70, "Shoulders", 10, 4);

        Lift lifts[] = {bench, incline_bench, chest_fly, tricep_pushdown, skull_crushers,
                row, cable_row, pulldown, curls, reverse_curls,
        squat, deadliift, lunges, shoulder_press, lateral_raises};

        for(int i = 0; i < lifts.length; i++) {
            liftService.save(lifts[i]);
        }

        Cardio run = new Cardio("Run", 360, 30);
        Cardio stairs = new Cardio("Stairs", 325, 30);

        Cardio cardio[] = {run, stairs};

        for(int i = 0; i < cardio.length; i++) {
            cardioService.save(cardio[i]);
        }
    }

    public VBox fillCalendar() {
        VBox vbox = new VBox();

        //fill middle of BorderPane with 2d array of days
        for (int row = 0; row < 5; row++) {
            HBox hbox = new HBox();

            for (int col = 0; col < 7; col++) {
                Day day = month.getDays()[row][col];

                DayCalendar day_container = new DayCalendar(day, day_height, day_width);

                day_container.updateDay();

                hbox.getChildren().add(day_container);

            }
            vbox.getChildren().add(row, hbox);
        }

        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        LiftService liftService = springContext.getBean(LiftService.class);
        CardioService cardioService = springContext.getBean(CardioService.class);

        month.populateMonth();


        BorderPane layout = new BorderPane();
        layout.setPrefHeight(750);
        ScrollPane root = new ScrollPane();
        root.setContent(layout);

        day_height = 300;
        day_width = 250;

        HBox header = new HBox();

        //display days of the week in header
        String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < 7; i++) {
            CalendarLabel day_label = new CalendarLabel();
            day_label.setText(days[i]);
            day_label.setPrefWidth(day_width);
            day_label.setStyle("-fx-border-color: black");

            header.getChildren().add(day_label);
        }

        layout.setTop(header);

        VBox vbox = fillCalendar();

        //add workouts to days
        VBox bottom = new VBox();

        HBox workout_search_container = new HBox();

        CalendarLabel workout_search_label = new CalendarLabel();
        workout_search_label.setText("Add Workout:");

        TextField workout_search = new TextField();

        workout_search_container.getChildren().add(workout_search_label);
        workout_search_container.getChildren().add(workout_search);
        bottom.getChildren().add(workout_search_container);

        HBox day_search_container = new HBox();

        CalendarLabel day_search_label = new CalendarLabel();
        day_search_label.setText("Select Day:");

        TextField day_search = new TextField();

        day_search_container.getChildren().add(day_search_label);
        day_search_container.getChildren().add(day_search);

        bottom.getChildren().add(day_search_container);

        Button button = new Button("Add Workout");

        bottom.getChildren().add(button);

        layout.setBottom(bottom);

        button.setOnAction(e -> {
            String workout_name = String.valueOf(workout_search.getText());
            try {
                int day = Integer.valueOf(day_search.getText());
                if (
                        (day < 1) || (day > month.getNumber_of_days())) {
                    throw new Exception("f");
                } else {
                    Map<String, Integer> pairs = month.indexOf(day);

                    Lift lift = liftService.findByName(workout_name);

                    if(lift != null) {
                        month.addLift(pairs, lift);
                    }
                }
            } catch (Exception f) {
                System.err.println("Enter a valid value");
                workout_search.clear();
                day_search.clear();
            }

            VBox update = fillCalendar();

            layout.setCenter(update);
            stage.show();
        });

        //create scene, stage, set title, and show
        layout.setCenter(vbox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(month.getName());
        stage.setResizable(true);
        stage.show();
    }

}
