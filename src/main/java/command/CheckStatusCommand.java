package command;

import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class CheckStatusCommand implements Command {
    public void execute(PlayerProfile profile) {
        indentPrint("Current Status:\n" + profile.toString() + "\n");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
