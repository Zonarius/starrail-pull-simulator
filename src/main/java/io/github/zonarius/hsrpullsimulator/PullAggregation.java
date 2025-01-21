package io.github.zonarius.hsrpullsimulator;

public class PullAggregation {
    private final long[] aggregatePulls = new long[180];

    public void inc(int pulls) {
        aggregatePulls[pulls - 1]++;
    }

    public PullAggregation add(PullAggregation other) {
        for (int i = 0; i < aggregatePulls.length; i++) {
            aggregatePulls[i] += other.aggregatePulls[i];
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (long aggregatePull : aggregatePulls) {
            builder.append(aggregatePull).append("\n");
        }
        return builder.toString();
    }
}
