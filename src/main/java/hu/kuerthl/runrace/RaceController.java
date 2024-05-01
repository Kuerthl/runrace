package hu.kuerthl.runrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RaceController {
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private RunnerRepository runnerRepository;
    @Autowired
    private ResultRepository resultRepository;
    @GetMapping("/races")
    public String getAllRaces(Model model) {
        List<RaceEntity> races = raceRepository.findAll();
        model.addAttribute("races", races);
        return "races";
    }
    @GetMapping("/races/addRace")
    public String showAddRaceForm(Model model) {
        model.addAttribute("addRace", new RaceEntity());
        return "addRace";
    }

    @PostMapping("/races/addRace")
    public String addRace(@ModelAttribute RaceEntity addRace, Model model) {
        addRaceInternal(addRace.getRaceName(),addRace.getDistance());
        return "redirect:/races";
    }

    public Boolean addRaceInternal(String name, int distance){
        Boolean raceAdded = false;
        List<RaceEntity> existingRaces = raceRepository.findByRaceName(name);
        if(existingRaces.isEmpty()) {
            RaceEntity raceEntity = new RaceEntity();
            raceEntity.setRaceName(name);
            raceEntity.setDistance(distance);
            raceRepository.save(raceEntity);
            raceAdded = true;
        }
        return raceAdded;
    }
    @GetMapping("/race/{id}")
    public String getRaceById(@PathVariable Long id, Model model) {
        RaceEntity race = raceRepository.findById(id).orElse(null);
        if (race != null) {
            model.addAttribute("raceName", race.getRaceName());
            List<ResultEntity> raceResults = resultRepository.findByRaceId(race);
            List<RunnerRaceResult> runnersRaceResult= new ArrayList<>();
            for (ResultEntity i:raceResults){
                Optional<RunnerEntity> runner;
                runner = runnerRepository.findById(i.getRunnerId().getRunnerId());
                runnersRaceResult.add(new RunnerRaceResult(runner.get().getRunnerId(),runner.get().getRunnerName(),i.getRunTime()));
            }
            model.addAttribute("result",runnersRaceResult);
            return "race";
        } else {
            return "error";
        }
    }
}
