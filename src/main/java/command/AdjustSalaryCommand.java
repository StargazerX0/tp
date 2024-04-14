package command;

import exception.GameException;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;
import static ui.ResponseManager.RED;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.RESET;

/**
 * Represents the command to either raise or lower the player's employee's salary.
 */
public class AdjustSalaryCommand implements Command {
    private static final int ADJUSTMENT_LIMIT = 800;
    private final String updateType;
    private int amount;

    public AdjustSalaryCommand(String updateType, int amount) {
        this.updateType = updateType;
        this.amount = amount;
    }

    /**
     * {@inheritDoc}
     *
     * Adjusts the player's employee's salary based on the update type and amount.
     * If the update type is "lower", the salary will be lowered by the amount.
     * If the update type is "raise", the salary will be raised by the amount.
     *
     * @param playerProfile the player profile to be updated.
     * @throws GameException if the extent of lowering salary is more than the employee's salary.
     * @throws GameException if the extent of raising salary is more than the adjustment limit.
     */
    @Override
    public void execute(PlayerProfile playerProfile) throws GameException {
        if (updateType.equals("lower")) {
            if (amount > playerProfile.getEmployeeSalary()) {
                throw new GameException("Extent of lowering salary cannot be more than the employee's salary.\n");
            }
            indentPrint("You have successfully lowered the salary by $" + RED + amount + RESET + ".\n");
            amount = -amount;
        } else {
            if (amount > ADJUSTMENT_LIMIT) {
                throw new GameException("Extent of rising salary cannot be more than $" + ADJUSTMENT_LIMIT + ".\n");
            }
            indentPrint("You have successfully raised the salary by $" + GREEN + amount + RESET + ".\n");
        }
        playerProfile.updateSalary(amount);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isAnAction() {
        return true;
    }
}
