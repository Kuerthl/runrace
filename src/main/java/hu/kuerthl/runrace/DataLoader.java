package hu.kuerthl.runrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public DataLoader(RunnerRepository runnerRepository, RaceRepository raceRepository, ResultRepository resultRepository) {
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public void run(String... args) {
        // create default runner entity
        RunnerEntity runner1 = new RunnerEntity();
        runner1.setRunnerName("Tomi");
        runner1.setRunnerAge(31);

        runnerRepository.save(runner1);

        RunnerEntity runner2 = new RunnerEntity();
        runner2.setRunnerName("Zsuzsi");
        runner2.setRunnerAge(29);

        runnerRepository.save(runner2);

        RaceEntity race1 = new RaceEntity();
        race1.setRaceName("Kis Klepács");
        race1.setDistance(5);

        RaceEntity race2 = new RaceEntity();
        race2.setRaceName("Nagy Klepács");
        race2.setDistance(10);

        raceRepository.save(race1);
    }
}

