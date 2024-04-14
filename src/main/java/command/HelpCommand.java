package command;

import player.PlayerProfile;
import ui.ResponseManager;

public class HelpCommand implements Command {
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
