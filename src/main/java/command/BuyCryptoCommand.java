package command;

import exception.GameException;
import exception.LockedFeatureException;
import player.PlayerProfile;
import minigame.cryptocurrency.CryptoCurrencyActivate;

public class BuyCryptoCommand implements Command {
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        CryptoCurrencyActivate.start(playerProfile);
        System.out.println("BuyCryptoCommand executed");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return true;
    }
}
