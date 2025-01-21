package party.zonarius.hsr.pullsimulator;

import org.junit.jupiter.api.Test;

import java.util.random.RandomGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static party.zonarius.hsr.pullsimulator.EntityRarity.FOUR_STAR;

class EntityPullSimulatorTest {
    private final RandomGenerator badRng = new RandomGenerator() {
        @Override
        public long nextLong() {
            return 0;
        }

        @Override
        public double nextDouble() {
            return 1;
        }
    };
    @Test
    void guaranteedFiveStar() {
        PullResult fiveStarResult = new PullResult(PullEntity.CHARACTER, EntityRarity.FIVE_STAR);

        EntityPullSimulator simulator = new EntityPullSimulator(badRng, PullEntity.CHARACTER);
        assertThat(simulator.warp(89)).allSatisfy(c -> assertThat(c).isNotEqualTo(fiveStarResult));
        assertThat(simulator.warp()).isEqualTo(fiveStarResult);
    }

    @Test
    void guaranteedPromoFiveStar() {
        PullResult fiveStarResult = new PullResult(PullEntity.CHARACTER, EntityRarity.PROMO_FIVE_STAR);

        EntityPullSimulator simulator = new EntityPullSimulator(badRng, PullEntity.CHARACTER);
        assertThat(simulator.warp(179)).allSatisfy(c -> assertThat(c).isNotEqualTo(fiveStarResult));
        assertThat(simulator.warp()).isEqualTo(fiveStarResult);
    }

    @Test
    void guaranteedFourStar() {
        EntityPullSimulator simulator = new EntityPullSimulator(badRng, PullEntity.CHARACTER);
        assertThat(simulator.warp(9)).allSatisfy(c -> assertThat(c.rarity()).isNotEqualTo(FOUR_STAR));
        assertThat(simulator.warp().rarity()).isEqualTo(FOUR_STAR);
    }

    @Test
    void guaranteedPromoFourStar() {
        PullResult fourStarResult = new PullResult(PullEntity.CHARACTER, EntityRarity.PROMO_FOUR_STAR);

        EntityPullSimulator simulator = new EntityPullSimulator(badRng, PullEntity.CHARACTER);
        assertThat(simulator.warp(19)).allSatisfy(c -> assertThat(c).isNotEqualTo(fourStarResult));
        assertThat(simulator.warp()).isEqualTo(fourStarResult);
    }
}