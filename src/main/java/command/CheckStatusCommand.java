package command;

import player.PlayerProfile;
import ui.ResponseManager;

public class CheckStatusCommand implements Command {
    public void execute(PlayerProfile profile) {
        ResponseManager.indentPrint(
                "Current Status:\n" + profile.toString() + "\n");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
