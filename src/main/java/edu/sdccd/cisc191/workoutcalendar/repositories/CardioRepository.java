package edu.sdccd.cisc191.workoutcalendar.repositories;

import edu.sdccd.cisc191.workoutcalendar.model.Cardio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardioRepository extends CrudRepository<Cardio, String> {
    Cardio findByName(String name);
}
