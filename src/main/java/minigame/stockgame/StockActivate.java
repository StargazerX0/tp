package minigame.stockgame;

import exception.GameException;
import player.PlayerProfile;

/**
 * Handles the activation of the stock market mini-game.
 */
public class StockActivate {

    /**
     * Starts the stock market mini-game for a given player profile.
     *
     * @param playerProfile The player's profile to initiate the game with.
     * @throws GameException If an error occurs during game initialization or play.
     */
    public static void start(PlayerProfile playerProfile) throws GameException {
        new StockStorage(playerProfile).play();
    }
}
