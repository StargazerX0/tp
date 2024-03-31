package randomevent;

import player.PlayerProfile;

public abstract class RandomEvent {
    private final double probability;

    public RandomEvent(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return this.probability;
    }

    // Your event-specific logic here
    public abstract void triggerEvent(PlayerProfile playerProfile);
}
