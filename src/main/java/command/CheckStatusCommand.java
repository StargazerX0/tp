package command;

import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

class CheckStatusCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        indentPrint("Current Status:" + playerProfile.toString());
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
