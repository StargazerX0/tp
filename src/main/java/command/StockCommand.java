package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import minigame.stockgame.StockActivate;

public class StockCommand implements Command {
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        StockActivate.start(playerProfile);
        System.out.println("StockCommand executed");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
