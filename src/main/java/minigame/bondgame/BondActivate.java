package minigame.bondgame;

import exception.GameException;
import player.PlayerProfile;

/**
 * Facilitates the activation of the bond-buying mini-game within EconoCraft.
 * This class provides a static method to start the bond-buying process for a player.
 */
public class BondActivate {

    /**
     * Initiates the bond-buying process for the given player.
     * It creates a {@code BondStorage} instance for the player to manage bond interactions.
     *
     * @param playerProfile The profile of the player starting the bond-buying process.
     * @throws GameException If there is an issue initializing the bond-buying process.
     */
    public static void start(PlayerProfile playerProfile) throws GameException {
        new BondStorage(playerProfile).play();
    }
}

