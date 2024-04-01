package command;

import exception.GameException;
import player.PlayerProfile;
import minigame.stockgame.StockActivate;

public class StockCommand implements Command {
    public void execute(PlayerProfile playerProfile) throws GameException {
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
