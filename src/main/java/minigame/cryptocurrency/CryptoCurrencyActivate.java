package minigame.cryptocurrency;

import exception.GameException;
import player.PlayerProfile;

/**
 * The CryptoCurrencyActivate class is responsible for initiating the cryptocurrency
 * investment gameplay. It acts as a trigger for starting the investment process.
 */
public class CryptoCurrencyActivate {
    /**
     * Starts the cryptocurrency investment process for a given player.
     * This method creates an instance of CryptoCurrencyStorage and calls its play method.
     *
     * @param playerProfile The profile of the player making the investment.
     * @throws GameException if there is an issue during the cryptocurrency investment process.
     */
    public static void start(PlayerProfile playerProfile) throws GameException {
        //new CryptoCurrencyStorage(playerProfile).play();
    }
}
