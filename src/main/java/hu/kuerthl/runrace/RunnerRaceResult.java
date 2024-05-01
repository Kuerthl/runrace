package hu.kuerthl.runrace;

public class RunnerRaceResult {
    public long runnerId;
    public String runnerName;
    public int runTime;

    public RunnerRaceResult(long runnerId, String runnerName, int runTime) {
        this.runnerId = runnerId;
        this.runnerName = runnerName;
        this.runTime = runTime;
    }
}
