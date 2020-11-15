public class Process {
    public static int createdProcessesCount = 0;

    public int id;
    public int priority;
    public int cputime;
    public int ioblocking;
    public int cpudone;
    public int ionext;
    public int numblocked;

    public Process(int priority, int cputime, int ioblocking, int cpudone, int ionext, int numblocked) {
        this.id = createdProcessesCount++;
        this.priority = priority;
        this.cputime = cputime;
        this.ioblocking = ioblocking;
        this.cpudone = cpudone;
        this.ionext = ionext;
        this.numblocked = numblocked;
    }
}
