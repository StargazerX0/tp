package command;

import exception.GameException;
import exception.MoneyNotEnoughException;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class UpgradeCommand implements Command {
    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (playerProfile.isAdvancedPlayer()) {
            throw new GameException("You have already upgraded your player.\n");
        }
        if (!playerProfile.canUpgrade()) {
            throw new MoneyNotEnoughException("you need at least $10000 to upgrade your player.\n" +
                    "You currently have $" + playerProfile.getAsset().getAsset() + ".\n");
        }
        playerProfile.upgrade();
        indentPrint("You have successfully upgraded your player!\n");
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
