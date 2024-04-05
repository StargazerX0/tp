package command;

import exception.LockedFeatureException;
import player.PlayerProfile;

public class SellCryptoCommand implements Command {
    public void execute(PlayerProfile playerProfile) throws LockedFeatureException {
        if (!playerProfile.isAdvancedPlayer()) {
            throw new LockedFeatureException();
        }
        playerProfile.getAsset().sellCrypto();
        System.out.println("SellCryptoCommand executed");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
