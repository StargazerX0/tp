package minigame.bondgame;

import exception.GameException;
import player.PlayerProfile;

public class BondGame {
    public static void start(PlayerProfile playerProfile) throws GameException {
        BondMarket bondMarket = new BondMarket(playerProfile);
        bondMarket.play();
    }
}
