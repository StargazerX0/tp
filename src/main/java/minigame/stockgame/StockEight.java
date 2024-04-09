package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class StockEight implements Stock{
    public static final String STOCK_GRAPH =
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "     x                                                  \n" +
            "   xxxxx                                                \n" +
            "xxxx   xx    xxxxxxxxxxx                                \n" +
            "         xxxx          xxxxxxx                 xxxxxxxx \n" +
            "          x                   xxxxxxxxx     xxxx        \n" +
            "                                       xxxxxx           \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "                                                        \n" +
            "\n";

    public static final String STOCK_NAME = "Microsoft (Multi-national cooperation) \n";
    private static final String STOCK_INFORMATION =
            "Profit for microsoft in quarter one is similar to quarter two -CNN \n" +
                    "Where is the next step for Microsoft? -Economist \n" +
                    "Not much progress in our R&D department for microsoft so far -User5642 from Twitter \n";

    private static final String HIDDEN_INFO = "The operation inside Microsoft has been steady so far. \n";

    private static final int STOCK_PRICE = 420;

    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
        ResponseManager.indentPrint("Price per stock : " + STOCK_PRICE + '\n');
        if (playerProfile.occupation.equals("Artificial intelligence")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    public int returnStockPrice() {
        return STOCK_PRICE;
    }

    public String returnStockName() {
        return STOCK_NAME;
    }

    public int returnProfit() {
        return getRandomNumber(-10, 10);
    }

    public int investmentGain(int stockAmount) {
        int gainPerStock = returnProfit();
        int gain = gainPerStock * stockAmount;
        System.out.println("The stock price risen by: " + gainPerStock);
        System.out.println("Your gain in stock for this round is: " + gain + '\n');
        return gain;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
