// Run() is called from Scheduling.main() and is where
// the scheduling algorithm written by the user resides.
// User modification should occur within the Run() function.

import java.util.Vector;
import java.io.*;

public class BaseAlgorithm extends SchedulingAlgorithm{
  public Results Run(int runtime, Vector processVector, Results result) {
    int i = 0;
    int comptime = 0;
    int currentProcess = 0;
    int previousProcess = 0;
    int size = processVector.size();
    int completed = 0;
    String resultsFile = "Summary-Processes";

    result.schedulingType = "Batch (Nonpreemptive)";
    result.schedulingName = "First-Come First-Served";

    try (var out = new PrintStream(new FileOutputStream(resultsFile))) {
      sProcess process = (sProcess) processVector.elementAt(currentProcess);
      out.println(GetProcessInfoAsString(currentProcess, "registered...", process));

      while (comptime < runtime) {
        if (process.cpudone == process.cputime) {
          completed++;
          out.println(GetProcessInfoAsString(currentProcess, "completed...", process));

          if (completed == size) {
            result.compuTime = comptime;
            return result;
          }
          for (i = size - 1; i >= 0; i--) {
            process = (sProcess) processVector.elementAt(i);
            if (process.cpudone < process.cputime) { 
              currentProcess = i;
            }
          }
          process = (sProcess) processVector.elementAt(currentProcess);
          out.println(GetProcessInfoAsString(currentProcess, "registered...", process));
        }      
        if (process.ioblocking == process.ionext) {
          out.println(GetProcessInfoAsString(currentProcess, "I/O blocked...", process));

          process.numblocked++;
          process.ionext = 0; 
          previousProcess = currentProcess;
          for (i = size - 1; i >= 0; i--) {
            process = (sProcess) processVector.elementAt(i);
            if (process.cpudone < process.cputime && previousProcess != i) { 
              currentProcess = i;
            }
          }
          process = (sProcess) processVector.elementAt(currentProcess);
          out.println(GetProcessInfoAsString(currentProcess, "registered...", process));
        }
        process.cpudone++;       
        if (process.ioblocking > 0) {
          process.ionext++;
        }
        comptime++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    result.compuTime = comptime;
    return result;
  }
}
