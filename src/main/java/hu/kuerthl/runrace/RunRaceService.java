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

/*    public double getAverageLaptime(Long runnerId) {
        RunnerEntity runner = raceRepository.findById(runnerId).orElse(null);
        if (runner != null) {
            List<LapTimeEntity> laptimes = runner.getLaptimes();
            int totalTime = 0;
            for (LapTimeEntity laptime : laptimes) {
                totalTime += laptime.getTimeSeconds();
            }
            return (double) totalTime / laptimes.size();
        } else {
            // handle error when runner is not found
            return -1.0;
        }
    }*/
}
