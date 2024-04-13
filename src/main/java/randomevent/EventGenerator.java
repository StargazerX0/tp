package randomevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventGenerator {
    private static final RandomEvent[] eventArray = {
        new PositiveEvent(0.2),
        new NegativeEvent(0.15),
        new LotteryEvent(0.25),
        new DecisionEvent(0.4)
    };

    private static final List<RandomEvent> events = new ArrayList<>(List.of(eventArray));

    /**
     * Returns a random event based on the probability of each event.
     *
     * @return a random event
     */
    public static RandomEvent getRandomEvent() {
        Random random = new Random();
        double totalProbability = events.stream()
                .mapToDouble(RandomEvent::getProbability)
                .sum();
        double randomValue = totalProbability * random.nextDouble();

        for (RandomEvent event : events) {
            randomValue -= event.getProbability();
            if (randomValue <= 0) {
                return event;
            }
        }
        return events.get(events.size() - 1);
    }
}
