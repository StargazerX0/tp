package randomevent;

import exception.CommandInputException;
import player.PlayerProfile;

/**
 * Represents a random event that can happen in the end of a game round.
 */
public abstract class RandomEvent {
    private final double probability;

    public RandomEvent(double probability) {
        this.probability = probability;
    }

    /**
     * Outputs the probability of the event occurring.
     *
     * @return the probability of the event occurring
     */
    public double getProbability() {
        return this.probability;
    }

    /**
     * Triggers the event to happen.
     *
     * @param playerProfile the player's profile that the event will affect
     * @throws CommandInputException if the player's input is invalid
     */
    public abstract void triggerEvent(PlayerProfile playerProfile) throws CommandInputException;
}
