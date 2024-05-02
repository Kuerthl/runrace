package hu.kuerthl.runrace;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "race")
    private RaceEntity race;

    @ManyToOne
    @JoinColumn(name = "runner")
    private RunnerEntity runner;

    @Column(name = "run_time", nullable = false)
    private int runTime;

    public Long getResultId() {
        return resultId;
    }

    public RaceEntity getRace() {
        return race;
    }

    public RunnerEntity getRunner() {
        return runner;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRace(RaceEntity race) {
        this.race = race;
    }

    public void setRunner(RunnerEntity runner) {
        this.runner = runner;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }
}
