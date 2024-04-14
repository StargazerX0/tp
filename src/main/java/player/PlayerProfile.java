package player;

import company.Company;
import ui.ResponseManager;

/**
 * Represents a player profile in the game, including personal attributes, health, assets,
 * and company ownership. This class facilitates managing player health, assets, round progression,
 * and checking game win/loss conditions.
 */
public class PlayerProfile {
    public static final int ROUND_LIMIT = 20;
    public final String occupation;
    private final String name;
    private final Health health;
    private final Asset asset;
    private final Company company;
    private int currentRound;
    private int actionCount;
    private boolean isAdvancedPlayer;

    /**
     * Constructs a player profile with default attributes.
     *
     * @param name Player's name.
     * @param occupation Player's occupation.
     */
    public PlayerProfile(String name, String occupation) {
        this.name = name;
        this.health = new Health();
        this.asset = new Asset();
        this.occupation = occupation;
        this.currentRound = 1;
        this.actionCount = 0;
        this.isAdvancedPlayer = false;
        this.company = new Company();
    }

    /**
     * Constructs a player profile with specified attributes and default company.
     * Facilitates testing with custom attributes.
     *
     * @param name Player's name.
     * @param occupation Player's occupation.
     * @param health Initial health points.
     * @param asset Initial asset value.
     * @param currentRound The current round number.
     * @param isAdvancedPlayer If the player is considered advanced.
     */
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
        String name, String occupation, int health, Asset asset,
        int currentRound, int actionCount, boolean isAdvancedPlayer, Company company) {
        this.name = name;
        this.health = new Health(health);
        this.asset = asset;
        this.occupation = occupation;
        this.currentRound = currentRound;
        this.actionCount = actionCount;
        this.isAdvancedPlayer = isAdvancedPlayer;
        this.company = company;
    }

    public void increaseAsset(int amount) {
        asset.addAsset(amount);

    public void loseHealth(int amount) {
        this.health.deductHealth(amount);
    }

    public void addHealth(int amount) {
        this.health.addHealth(amount);
    }

    public void setHealth(int amount) {
        this.health.setHealth(amount);
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

    public int getHealth() {
        return health.outputHealth();
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getActionCount() {
        return actionCount;
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

    private void calculateRoundProfit() {
        if (isAdvancedPlayer()) {
            int companyProfit = company.profitPerRound();
            int bondReturn = asset.bondReturn();
            int cryptoReturn = asset.cryptoReturn();
            int totalProfit = companyProfit + bondReturn + cryptoReturn;

            ResponseManager.printRoundEarned(companyProfit, bondReturn, cryptoReturn);
            if (totalProfit >= 0) {
                asset.addAsset(totalProfit);
            } else {
                asset.deductAsset(Math.abs(totalProfit));
            }
            ResponseManager.endOfRoundMessage(currentRound);
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
        calculateRoundProfit();
        resetAssetMultiplier();
        currentRound++;
    }

    public void nextAction() {
        actionCount++;
    }

    public boolean canAct() {
        if (actionCount < actionPerRound()) {
            return true;
        }
        actionCount = 0;
        return false;
    }

    public boolean isFinished() {
        if (asset.isBankrupt()) {
            ResponseManager.indentPrint("You have gone bankrupt!\n");
            return true;
        }
        return currentRound > ROUND_LIMIT || asset.isAchieved();
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
        if (asset.isBankrupt() || currentRound > ROUND_LIMIT) {
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
        return "Your name is: " + name + "\n"
                + "occupation: " + occupation + "\n"
                + "current health: " + health + "\n"
                + "current money: " + asset;
    }
}
