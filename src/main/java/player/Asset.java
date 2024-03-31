package player;

public class Asset {
    public static double assetMultiplier = 1.0;
    private static final int FINAL_GOAL = 1000000;


    private int totalAsset;

    public Asset() {
        this.totalAsset = 5000;
    }

    public Asset(int totalAsset) {
        this.totalAsset = totalAsset;
    }

    public int getAsset() {
        return this.totalAsset;
    }

    public void addAsset(int amount) {
        totalAsset += (int)(amount * assetMultiplier);
    }

    public void deductAsset(int amount) {
        totalAsset -= amount;
    }

    public boolean isAchieved() {
        return totalAsset >= FINAL_GOAL;
    }

    public boolean isBankrupt() {
        return totalAsset <= 0;
    }

    public int outputAsset() {
        return totalAsset;
    }

    public boolean moreThan(int amount) {
        return totalAsset > amount;
    }

    public String toString() {
        return String.format("%d", totalAsset);
    }
}
