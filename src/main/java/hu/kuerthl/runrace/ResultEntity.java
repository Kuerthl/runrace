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
    @JoinColumn(name = "race_id")
    private RaceEntity raceId;

    @ManyToOne
    @JoinColumn(name = "runner_id")
    private RunnerEntity runnerId;

    @Column(name = "run_time", nullable = false)
    private int runTime;

    public Long getResultId() {
        return resultId;
    }

    public RaceEntity getRaceId() {
        return raceId;
    }

    public RunnerEntity getRunnerId() {
        return runnerId;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRaceId(RaceEntity raceId) {
        this.raceId = raceId;
    }

    public void setRunnerId(RunnerEntity runnerId) {
        this.runnerId = runnerId;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }
}
