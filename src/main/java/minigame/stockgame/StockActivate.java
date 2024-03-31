package minigame.stockgame;

import exception.GameException;
import player.PlayerProfile;

public class StockActivate {
    public static void start(PlayerProfile playerProfile) throws GameException {
        new StockStorage(playerProfile).play();
    }
}
