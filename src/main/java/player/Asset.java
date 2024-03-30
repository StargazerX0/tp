package player;

public class Asset {
    private static final int GOAL = 100000;
    private int totalAsset;

    public Asset() {
        this.totalAsset = 5000;
    }

    public Asset(int totalAsset) {
        this.totalAsset = totalAsset;
    }

    public void addAsset(int amount) {
        totalAsset += amount;
    }

    public void deductAsset(int amount) {
        totalAsset -= amount;
    }

    public boolean isAchieved() {
        return totalAsset >= GOAL;
    }

    public boolean isBankrupt() {
        return totalAsset <= 0;
    }

    public String outputAsset() {
        return "" + totalAsset;
    }

    public String toString() {
        return String.format("%d", totalAsset);
    }
}
