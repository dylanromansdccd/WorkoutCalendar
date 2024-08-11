package edu.sdccd.cisc191.workoutcalendar.services;

import edu.sdccd.cisc191.workoutcalendar.model.Cardio;
import edu.sdccd.cisc191.workoutcalendar.repositories.CardioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardioService {
    @Autowired
    CardioRepository cardioRepository;

    public List<Cardio> findAll() {
        return (List<Cardio>) cardioRepository.findAll();
    }

    public Cardio save(Cardio cardio) {
        return cardioRepository.save(cardio);
    }
}
