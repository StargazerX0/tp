package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import minigame.bondgame.BondActivate;

public class BuyBondCommand implements Command {
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        BondActivate.start(playerProfile);
        System.out.println("BuyBondCommand executed");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
