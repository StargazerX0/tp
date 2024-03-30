package player;

public class PlayerProfile {
    public static final int ROUND_LIMIT = 20;
    public final String occupation;
    private final String name;
    private final Health health;
    private final Asset asset;
    private int currentRound;


    public PlayerProfile(String name, String occupation) {
        this.name = name;
        this.health = new Health();
        this.asset = new Asset();
        this.occupation = occupation;
        this.currentRound = 0;
    }

    public PlayerProfile(String name, String occupation, int health, int asset, int currentRound) {
        this.name = name;
        this.health = new Health(health);
        this.asset = new Asset(asset);
        this.occupation = occupation;
        this.currentRound = currentRound;
    }

    public void addAsset(int amount) {
        this.asset.addAsset(amount);
    }

    public void addHealth(int amount) {
        this.health.add(amount);
    }

    public void loseHealth() {
        this.health.deduct(1);
    }

    public void loseAsset() {
        this.asset.deductAsset(1);
    }

    public String getName() {
        return this.name;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public Health getHealth() {
        return health;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public Asset getAsset() {
        return asset;
    }

    public void nextRound() {
        currentRound++;
    }

    public boolean isFinished() {
        return currentRound >= ROUND_LIMIT || health.isDead() || asset.isBankrupt() || asset.isAchieved();
    }

    public int checkWin() {
        if (asset.isAchieved()) {
            return 1;
        }
        if (asset.isBankrupt() || health.isDead() || currentRound >= ROUND_LIMIT) {
            return -1;
        }
        // game not finished
        return 0;
    }

    @Override
    public String toString() {
        return "Your name is :" + name + '\n'
                + "occupation :" + occupation + '\n'
                + "current health :" + health.outputHealth() + "\n"
                + "current asset: " + asset.outputAsset();
    }
}
