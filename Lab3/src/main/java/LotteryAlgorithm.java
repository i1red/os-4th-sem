// Run() is called from Scheduling.main() and is where
// the scheduling algorithm written by the user resides.
// User modification should occur within the Run() function.

import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class LotteryAlgorithm extends SchedulingAlgorithm {
    protected int lotteryInterval = 20;
    protected int currentProcessIndex;
    protected Integer blockedProcessIndex;
    protected List<Process> uncompletedProcesses;

    public LotteryAlgorithm() {
        this.schedulingName = "Lottery";
        this.resultsFilename = "Lottery-Results";
        this.processesFilename = "Lottery-Processes";
    }

    protected void UpdateUncompletedProcesses() {
        this.uncompletedProcesses.remove(this.currentProcessIndex);
    }

    protected void InitVariables() {
        this.blockedProcessIndex = null;
        this.currentProcessIndex = this.GetNextProcessIndex();
    }

    protected int GetNextProcessIndex() {
        if (uncompletedProcesses.size() >= 2) {
            return this.GetRandIntWithExclusion(uncompletedProcesses.size(), this.blockedProcessIndex);
        }

        return 0;
    }

    private Integer GetRandIntWithExclusion(int end, Integer excludedInt) {
        int adjustedEnd = end;
        boolean exclude = false;

        if (excludedInt != null && 0 <= excludedInt && excludedInt < end) {
            --adjustedEnd;
            exclude = true;
        }

        if (adjustedEnd <= 0) {
            return null;
        }

        int randInt = new Random().nextInt(adjustedEnd);

        return exclude && randInt >= excludedInt ? randInt + 1 : randInt;
    }


    @Override
    protected Results RunImplementation(int runtime, List<Process> processes) {
        this.uncompletedProcesses = new ArrayList<Process>(processes);

        this.InitVariables();
        int computationTime = 0;
        int timeToNextLottery = lotteryInterval;

        Process currentProcess = this.uncompletedProcesses.get(currentProcessIndex);

        try (var out = new PrintStream(new FileOutputStream(this.GetProcessesFilename()))) {
            out.println(GetProcessInfoAsString("registered...", currentProcess));

            while (computationTime < runtime) {
                if (this.uncompletedProcesses.size() >= 2 && timeToNextLottery == 0) {
                    Process previousProcess = currentProcess;

                    this.currentProcessIndex = GetNextProcessIndex();
                    currentProcess = this.uncompletedProcesses.get(this.currentProcessIndex);

                    if (previousProcess.id != currentProcess.id) {
                        out.println(GetProcessInfoAsString("unregistered...", previousProcess));
                        out.println(GetProcessInfoAsString("registered...", currentProcess));
                    }
                    timeToNextLottery = lotteryInterval;
                }

                if (currentProcess.ioblocking == currentProcess.ionext) {
                    out.println(GetProcessInfoAsString("I/O blocked...", currentProcess));

                    currentProcess.numblocked++;
                    currentProcess.ionext = 0;

                    this.blockedProcessIndex = this.currentProcessIndex;
                    this.currentProcessIndex = this.GetNextProcessIndex();
                    currentProcess = this.uncompletedProcesses.get(this.currentProcessIndex);

                    timeToNextLottery = this.lotteryInterval;

                    out.println(GetProcessInfoAsString("registered...", currentProcess));
                }

                currentProcess.cpudone++;
                if (currentProcess.ioblocking > 0) {
                    currentProcess.ionext++;
                }

                timeToNextLottery = Math.max(timeToNextLottery - 1, 0);
                computationTime++;

                if (currentProcess.cpudone == currentProcess.cputime) {
                    this.UpdateUncompletedProcesses();

                    out.println(GetProcessInfoAsString("completed...", currentProcess));

                    if (this.uncompletedProcesses.isEmpty()) {
                        break;
                    }

                    this.currentProcessIndex = this.GetNextProcessIndex();
                    currentProcess = this.uncompletedProcesses.get(this.currentProcessIndex);

                    timeToNextLottery = this.lotteryInterval;

                    out.println(GetProcessInfoAsString("registered...", currentProcess));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Results(null, this.GetSchedulingName(), computationTime);
    }
}
