package command;

import player.PlayerProfile;
import ui.ResponseManager;

public class HelpCommand implements Command {
    /**
     * Outputs help message based on player's current level.
     *
     * @param playerProfile the player profile to be obtained.
     */
    @Override
    public void execute(PlayerProfile playerProfile) {
        ResponseManager.printHelp();
    }

    public boolean isExit() {
        return false;
    }

    public boolean isAnAction() {
        return false;
    }
}
