package command;

import exception.GameException;
import player.PlayerProfile;
import static ui.ResponseManager.indentPrint;
import static ui.ResponseManager.RED;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.RESET;

/**
 * Executes salary adjustment commands for an employee, either increasing or decreasing their salary.
 * This command validates the adjustment against predefined limits before applying the change.
 */
public class AdjustSalaryCommand implements Command {
    private static final int ADJUSTMENT_LIMIT = 800;
    private final String updateType;
    private int amount;

    /**
     * Constructs a new AdjustSalaryCommand.
     *
     * @param updateType Type of salary adjustment ("lower" or otherwise considered as "raise").
     * @param amount The amount to adjust the salary by.
     */
    public AdjustSalaryCommand(String updateType, int amount) {
        this.updateType = updateType;
        this.amount = amount;
    }

    /**
     * Executes the salary adjustment command on the specified player profile.
     *
     * @param playerProfile The player profile whose employee's salary is to be adjusted.
     * @throws GameException If the adjustment exceeds limits or is invalid.
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


    /**
     * Indicates that this command does not exit the game.
     *
     * @return false always.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Indicates that this command can potentially trigger a random event.
     *
     * @return true always.
     */
    @Override
    public boolean canGenerateEvent() {
        return true;
    }
}
