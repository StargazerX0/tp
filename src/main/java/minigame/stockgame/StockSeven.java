package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class StockSeven implements Stock {
    public static final String STOCK_GRAPH =
            "▲                                                      \n" +
            "│                                                      \n" +
            "│                                                 xxx  \n" +
            "│                                                xx    \n" +
            "│                                               xx     \n" +
            "│                                               x      \n" +
            "│                                              x       \n" +
            "│                                             x        \n" +
            "│                                            xx        \n" +
            "│                                            x         \n" +
            "│                                           xx         \n" +
            "│               xx                 xxxxx    x          \n" +
            "│              xxx                 x   xx  xx          \n" +
            "│             x  xx               x     x xx           \n" +
            "│     xxxxx   x   xxx             x     xxx            \n" +
            "│     x   xx x      xx           x                     \n" +
            "│    xx    xxx       x           x                     \n" +
            "│    x     xx        xx    xx    x                     \n" +
            "│   xx                xx  xxxx   x                     \n" +
            "│   x                  xxxx  xxxxx                     \n" +
            "│                       xx     xx                      \n" +
            "│                        x                             \n" +
            "│                                                      \n" +
            "│                                                      \n" +
            "└─────────────────────────────────────────────────────►\n";
    private static final String STOCK_INFORMATION =
            "Profit for last year is 120 million dollars -NextGen official announcement \n" +
                    "The increase in NextGen stock is out of expectation -Economist \n" +
                    "Buying NextGen stock gave me a new house -User8482 from X \n";
    private static final String STOCK_NAME = "NextGen (Semiconductor start-up)";

    private static final String HIDDEN_INFO =
            "They are publishing false information";

    private static final int STOCK_PRICE = 120;

    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
        if (playerProfile.occupation.equals("Semi-conductor")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    public int returnProfit() {
        return getRandomNumber(-110, 5);
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
