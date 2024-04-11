package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

/**
 * Represents the Microsoft stock within the stock market mini-game, encapsulating details
 * such as performance graphics, relevant news, and special insights tailored for players with
 * specific occupations. This class allows for an interactive representation of stock market
 * investments, providing players with a realistic experience of trading Microsoft stock, including
 * its price fluctuations and potential investment gains.
 */
public class StockEight implements Stock{
    public static final String STOCK_GRAPH =
            "▲                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│     x                                                  \n" +
            "│   xxxxx                                                \n" +
            "│xxxx   xx    xxxxxxxxxxx                                \n" +
            "│         xxxx          xxxxxxx                 xxxxxxxx \n" +
            "│          x                   xxxxxxxxx     xxxx        \n" +
            "│                                       xxxxxx           \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "│                                                        \n" +
            "└───────────────────────────────────────────────────────►\n";

    public static final String STOCK_NAME = "Microsoft (Multi-national cooperation) ";
    private static final String STOCK_INFORMATION =
            "Profit for microsoft in quarter one is similar to quarter two -CNN \n" +
                    "Where is the next step for Microsoft? -Economist \n" +
                    "Not much progress in our R&D department for microsoft so far -User5642 from Twitter \n";

    private static final String HIDDEN_INFO = "The operation inside Microsoft has been steady so far. \n";

    private static final int STOCK_PRICE = 420;

    /**
     * Displays comprehensive information about Microsoft stock to the player, including a
     * visual graph of its performance, recent news headlines, and the current stock price.
     * If the player's profile matches certain criteria (e.g., occupation in Artificial Intelligence),
     * additional hidden insights are revealed to provide deeper understanding of the stock's potential.
     *
     * @param playerProfile The profile of the player, used to tailor the information displayed.
     */
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME + "\n");
        ResponseManager.indentPrint("Price per stock : " + STOCK_PRICE + '\n');
        if (playerProfile.occupation.equals("Artificial intelligence")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    /**
     * Returns the current fixed price of Microsoft stock, facilitating trading decisions within the game.
     *
     * @return The current price of the stock.
     */
    public int returnStockPrice() {
        return STOCK_PRICE;
    }

    /**
     * Provides the name of the stock, enabling identification and selection within the trading interface.
     *
     * @return The name of the stock.
     */
    public String returnStockName() {
        return STOCK_NAME;
    }

    /**
     * Generates a random profit or loss value for the stock, simulating market changes that affect stock prices.
     * This function provides dynamic feedback on stock investment outcomes, enhancing the game's realism.
     *
     * @return A random profit or loss value.
     */
    public int returnProfit() {
        return getRandomNumber(-10, 10);
    }

    /**
     * Simulates the calculation of investment gains based on the amount of stock a player decides to invest in.
     * This function models stock price volatility and the resultant gain or loss from trading activities.
     *
     * @param stockAmount The quantity of stock the player has invested in.
     * @return The calculated total investment gain or loss.
     */
    public int investmentGain(int stockAmount) {
        int gainPerStock = returnProfit();
        int gain = gainPerStock * stockAmount;
        System.out.println("The stock price risen by: " + gainPerStock);
        System.out.println("Your gain in stock for this round is: " + gain + '\n');
        return gain;
    }

    /**
     * Utility method to generate a random number within a specified range, used to simulate stock price fluctuation.
     * This method reflects the unpredictable nature of the stock market, affecting investment gain calculations.
     *
     * @param min The minimum value (inclusive) of the range.
     * @param max The maximum value (exclusive) of the range.
     * @return A randomly generated number within the specified range.
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
