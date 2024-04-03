package command;

import exception.LockedFeatureException;
import player.PlayerProfile;

public class SellStockCommand implements Command{
    public void execute(PlayerProfile playerProfile) throws LockedFeatureException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        playerProfile.getAsset().sellStock();
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
