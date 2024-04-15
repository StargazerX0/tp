package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import minigame.stockgame.StockActivate;

public class StockCommand implements Command {
    /**
     * Purchases stock of player's choice if the player is advanced player and have enough money.
     *
     * @param playerProfile the player profile to be updated.
     * @throws GameException if the player is not an advanced player or do not have enough money.
     */
    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        StockActivate.start(playerProfile);
        System.out.println("StockCommand executed");
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isAnAction() {
        return true;
    }
}
