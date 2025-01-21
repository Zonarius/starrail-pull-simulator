package io.github.zonarius.hsrpullsimulator;

import java.util.random.RandomGenerator;

public class Account {
    private long totalPulls;
    private long fourStarsPulled;
    private long fiveStarsPulled;
    private long limitedFiveStarsPulled;
    private int pityTimer;
    private boolean guaranteedLimited;
    private final RandomGenerator rng = RandomGenerator.getDefault();

    public PullResult pull() {
        totalPulls++;
        if (rng.nextDouble() < getChance()) { // 5-star?
            if (guaranteedLimited) {
                return limitedFiveStarPull();
            } else {
                if (rng.nextDouble() < 0.58) { // limited?
                    return limitedFiveStarPull();
                } else {
                    return fiveStarPull();
                }
            }
        } else {
            return fourStarPull();
        }
    }

    private double getChance() {
        int currentPull = pityTimer + 1;
        return switch (currentPull) {
            case 74 -> 0.066;
            case 75 -> 0.126;
            case 76 -> 0.186;
            case 77 -> 0.246;
            case 78 -> 0.306;
            case 79 -> 0.366;
            case 80 -> 0.426;
            case 81 -> 0.486;
            case 82 -> 0.526;
            case 83 -> 0.586;
            case 84 -> 0.646;
            case 85 -> 0.706;
            case 86 -> 0.766;
            case 87 -> 0.826;
            case 88 -> 0.886;
            case 89 -> 0.946;
            case 90 -> 1;
            default -> 0.006;
        };
    }

    private PullResult limitedFiveStarPull() {
        pityTimer = 0;
        guaranteedLimited = false;
        limitedFiveStarsPulled++;
        return PullResult.LIMITED_FIVE_STAR;
    }

    private PullResult fiveStarPull() {
        pityTimer = 0;
        guaranteedLimited = true;
        fiveStarsPulled++;
        return PullResult.FIVE_STAR;
    }

    private PullResult fourStarPull() {
        pityTimer++;
        fourStarsPulled++;
        return PullResult.FOUR_STAR;
    }

    public long fourStarsPulled() {
        return fourStarsPulled;
    }

    public long fiveStarsPulled() {
        return fiveStarsPulled;
    }

    public long limitedFiveStarsPulled() {
        return limitedFiveStarsPulled;
    }

    public int fourStarPulls() {
        return pityTimer;
    }

    public boolean guaranteedLimited() {
        return guaranteedLimited;
    }

    public long totalPulls() {
        return totalPulls;
    }
}
