package player;

import company.Company;

public class PlayerProfile {
    public static final int ROUND_LIMIT = 20;
    public final String occupation;
    private final String name;
    private final Health health;
    private final Asset asset;
    private int currentRound;
    private boolean isAdvancedPlayer;
    private final Company company;

    public PlayerProfile(String name, String occupation) {
        this.name = name;
        this.health = new Health();
        this.asset = new Asset();
        this.occupation = occupation;
        this.currentRound = 1;
        this.isAdvancedPlayer = false;
        this.company = new Company();
    }

    public PlayerProfile(
            String name, String occupation, int health, int asset,
            int currentRound, boolean isAdvancedPlayer) {
        this.name = name;
        this.health = new Health(health);
        this.asset = new Asset(asset);
        this.occupation = occupation;
        this.currentRound = currentRound;
        this.isAdvancedPlayer = isAdvancedPlayer;
        this.company = new Company();
    }

    public PlayerProfile(
        String name, String occupation, int health, int asset,
        int currentRound, boolean isAdvancedPlayer, Company company) {
        this.name = name;
        this.health = new Health(health);
        this.asset = new Asset(asset);
        this.occupation = occupation;
        this.currentRound = currentRound;
        this.isAdvancedPlayer = isAdvancedPlayer;
        this.company = company;
    }

    public void addAsset(int amount) {
        this.asset.addAsset(amount);
    }

    public void addHealth(int amount) {
        this.health.add(amount);
    }

    public void loseHealth(int amount) {
        this.health.deduct(1);
    }

    public void loseAsset(int amount) {
        this.asset.deductAsset(amount);
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

    public void upgrade() {
        this.asset.deductAsset(10000);
        isAdvancedPlayer = true;
    }

    public boolean canUpgrade(int moneyNeeded) {
        return asset.moreThan(moneyNeeded);
    }

    public int getNumberOfEmployees() {
        return company.getNumberOfEmployees();
    }

    public int getEmployeeSalary() {
        return company.getEmployeeSalary();
    }

    public void hireEmployee(int number) {
        company.hireEmployee(number);
    }

    public void fireEmployee(int number) {
        company.removeEmployee(number);
    }

    public void updatePlayer() {
        if (isAdvancedPlayer()) {
            company.updatePlayer(asset);
        }
    }

    public void updateRevenue(int amount) {
        company.updateRevenue(amount);
    }

    public void updateSalary(int amount) {
        company.updateSalary(amount);
    }

    public Company getCompany() {
        return company;
    }

    public boolean isAdvancedPlayer() {
        return isAdvancedPlayer;
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

    public void adjustAssetMultiplier(double multiplier) {
        Asset.assetMultiplier = multiplier;
    }

    public void resetAssetMultiplier() {
        Asset.assetMultiplier = 1.0;
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

    public int actionPerRound() {
        return isAdvancedPlayer() ? 3 : 1;
    }

    public String companyInfo() {
        return company.toString();
    }

    @Override
    public String toString() {
        return "Your name is :" + name + '\n'
                + "occupation :" + occupation + '\n'
                + "current health :" + health + "\n"
                + "current asset: " + asset + "\n";
    }
}
