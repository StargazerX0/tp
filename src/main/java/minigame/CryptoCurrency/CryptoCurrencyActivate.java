package minigame.CryptoCurrency;

import exception.GameException;
import player.PlayerProfile;

public class CryptoCurrencyActivate {
    public static void start(PlayerProfile playerProfile) throws GameException {
        new CryptoCurrencyStorage(playerProfile).play();
    }
}
