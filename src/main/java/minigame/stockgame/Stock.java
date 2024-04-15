package minigame.stockgame;

import player.PlayerProfile;

/**
 * Represents the virtual stock market accessed by players inside the game. Including
 * stock graphs, stock information, price, hidden information, and stock fluctuation
 * metrics.
 */
public interface Stock {

    /**
     * Displays information about the stock to player including stock graph, relevant information,
     * and current stock price. In order to help the player to make a more rational decision.
     *
     * @param playerProfile the player profile obtained to access player's information.
     */
    public void printInfo(PlayerProfile playerProfile);

    /**
     * Calculates the financial profits for a particular stock if the player sells it.
     *
     * @param stockAmount The number of stock the player currently possess.
     * @return The calculated financial profit from selling the stock.
     */
    public int investmentGain(int stockAmount);

    /**
     * Retrieves the name of a particular stock.
     *
     * @return the name of the stock.
     */
    public String returnStockName();

    /**
     * Retrieves the current market price of a particular stock.
     *
     * @return The current purchase price of a stock.
     */
    public int returnStockPrice();

}
