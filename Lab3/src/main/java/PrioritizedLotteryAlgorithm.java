import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrioritizedLotteryAlgorithm extends LotteryAlgorithm {
    private List<Integer> ticketsCounts;

    public PrioritizedLotteryAlgorithm() {
        this.schedulingName = "PrioritizedLottery";
        this.resultsFilename = "PrioritizedLottery-Results";
        this.processesFilename = "PrioritizedLottery-Processes";
    }

    private void DistributeTickets() {
        this.ticketsCounts = this.uncompletedProcesses.stream().map(process -> process.priority)
                .collect(Collectors.toList());
    }

    @Override
    protected void InitVariables() {
        this.DistributeTickets();
        super.InitVariables();
    }

    @Override
    protected void UpdateUncompletedProcesses() {
        this.uncompletedProcesses.remove(this.currentProcessIndex);
        this.ticketsCounts.remove(this.currentProcessIndex);
    }

    @Override
    protected int GetNextProcessIndex() {
        if (uncompletedProcesses.size() >= 2) {
            int[] indices;
            if (this.blockedProcessIndex == null) {
                indices = IntStream.range(0, this.uncompletedProcesses.size()).toArray();
            }
            else {
                indices = IntStream.range(0, this.uncompletedProcesses.size())
                        .filter(index -> index != this.blockedProcessIndex).toArray();
            }

            double[] probabilities = Arrays.stream(indices)
                    .mapToDouble(index -> this.ticketsCounts.get(index)).toArray();

            int nextProcessIndex = new EnumeratedIntegerDistribution(indices, probabilities).sample();

            this.ticketsCounts.set(nextProcessIndex, Math.max(this.ticketsCounts.get(nextProcessIndex) - 1, 1));

            if (this.ticketsCounts.stream().allMatch(count -> count == 1)) {
                this.DistributeTickets();
            }

            return nextProcessIndex;
        }

        return 0;
    }
}
