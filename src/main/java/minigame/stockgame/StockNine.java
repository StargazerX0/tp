package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class StockNine implements Stock {
    public static final String STOCK_GRAPH =
            "▲                                                  \n" +
            "│                                                  \n" +
            "│           xx                                     \n" +
            "│           xx                                     \n" +
            "│          xxx                                     \n" +
            "│         xx  x                        xxx         \n" +
            "│        xx   x                       xx x         \n" +
            "│ x     xx    x                      xx  x         \n" +
            "│ xx    x     x          xx        xx    xx        \n" +
            "│   xx x       x        xxxxx     xx      x        \n" +
            "│    xx        xx      xx   xx  xx        xx   x   \n" +
            "│     x         xx     x     xxx           xx xx   \n" +
            "│                xx   xx                    xx x   \n" +
            "│                 xx  x                            \n" +
            "│                  xxx                             \n" +
            "│                   x                              \n" +
            "│                                                  \n" +
            "│                                                  \n" +
            "│                                                  \n" +
            "└─────────────────────────────────────────────────►\n";
    private static final String STOCK_INFORMATION =
            "Huge competition inside the software industry -CnA \n" +
                    "Why some of the simulation company are not making any profit? -Economist \n" +
                    "This is a good company -user482 from X \n";
    private static final String STOCK_NAME = "eSim (software start-up) \n";

    private static final String HIDDEN_INFO =
            "Some employers have not received their salary for 3 months \n";

    private static final int STOCK_PRICE = 11;

    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
        if (playerProfile.occupation.equals("Artificial intelligence")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    public int returnProfit() {
        return getRandomNumber(-10, -1);
    }

    public int returnStockPrice() {
        return STOCK_PRICE;
    }

    public int investmentGain(int stockAmount) {
        int gainPerStock = returnProfit();
        int gain = gainPerStock * stockAmount;
        System.out.println("The stock price risen by: " + gainPerStock);
        System.out.println("Your gain in stock for this round is: " + gain);
        return gain;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
