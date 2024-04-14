package command;

import player.PlayerProfile;
import ui.ResponseManager;

/**
 * Represents the command to display the player's company statistics.
 */
public class CompanyStatsCommand implements Command {
    @Override
    public void execute(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(playerProfile.companyInfo());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isAnAction() {
        return false;
    }
}
