package minigame.stockgame;

import exception.GameException;
import player.PlayerProfile;

/**
 * Provides simulation to the virtual stock market where users can purchase and sell stocks.
 * Acts as the entry point to the virtual stock market.
 */
public class StockActivate {

    /**
     * Initializes the virtual stock environment for the player.
     *
     * @param playerProfile the player profile to be updated.
     * @throws GameException if the player input in invalid format or does not have enough money.
     */
    public static void start(PlayerProfile playerProfile) throws GameException {
        new StockStorage(playerProfile).play();
    }
}
