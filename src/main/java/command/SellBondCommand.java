package command;

import exception.LockedFeatureException;
import player.PlayerProfile;

public class SellBondCommand implements Command {
    public void execute(PlayerProfile playerProfile) throws LockedFeatureException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        playerProfile.getAsset().sellBond();
        System.out.println("SellBondsCommand executed");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
