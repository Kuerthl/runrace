package hu.kuerthl.runrace;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "races")
public class RaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long raceId;
    @Column(name = "race_name", nullable = false)
    private String raceName;
    @Column(name = "distance", nullable = false)
    private int distance;
    @OneToMany(mappedBy = "raceId" )
    private List<ResultEntity> results;

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long id) {
        this.raceId = id;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<ResultEntity> getResults() {
        return results;
    }

    // constructors, getters and setters
}
