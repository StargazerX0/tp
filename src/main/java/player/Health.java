package player;

import static ui.ResponseManager.indentPrint;
import static ui.ResponseManager.RED;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.RESET;

public class Health {
    private int healthAmount;

    public Health() {
        this.healthAmount = 100;
    }

    public Health(int healthAmount) {
        this.healthAmount = healthAmount;
    }

    /**
     * Adds health to the player. If the health amount exceeds 100,
     * the health amount is set to 100. A message about the health gain
     * is printed to the console.
     *
     * @param amount The amount of health to be added.
     */
    public void addHealth(int amount) {
        healthAmount += amount;
        if (healthAmount > 100) {
            healthAmount = 100;
            indentPrint(GREEN + "Health is full!\n" + RESET +
                    "Your health is now 100!\n");
        }
        indentPrint("Your have gained " + amount + " health.\n" +
                "Your current health is: " + healthAmount + "\n");
    }

    /**
     * Sets the health amount to a specific value.
     *
     * @param amount The amount to set the health to.
     */
    public void setHealth(int amount) {
        healthAmount = amount;
    }

    /**
     * Deducts health from the player. If the health amount falls below 0,
     * the health amount is set to 0. A message about the health loss
     * is printed to the console. If the health amount falls below 50,
     * a warning message is printed to the console.
     *
     * @param amount The amount of health to be deducted.
     */
    public void deductHealth(int amount) {
        if (healthAmount - amount < 0) {
            healthAmount = 0;
            return;
        }
        healthAmount -= amount;
        indentPrint("Your have lost " + amount + " health.\n" +
                "Your current health is: " + healthAmount + "\n");
        if (!isHealthy()) {
            indentPrint(RED + "Your health is below 50!\n" + RESET +
                    "Please take care of yourself!\n");
        }
    }

    /**
     * Checks if the player's health is above 50.
     *
     * @return true if the player's health is above 50, false otherwise.
     */
    public boolean isHealthy() {
        return healthAmount >= 50;
    }

    public int outputHealth() {
        return healthAmount;
    }

    /**
     * Returns a graphical representation of the health bar.
     * The health bar is represented by a series of '#' characters.
     * The number of '#' characters is determined by the health amount.
     * The health bar is colored green if the health amount is above 30,
     * and red if the health amount is below 30.
     *
     * @return the graphical representation of the health bar in string format.
     */
    public String toString() {
        int totalBar = 10;
        int bar = totalBar * healthAmount / 100;
        String healthBar = "#".repeat(bar) +
                " ".repeat(totalBar - bar);
        String color = healthAmount > 30 ? GREEN : RED;

        return "|" + color + healthBar + RESET +"|" + " " + healthAmount + "%";
    }
}
