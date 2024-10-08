package edu.sdccd.cisc191.workoutcalendar.repositories;

import edu.sdccd.cisc191.workoutcalendar.model.Lift;
import org.springframework.data.repository.CrudRepository;

public interface LiftRepository extends CrudRepository<Lift, String> {
    Lift findByName(String name);
}
