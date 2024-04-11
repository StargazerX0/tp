package minigame.stockgame;

import player.PlayerProfile;

/**
 * Represents the fundamental behaviors that all stock entities must implement in the stock market
 * mini-game. This interface is designed to encapsulate the essential actions that can be performed
 * with stocks, including obtaining information about the stock, calculating potential investment gains,
 * and accessing basic stock details like name and price. It allows players to interact with different
 * types of stocks in a unified manner within the game's investment environment.
 */
public interface Stock {

    /**
     * Displays information about the stock to a player. This may include details such as the stock's
     * current price, performance history, and any other relevant information that can influence
     * the player's decision-making process regarding buying, holding, or selling the stock.
     *
     * @param playerProfile The player's profile, which may be used to tailor the information
     *                      displayed based on the player's past interactions or preferences.
     */
    public void printInfo(PlayerProfile playerProfile);

    /**
     * Calculates the potential gain from an investment in a specified amount of stock. This method
     * is essential for players to estimate the profitability of their investment decisions within
     * the game's economic model.
     *
     * @param stockAmount The amount of stock being considered for investment.
     * @return The calculated potential investment gain as an integer.
     */
    public int investmentGain(int stockAmount);

    /**
     * Retrieves the name of the stock. This is a unique identifier for the stock within the game's
     * financial universe and is used for displaying to players and for transaction processing.
     *
     * @return The name of the stock as a String.
     */
    public String returnStockName();

    /**
     * Obtains the current market price of the stock. This price is crucial for all financial
     * transactions involving the stock, including buying and selling actions by the player.
     *
     * @return The current price of the stock as an integer.
     */
    public int returnStockPrice();

}
