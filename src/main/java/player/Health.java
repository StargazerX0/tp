package player;

public class Health {
    private int healthBar;

    public Health() {
        this.healthBar = 100;
    }

    public Health(int healthBar) {
        this.healthBar = healthBar;
    }

    public void add(int amount) {
        healthBar += amount;
    }

    public void deduct(int amount) {
        healthBar -= amount;
    }

    public boolean isDead() {
        return healthBar <= 0;
    }

    public int outputHealth() {
        return healthBar;
    }

    public String toString() {
        return String.format("%d", healthBar);
    }
}
