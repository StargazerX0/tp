package minigame.bondgame;

import exception.GameException;
import player.PlayerProfile;

/**
 * Activates the bond-buying mini-game within the EconoCraft game. This class is responsible
 * for initializing and starting the bond interaction process for a player.
 */
public class BondActivate {
    /**
     * Starts the bond-buying process for the specified player profile.
     *
     * @param playerProfile The player profile initiating the bond-buying process.
     * @throws GameException if there is an issue starting the bond-buying process.
     */
    public static void start(PlayerProfile playerProfile) throws GameException {
        //new BondStorage(playerProfile).play();
    }
}
