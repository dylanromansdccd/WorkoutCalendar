package edu.sdccd.cisc191.workoutcalendar.services;

import edu.sdccd.cisc191.workoutcalendar.model.Lift;
import edu.sdccd.cisc191.workoutcalendar.repositories.LiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiftService {
    @Autowired
    LiftRepository liftRepository;

    public List<Lift> findAll() {
        return (List<Lift>) liftRepository.findAll();
    }


    public Lift save(Lift lift) {
        return liftRepository.save(lift);
    }

    public Lift findByName(String name) {
        return liftRepository.findByName(name);
    }
}
