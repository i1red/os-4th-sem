import java.util.Vector;

public abstract class SchedulingAlgorithm {
    protected String GetProcessInfoAsString(int processNo, String action, sProcess process) {
        return String.format(
                "Process: %-5d %-20s (%-5d %-5d %-5d %-5d)",
                processNo, action, process.cputime, process.ioblocking, process.cpudone, process.cpudone
        );
    }

    public abstract Results Run(int runtime, Vector processVector, Results result);
}
