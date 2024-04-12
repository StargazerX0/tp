package minigame.stockgame;

import exception.GameException;
import player.PlayerProfile;

/**
 * Provides the functionality to initiate the stock market mini-game for individual players.
 * This class serves as an entry point to the stock trading simulation, where players can engage
 * in buying, selling, and managing a portfolio of stocks within a simulated game environment.
 */
public class StockActivate {

    /**
     * Initiates the stock market mini-game for the specified player profile, setting up the
     * necessary environment for the player to start trading stocks. This method dynamically
     * creates an instance of {@code StockStorage} dedicated to the player's session, which
     * contains all the available stocks for trading and manages the game's state.
     *
     * @param playerProfile The player profile for whom the stock market game is to be started.
     *                      This profile includes details that may affect game play, such as
     *                      available funds, previous stock holdings, and any relevant player
     *                      preferences or history.
     * @throws GameException If there is an issue with initializing the game environment or if
     *                       an error occurs during the setup process. This exception ensures
     *                       that the calling code can handle initialization failures appropriately,
     *                       providing feedback to the player or attempting to correct the issue.
     */
    public static void start(PlayerProfile playerProfile) throws GameException {
        new StockStorage(playerProfile).play();
    }
}
