package player;

import static ui.ResponseManager.indentPrint;
public class Health {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";
    private int health;

    public Health() {
        this.health = 100;
    }

    public Health(int health) {
        this.health = health;
    }

    public void addHealth(int amount) {
        health += amount;
        if (health > 100) {
            health = 100;
            indentPrint(GREEN + "Health is full!\n" + RESET +
                    "Your health is now 100!\n");
        }
    }

    public void setHealth(int amount) {
        health = amount;
    }

    public void deduct(int amount) {
        if (health - amount < 0) {
            health = 0;
            return;
        }
        health -= amount;
        if (!isHealthy()) {
            indentPrint(RED + "Your health is below 50!\n" + RESET +
                    "Please take care of yourself!\n");
        }
    }

    public boolean isHealthy() {
        return health >= 50;
    }

    public int outputHealth() {
        return health;
    }

    public String toString() {
        int totalBar = 10;
        int bar = totalBar * health / 100;
        String healthBar = "#".repeat(bar) +
                " ".repeat(totalBar - bar);
        String color = health >= 30 ? GREEN : RED;
        return "|" + color + healthBar + RESET +"|";
    }
}
