package command;

import exception.GameException;
import player.PlayerProfile;

/**
 * Defines the contract for game commands. Commands affect the game state or player's status.
 */
public interface Command {

    /**
     * Executes the command for the given player profile.
     *
     * @param playerProfile The player profile affected by the command.
     * @throws GameException If execution fails due to game logic errors.
     */
    void execute(PlayerProfile playerProfile) throws GameException;

    /**
     * Determines if this command can trigger a random event.
     *
     * @return true if a random event can be triggered, false otherwise.
     */
    boolean canGenerateEvent();

    /**
     * Checks if this command signifies game exit.
     *
     * @return true if this command exits the game, false otherwise.
     */
    boolean isExit();
}
