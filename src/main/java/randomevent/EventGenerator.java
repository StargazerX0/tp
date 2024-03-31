package randomevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventGenerator {
    private List<RandomEvent> events;
    private Random random;

    public EventGenerator(List<RandomEvent> events) {
        this.events = events;
        this.random = new Random();
    }

    public RandomEvent getRandomEvent() {
        double totalProbability = this.events.stream().mapToDouble(RandomEvent::getProbability).sum();
        double randomValue = totalProbability * random.nextDouble();

        for (RandomEvent event : this.events) {
            randomValue -= event.getProbability();
            if (randomValue <= 0) {
                return event;
            }
        }
        throw new IllegalStateException("No event was generated.");
    }
}
