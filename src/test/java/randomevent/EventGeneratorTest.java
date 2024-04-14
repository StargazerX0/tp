package randomevent;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventGeneratorTest {

    @Test
    public void testGetRandomEvent_emptyInput_returnsEventWithRightProbability() {
        int iterations = 10000;
        int positiveCount = 0;
        int negativeCount = 0;
        int lotteryCount = 0;
        int decisionCount = 0;

        for (int i = 0; i < iterations; i++) {
            RandomEvent event = EventGenerator.getRandomEvent();
            if (event instanceof PositiveEvent) {
                positiveCount++;
            } else if (event instanceof NegativeEvent) {
                negativeCount++;
            } else if (event instanceof LotteryEvent) {
                lotteryCount++;
            } else if (event instanceof DecisionEvent) {
                decisionCount++;
            }
        }

        double total = positiveCount + negativeCount + lotteryCount + decisionCount;
        double positivePercent = positiveCount / total;
        double negativePercent = negativeCount / total;
        double lotteryPercent = lotteryCount / total;
        double decisionPercent = decisionCount / total;

        assertTrue(Math.abs(positivePercent - 0.2) < 0.05, "Positive event probability is within range");
        assertTrue(Math.abs(negativePercent - 0.15) < 0.05, "Negative event probability is within range");
        assertTrue(Math.abs(lotteryPercent - 0.25) < 0.05, "Lottery event probability is within range");
        assertTrue(Math.abs(decisionPercent - 0.4) < 0.05, "Decision event probability is within range");
    }
}
