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
}
