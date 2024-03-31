package command;

import player.PlayerProfile;

public class SellStockCommand implements Command{
    public void execute(PlayerProfile playerProfile) {
        playerProfile.getAsset().sellStock();
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
