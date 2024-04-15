package command;

import exception.GameException;
import exception.MoneyNotEnoughException;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;

public class UpgradeCommand implements Command {
    /**
     * {@inheritDoc}
     *
     * Upgrades the player's profile if the player has not upgraded before and has enough money.
     *
     * @param playerProfile the player profile to be updated.
     * @throws GameException if the player has already upgraded before or does not have enough money.
     */
    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (playerProfile.isAdvancedPlayer()) {
            throw new GameException("You have already upgraded your player.\n");
        }
        if (!playerProfile.canUpgrade()) {
            throw new MoneyNotEnoughException("you need at least $10000 to upgrade your player.\n" +
                    "You currently have $" + playerProfile.getAsset().outputMoney() + ".\n");
        }
        playerProfile.upgrade();
        indentPrint("You have successfully upgraded your player!\n");
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
