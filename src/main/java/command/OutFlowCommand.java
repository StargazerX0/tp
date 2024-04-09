package command;

import exception.GameException;
import player.PlayerProfile;

public class OutFlowCommand implements Command {
    private final String description;
    private final int amount;

    public OutFlowCommand(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (amount <= 0) {
            throw new GameException("Outflow amount must be positive.");
        }
        playerProfile.loseAsset(amount);
        playerProfile.recordFinancialActivity("Outflow", description, -amount);
        System.out.println("Recorded outflow of $" + amount + ": " + description);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean canGenerateEvent() {
        return false;
    }
}
