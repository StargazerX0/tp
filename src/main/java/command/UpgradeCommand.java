package command;

import exception.GameException;
import exception.MoneyNotEnoughException;
import player.PlayerProfile;
import ui.ResponseManager;

public class UpgradeCommand implements Command {
    private static final int UPGRADE_COST = 10000;
    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (playerProfile.isAdvancedPlayer()) {
            throw new GameException("You have already upgraded your player.\n");
        }
        if (!playerProfile.canUpgrade(UPGRADE_COST)) {
            throw new MoneyNotEnoughException("you need at least $10000 to upgrade your player.\n");
        }
        playerProfile.upgrade();
        ResponseManager.indentPrint("You have successfully upgraded your player!\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean canGenerateEvent() {
        return false;
    }
}
