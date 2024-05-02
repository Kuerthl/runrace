package hu.kuerthl.runrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunRaceService {

    private final RaceRepository raceRepository;

    @Autowired
    public RunRaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }
}
