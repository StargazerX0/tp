package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import minigame.stockgame.StockActivate;

public class StockCommand implements Command {
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
