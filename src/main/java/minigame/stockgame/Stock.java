package minigame.stockgame;

import player.PlayerProfile;

/**
 * Defines the contract for stock entities in the stock market mini-game.
 * Stocks are financial instruments that players can buy, hold, and sell to
 * manage their portfolio and aim for investment gains within the game environment.
 */
public interface Stock {

    /**
     * Displays stock information.
     *
     * @param playerProfile Player's profile for contextual information.
     */
    public void printInfo(PlayerProfile playerProfile);

    /**
     * Calculates investment gain.
     *
     * @param stockAmount Amount of stock.
     * @return Investment gain.
     */
    public int investmentGain(int stockAmount);

    /**
     * Returns the stock name.
     *
     * @return Stock name.
     */
    public String returnStockName();

    /**
     * Returns the stock price.
     *
     * @return Stock price.
     */

    public int returnStockPrice();
}
