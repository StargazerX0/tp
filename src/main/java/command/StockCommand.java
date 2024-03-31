package command;

import player.PlayerProfile;

public class StockCommand implements Command {
    public void execute(PlayerProfile playerProfile) {
        System.out.println("StockCommand executed");
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return false;
    }
}
