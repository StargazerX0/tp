package command;

import exception.LockedFeatureException;
import player.PlayerProfile;

public class SellStockCommand implements Command{
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
