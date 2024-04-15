package command;

import exception.LockedFeatureException;
import player.PlayerProfile;

public class SellStockCommand implements Command{
    /**
     * Sells all the stock the player currently possess.
     *
     * @param playerProfile the player profile to be updated.
     * @throws LockedFeatureException if the player have not been upgrade to advanced player.
     */
    @Override
    public void execute(PlayerProfile playerProfile) throws LockedFeatureException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        playerProfile.getAsset().sellStock();
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
