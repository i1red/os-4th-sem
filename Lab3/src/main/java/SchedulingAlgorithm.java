import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class SchedulingAlgorithm {
    protected String resultsFilename;
    protected String schedulingName;
    protected String processesFilename;

    protected static String GetProcessInfoAsString(String action, Process process) {
        return String.format(
                "Process: %-5d %-20s (%-5d %-5d %-5d)",
                process.id, action, process.cputime, process.ioblocking, process.cpudone
        );
    }

    protected abstract Results RunImplementation(int runtime, List<Process> processes);

    public Results Run(int runtime, Vector processVector) {
        return this.RunImplementation(runtime, new ArrayList<Process>(processVector));
    }

    public String GetResultsFilename() {
        return this.resultsFilename;
    }

    public String GetSchedulingName() {
        return this.schedulingName;
    }

    public String GetProcessesFilename() {
        return processesFilename;
    }
}
