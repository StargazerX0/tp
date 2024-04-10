package randomevent;

import exception.CommandInputException;
import player.PlayerProfile;

/**
 * Represents the base class for all random events in the game. Each event has a certain probability
 * of occurring and must be able to be triggered, affecting the player's profile in various ways.
 */
public abstract class RandomEvent {
    private final double probability;

    /**
     * Constructs a random event with a specified probability.
     *
     * @param probability The likelihood of the event happening, expressed as a decimal.
     */
    public RandomEvent(double probability) {
        this.probability = probability;
    }

    /**
     * Gets the probability of the event.
     *
     * @return The probability of the event.
     */
    public double getProbability() {
        return this.probability;
    }

    /**
     * Triggers the event, applying its effects to the given player profile.
     * Implementations of this method in subclasses define specific event behaviors.
     *
     * @param playerProfile The player profile affected by the event.
     * @throws CommandInputException If an issue occurs during the event's execution.
     */
    public abstract void triggerEvent(PlayerProfile playerProfile) throws CommandInputException;
}
