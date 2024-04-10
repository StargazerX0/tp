package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

/**
 * Represents the Microsoft stock in the stock market mini-game.
 * This stock provides specific information, including a graphical representation of its performance,
 * relevant news, and special insights for players with a specific occupation.
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

    public static final String STOCK_NAME = "Microsoft (Multi-national cooperation) \n";
    private static final String STOCK_INFORMATION =
            "Profit for microsoft in quarter one is similar to quarter two -CNN \n" +
                    "Where is the next step for Microsoft? -Economist \n" +
                    "Not much progress in our R&D department for microsoft so far -User5642 from Twitter \n";

    private static final String HIDDEN_INFO = "The operation inside Microsoft has been steady so far. \n";

    private static final int STOCK_PRICE = 420;

    /**
     * Prints detailed information about the Microsoft stock, including a stock graph,
     * current price, and news. Provides additional insights for AI specialists.
     *
     * @param playerProfile Player's profile, used to determine if special insights are shown.
     */
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
        ResponseManager.indentPrint("Price per stock : " + STOCK_PRICE + '\n');
        if (playerProfile.occupation.equals("Artificial intelligence")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    /**
     * Returns the fixed price of the Microsoft stock.
     *
     * @return Stock price.
     */
    public int returnStockPrice() {
        return STOCK_PRICE;
    }

    /**
     * Returns the name of the stock.
     *
     * @return Stock name.
     */
    public String returnStockName() {
        return STOCK_NAME;
    }

    public int returnProfit() {
        return getRandomNumber(-10, 10);
    }

    /**
     * Calculates the investment gain based on the amount of stock and a random profit range.
     *
     * @param stockAmount The amount of stock the player has invested in.
     * @return The total investment gain.
     */
    public int investmentGain(int stockAmount) {
        int gainPerStock = returnProfit();
        int gain = gainPerStock * stockAmount;
        System.out.println("The stock price risen by: " + gainPerStock);
        System.out.println("Your gain in stock for this round is: " + gain + '\n');
        return gain;
    }

    /**
     * Generates a random number within a specified range, representing stock price fluctuation.
     *
     * @param min Minimum value of the range.
     * @param max Maximum value of the range.
     * @return A random number within the specified range.
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
