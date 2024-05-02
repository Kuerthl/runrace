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
        runner1.setRunnerSex(RunRaceApplication.Sex.male);

        runnerRepository.save(runner1);

        RunnerEntity runner2 = new RunnerEntity();
        runner2.setRunnerName("Zsuzsi");
        runner2.setRunnerAge(29);
        runner2.setRunnerSex(RunRaceApplication.Sex.female);

        runnerRepository.save(runner2);

        RunnerEntity runner3 = new RunnerEntity();
        runner3.setRunnerName("Bandi");
        runner3.setRunnerAge(45);
        runner3.setRunnerSex(RunRaceApplication.Sex.male);

        runnerRepository.save(runner3);

        RunnerEntity runner4 = new RunnerEntity();
        runner4.setRunnerName("Ildi");
        runner4.setRunnerAge(37);
        runner4.setRunnerSex(RunRaceApplication.Sex.female);

        runnerRepository.save(runner4);

        RaceEntity race1 = new RaceEntity();
        race1.setRaceName("Kis Klepács");
        race1.setDistance(5);

        raceRepository.save(race1);

        RaceEntity race2 = new RaceEntity();
        race2.setRaceName("Nagy Klepács");
        race2.setDistance(10);

        raceRepository.save(race2);

        ResultEntity result1 = new ResultEntity();
        result1.setRace(race1);
        result1.setRunner(runner1);
        result1.setRunTime(7);

        resultRepository.save(result1);

        ResultEntity result2 = new ResultEntity();
        result2.setRace(race1);
        result2.setRunner(runner2);
        result2.setRunTime(9);

        resultRepository.save(result2);

        ResultEntity result3 = new ResultEntity();
        result3.setRace(race1);
        result3.setRunner(runner3);
        result3.setRunTime(13);

        resultRepository.save(result3);

        ResultEntity result4 = new ResultEntity();
        result4.setRace(race2);
        result4.setRunner(runner2);
        result4.setRunTime(6);

        resultRepository.save(result4);

        ResultEntity result5 = new ResultEntity();
        result5.setRace(race2);
        result5.setRunner(runner3);
        result5.setRunTime(11);

        resultRepository.save(result5);

        ResultEntity result6 = new ResultEntity();
        result6.setRace(race2);
        result6.setRunner(runner4);
        result6.setRunTime(20);

        resultRepository.save(result6);
    }
}

