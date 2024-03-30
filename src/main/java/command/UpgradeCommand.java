package command;

import exception.GameException;
import exception.MoneyNotEnoughException;
import player.PlayerProfile;

public class UpgradeCommand implements Command {
    private static final int UPGRADE_COST = 10000;
    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (playerProfile.isAdvancedPlayer()) {
            throw new GameException("You have already upgraded!");
        }
        if (!playerProfile.canUpgrade(UPGRADE_COST)) {
            throw new MoneyNotEnoughException();
        }

        playerProfile.upgrade();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
