package command;

import exception.GameException;
import player.PlayerProfile;

public class InFlowCommand implements Command {
    private final String description;
    private final int amount;

    public InFlowCommand(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (amount <= 0) {
            throw new GameException("Inflow amount must be positive.");
        }
        playerProfile.addAsset(amount);
        playerProfile.recordFinancialActivity("Inflow", description, amount);
        System.out.println("Recorded inflow of $" + amount + ": " + description);
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
