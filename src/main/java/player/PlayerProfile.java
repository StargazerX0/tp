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

    public void addAsset(int amount) {
        asset.addAsset(amount);
    }

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

    /**
     * Upgrades the player to an advanced player.
     */
    public void upgrade() {
        this.asset.deductAsset(10000);
        isAdvancedPlayer = true;
    }

    /**
     * Checks if the player can upgrade to an advanced player.
     */
    public boolean canUpgrade() {
        int upgradeCost = 10000;
        return asset.moreThan(upgradeCost);
    }

    public int getNumberOfEmployees() {
        return company.getNumberOfEmployees();
    }

    /**
     * Outputs the salary of each employee in the company.
     *
     * @return the salary of each employee in the company
     */
    public int getEmployeeSalary() {
        return company.getEmployeeSalary();
    }

    /**
     * Hires employees to the company based on the number of employees specified.
     *
     * @param number the number of employees to hire
     */
    public void hireEmployee(int number) {
        company.hireEmployee(number);
    }

    /**
     * Fires employees from the company based on the number of employees specified.
     *
     * @param number the number of employees to fire
     */
    public void fireEmployee(int number) {
        company.removeEmployee(number);
    }

    /**
     * Calculates the passive profit earned by the player in the current round
     * based on the company, bond, and cryptocurrency profits.
     */
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

    /**
     * Updates the revenue of the company based on the amount specified.
     *
     * @param amount the amount to update the revenue by.
     */
    public void updateRevenue(int amount) {
        company.updateRevenue(amount);
    }

    /**
     * Updates the salary of the employees in the company based on the amount specified.
     *
     * @param amount the amount to update the salary by.
     */
    public void updateSalary(int amount) {
        company.updateSalary(amount);
    }

    public Company getCompany() {
        return company;
    }

    /**
     * Checks if the player has upgraded to an advanced player.
     *
     * @return true if the player is an advanced player, false otherwise.
     */
    public boolean isAdvancedPlayer() {
        return isAdvancedPlayer;
    }

    public Asset getAsset() {
        return asset;
    }

    /**
     * Proceeds to the next round of the game,
     * calculates the profit earned in the current round and
     * resets the asset multiplier.
     */
    public void nextRound() {
        calculateRoundProfit();
        resetAssetMultiplier();
        currentRound++;
    }

    public void nextAction() {
        actionCount++;
    }

    /**
     * Checks if the player can perform an action in the current round.
     *
     * @return true if the player can perform an action, false otherwise.
     */
    public boolean canAct() {
        if (actionCount < actionPerRound()) {
            return true;
        }
        actionCount = 0;
        return false;
    }

    /**
     * Checks if the game has finished.
     *
     * @return true if round exceeds the round limit, player has achieved the goal or bankrupted, false otherwise.
     */
    public boolean isFinished() {
        if (asset.isBankrupt()) {
            ResponseManager.indentPrint("You have gone bankrupt!\n");
            return true;
        }
        return currentRound > ROUND_LIMIT || asset.isAchieved();
    }

    /**
     * Adjusts the asset multiplier by the multiplier specified.
     *
     * @param multiplier the multiplier to adjust the asset by.
     */
    public void adjustAssetMultiplier(double multiplier) {
        Asset.assetMultiplier = multiplier;
    }

    public void resetAssetMultiplier() {
        Asset.assetMultiplier = 1.0;
    }

    /**
     * Checks the player's current condition in the game.
     * The conditions are: player wins, player loses and game not finished.
     *
     * @return 1 if the player wins, -1 if the player loses, 0 if the game is not finished.
     */
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

    /**
     * Returns the number of actions the player can perform in a round.
     *
     * @return 3 if the player is an upgraded player, 1 otherwise.
     */
    public int actionPerRound() {
        int advancedPlayerAction = 3;
        int normalPlayerAction = 1;
        return isAdvancedPlayer() ? advancedPlayerAction : normalPlayerAction;
    }

    /**
     * Outputs the information of the player's company.
     *
     * @return the information of the player's company in a formatted string.
     */
    public String companyInfo() {
        return company.toString();
    }

    /**
     * Outputs the information of the player's profile.
     *
     * @return the information of the player's profile in a formatted string.
     */
    @Override
    public String toString() {
        return "Your name is: " + name + "\n"
                + "occupation: " + occupation + "\n"
                + "current health: " + health + "\n"
                + "current money: " + asset;
    }
}
