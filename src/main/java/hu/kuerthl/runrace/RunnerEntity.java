package hu.kuerthl.runrace;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "runners")
public class RunnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long runnerId;
    private String runnerName;
    private int runnerAge;
    @Enumerated(STRING)
    private RunRaceApplication.Sex runnerSex;
    @OneToMany(mappedBy = "runnerId" )
    private Set<ResultEntity> results;

    public RunnerEntity() {
    }

    public long getRunnerId() {
        return runnerId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public int getRunnerAge() {
        return runnerAge;
    }

    public RunRaceApplication.Sex getRunnerSex() {
        return runnerSex;
    }

    public void setRunnerId(long runnerId) {
        this.runnerId = runnerId;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public void setRunnerAge(int runnerAge) {
        this.runnerAge = runnerAge;
    }

    public void setRunnerSex(RunRaceApplication.Sex runnerSex) {
        this.runnerSex = runnerSex;
    }

    public Set<ResultEntity> getResults() {
        return results;
    }
}
