package hu.kuerthl.runrace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

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
    private Set<ResultEntity> results;

    public Long getId() {
        return raceId;
    }

    public void setId(Long id) {
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

    public void setDistance(int Distance) {
        this.distance = distance;
    }

    public Set<ResultEntity> getResults() {
        return results;
    }

    // constructors, getters and setters
}
