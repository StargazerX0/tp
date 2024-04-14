package command;

import player.PlayerProfile;
import exception.GameException;

/**
 * Represents a command that can be executed based on the user's input.
 */
public interface Command {
    void execute(PlayerProfile playerProfile) throws GameException;

    /**
     * Outputs if the command is counted as an action.
     *
     * @return true if the command is counted as an action, false otherwise.
     */
    boolean isAnAction();

    /**
     * Outputs if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    boolean isExit();
}
