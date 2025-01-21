package party.zonarius.hsr.pullsimulator;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {
    private static AtomicInteger millions = new AtomicInteger();
    public static void main(String[] args) {
        PullAggregation pullAggregation = IntStream.range(0, 100)
            .parallel()
            .mapToObj(i -> aggregatePulls(1_000_000))
            .reduce(PullAggregation::add)
            .orElseThrow();

        System.out.println(pullAggregation);
    }

    private static PullAggregation aggregatePulls(long accounts) {
        PullAggregation pullAggregation = new PullAggregation();
        for (long i = 0; i < accounts; i++) {
            pullAggregation.inc(pullsForLimitedFiveStar());
        }
        System.out.println(millions.addAndGet(1) + " millions");
        return pullAggregation;
    }

    private static int pullsForLimitedFiveStar() {
        Account account = new Account();
        while (account.limitedFiveStarsPulled() < 1) {
            account.pull();
        }
        return (int) account.totalPulls();
    }
}