package player;

public class Health {
    private int healthBar;

    public Health() {
        this.healthBar = 100;
    }

    public Health(int healthBar) {
        this.healthBar = healthBar;
    }

    public void addHealth(int amount) {
        healthBar += amount;
        if (healthBar > 100) {
            healthBar = 100;
            System.out.println("Health is full! Your health is now 100!");
        }
    }

    public void setHealth(int amount) {
        healthBar = amount;
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
