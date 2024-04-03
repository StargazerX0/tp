package command;

import player.PlayerProfile;
import ui.ResponseManager;

public class CompanyStatsCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(playerProfile.companyInfo());
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
