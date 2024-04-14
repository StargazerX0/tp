package command;

import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

/**
 * Represents the command to display the player's status.
 */
class CheckStatusCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        indentPrint("Current Status:" + playerProfile.toString());
    }

    public boolean isExit() {
        return false;
    }

    public boolean isAnAction() {
        return false;
    }
}
