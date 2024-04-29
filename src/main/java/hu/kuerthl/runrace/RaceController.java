package hu.kuerthl.runrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RaceController {
    @Autowired
    private RaceRepository raceRepository;
    @GetMapping("/races")
    public String getAllRaces(Model model) {
        List<RaceEntity> races = raceRepository.findAll();
        model.addAttribute("races", races);
        return "races";
    }
    @GetMapping("/races/addRace")
    public Boolean addRace(String name, int distance){
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
}
