package minigame.cryptocurrency;

import exception.GameException;
import player.PlayerProfile;

/**
 * Initiates the cryptocurrency investment gameplay within the game. This class provides a static method
 * to start the process, acting as the entry point for players to engage with cryptocurrency investments.
 */
public class CryptoCurrencyActivate {

    /**
     * Initiates the cryptocurrency investment process for the specified player by creating an instance
     * of {@code CryptoCurrencyStorage} and invoking its play method. This setup ensures that all necessary
     * preparations are made for the player to start investing in cryptocurrencies within the game context.
     *
     * @param playerProfile The player profile initiating the investment process. This profile contains
     *                      all relevant information about the player's current state in the game, which
     *                      may affect how the investment process unfolds.
     * @throws GameException if any problems arise during the setup or execution of the cryptocurrency
     *                       investment process. This ensures that any issues are appropriately signaled
     *                       to higher-level handlers for resolution or feedback to the player.
     */
    public static void start(PlayerProfile playerProfile) throws GameException {

        new CryptoCurrencyStorage(playerProfile).play();
    }
}
