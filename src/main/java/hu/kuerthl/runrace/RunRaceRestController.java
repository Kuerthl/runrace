package hu.kuerthl.runrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/races")
public class RunRaceRestController {

    @Autowired
    private RaceRepository raceRepository;
    private RunnerRepository runnerRepository;
    private ResultRepository resultRepository;
    private RaceController raceController;

    @Autowired
    public RunRaceRestController(RunnerRepository runnerRepository, RaceRepository raceRepository, ResultRepository resultRepository, RaceController raceController) {
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
        this.raceController = raceController;
    }

    @GetMapping("/{id}")
    public RunnerEntity getRunner(@PathVariable Long id) {
        return runnerRepository.findById(id).orElse(null);
    }

    @GetMapping("/getAverageTime/{id}")
    public double getAverageTime(@PathVariable Long id) {
        double averageRunTime = 0;
        RaceEntity race = raceRepository.findById(id).orElse(null);
        if (race != null) {
            List<ResultEntity> raceResults = resultRepository.findByRace(race);
            int runTimeSum = 0;
            for (ResultEntity i : raceResults) {
                runTimeSum += i.getRunTime();
            }
            averageRunTime = (double) runTimeSum / raceResults.size();
        }
        return averageRunTime;
    }

    @GetMapping("")
    public List<RaceEntity> getAllRaces() {
        return raceRepository.findAll();
    }

    public static class AddRaceRequest{
        private String name;
        private int distance;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }

    @PostMapping("/addRace")
    public ResponseEntity addRace(@RequestBody AddRaceRequest addRaceRequest) {
        raceController.addRaceInternal(addRaceRequest.getName(),addRaceRequest.getDistance());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getResults")
    public List<ResultEntity> getAllResults() {
        return resultRepository.findAll();
    }

    @GetMapping("/getRunners")
    public List<RunnerEntity> getAllRunners() {
        return runnerRepository.findAll();
    }

    public static class AddRunnerRequest{
        private String name;
        private int age;
        private RunRaceApplication.Sex sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public RunRaceApplication.Sex getSex() {
            return sex;
        }

        public void setSex(RunRaceApplication.Sex sex) {
            this.sex = sex;
        }
    }

    @PostMapping("/addRunner")
    public ResponseEntity addRace(@RequestBody AddRunnerRequest addRunnerRequest) {
        RunnerEntity runnerEntity = new RunnerEntity();
        runnerEntity.setRunnerName(addRunnerRequest.getName());
        runnerEntity.setRunnerAge(addRunnerRequest.getAge());
        runnerEntity.setRunnerSex(addRunnerRequest.getSex());
        runnerRepository.save(runnerEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getRaceRunners/{id}")
    public List<RunnerRaceResult> getAllRunners(@PathVariable Long id) {
        List<RunnerRaceResult> runnersRaceResult = new ArrayList<>();
        RaceEntity race = raceRepository.findById(id).orElse(null);
        if (race != null) {
            runnersRaceResult = raceController.getRaceByIdInternal(race);
        }
        return runnersRaceResult;
    }

    public static class UpdateRaceRequest{
        private Long id;
        private String name;
        private int distance;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @PostMapping("/updateRace")
    public ResponseEntity updateRace(@RequestBody UpdateRaceRequest updateRaceRequest) {
        RaceEntity raceEntity = raceRepository.findById(updateRaceRequest.getId()).orElse(null);
        if (raceEntity != null) {
            raceEntity.setRaceName(updateRaceRequest.getName());
            raceEntity.setDistance(updateRaceRequest.getDistance());
            raceRepository.save(raceEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Race with ID " + updateRaceRequest.getId() + " not found");
        }
    }

    public static class AddResultRequest {
        private Long raceId;
        private Long runnerId;
        private int runTime;

        public Long getRaceId() {
            return raceId;
        }

        public void setRaceId(Long raceId) {
            this.raceId = raceId;
        }

        public Long getRunnerId() {
            return runnerId;
        }

        public void setRunnerName(Long runnerId) {
            this.runnerId = runnerId;
        }

        public int getRunTime() {
            return runTime;
        }

        public void setRunTime(int runTime) {
            this.runTime = runTime;
        }
    }

    @PostMapping("/addResult")
    public ResponseEntity addRace(@RequestBody AddResultRequest addResultRequest) {
        RaceEntity race = raceRepository.findById(addResultRequest.getRaceId()).orElse(null);
        if (race != null) {
            RunnerEntity runner = runnerRepository.findById(addResultRequest.getRunnerId()).orElse(null);
            if (runner != null) {
                List<ResultEntity> raceResults = resultRepository.findByRace(race);
                for (ResultEntity i : raceResults) {
                    if(i.getRunner().getRunnerId() == runner.getRunnerId()){
                        i.setRunTime(addResultRequest.getRunTime());
                        resultRepository.save(i);
                        break;
                    }
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Runner with ID " + addResultRequest.getRunnerId() + " not found");
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Race with ID " + addResultRequest.getRaceId() + " not found");
        }
    }
}
