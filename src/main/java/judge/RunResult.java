package judge;

public class RunResult {
    private final long cpuTime;
    private final long realTime;
    private final long memeory;
    private final int signal;
    private final int exitCode;
    private final int error;
    private final int result;

    public RunResult(long cpuTime, long realTime, long memeory, int signal, int exitCode, int error, int result) {
        this.cpuTime = cpuTime;
        this.realTime = realTime;
        this.memeory = memeory;
        this.signal = signal;
        this.exitCode = exitCode;
        this.error = error;
        this.result = result;
    }
}
