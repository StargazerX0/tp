package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

/**
 * Represents the Microsoft stock within the virtual stock market.
 */
public class StockEight implements Stock{
    public static final String STOCK_GRAPH =
            "^                                                       \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|     x                                                  \n" +
            "|   xxxxx                                                \n" +
            "|xxxx   xx    xxxxxxxxxxx                                \n" +
            "|         xxxx          xxxxxxx                 xxxxxxxx \n" +
            "|          x                   xxxxxxxxx     xxxx        \n" +
            "|                                       xxxxxx           \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|                                                        \n" +
            "|________________________________________________________>\n";

    public static final String STOCK_NAME = "Microsoft (Multi-national cooperation) ";
    private static final String STOCK_INFORMATION =
            "Profit for microsoft in quarter one is similar to quarter two -CNN \n" +
                    "Where is the next step for Microsoft? -Economist \n" +
                    "Not much progress in our R&D department for microsoft so far -User5642 from Twitter \n";

    private static final String HIDDEN_INFO = "The operation inside Microsoft has been steady so far. \n";

    private static final int STOCK_PRICE = 420;

    /**
     * Display the stock information the player can obtain based on their personal profile.
     *
     * @param playerProfile the player profile obtained to access player's information.
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
     * Returns the current market stock price of the stock.
     *
     * @return the current market stock price.
     */
    public int returnStockPrice() {
        return STOCK_PRICE;
    }

    /**
     * Retrieves the stock name for the player.
     *
     * @return the stock name of the stock.
     */
    public String returnStockName() {
        return STOCK_NAME;
    }

    /**
     * Generates the gain made per stock if player sells them now.
     *
     * @return the gain per unit of stock.
     */
    public int returnProfit() {
        return getRandomNumber(-10, 10);
    }

    /**
     * Generates the investment gain made for selling the stock.
     *
     * @param stockAmount The number of stock the player currently possess.
     * @return the total investment gain made.
     */
    public int investmentGain(int stockAmount) {
        int gainPerStock = returnProfit();
        int gain = gainPerStock * stockAmount;
        System.out.println("The stock price risen by: " + gainPerStock);
        System.out.println("Your gain in stock for this round is: " + gain + '\n');
        return gain;
    }

    /**
     * Generates a random integer within a certain range.
     *
     * @param min the lower bound of the range.
     * @param max the upper bound of the range.
     * @return the random integer generated within the range.
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
