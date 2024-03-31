package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class StockSix implements Stock {
    public static final String STOCK_GRAPH = "▲                                                                 \n" +
            "│                                                                 \n" +
            "│                                                                 \n" +
            "│                                                                 \n" +
            "│                                                     xxxxxx      \n" +
            "│                                                 xxxxx    xx     \n" +
            "│                                               xx          xx    \n" +
            "│                                              xx            xx   \n" +
            "│                                            xxx              xx  \n" +
            "│                                           xx                    \n" +
            "│                                          xx                     \n" +
            "│        xxxx                             xx                      \n" +
            "│     xxxx  xxx                          xx                       \n" +
            "│   xxx       xx                       xxx                        \n" +
            "│ xxx           x                    xxx                          \n" +
            "│               xx         xxxxx xxxxx                            \n" +
            "│                xx       xx   xxx                                \n" +
            "│                 xxxxxxxxx                                       \n" +
            "│                                                                 \n" +
            "│                                                                 \n" +
            "│                                                                 \n" +
            "│                                                                 \n" +
            "│                                                                 \n" +
            "└────────────────────────────────────────────────────────────────►\n";
    private static final String STOCK_INFORMATION =
            "The profit for BYD last year is 300 million -BYD financial report \n" +
                    "Is China entering the competition of electric vehicles? -Economist \n" +
                    "I have received by dividend for BYD this year -User3435 on X \n";
    private static final String STOCK_NAME = "BYD (Vehicle cooperation) \n";

    private static final String HIDDEN_INFO =
            "Minister of Finance in China is very interested in the market of electric vehicles. \n";

    private static final int STOCK_PRICE = 192;

    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
        if (playerProfile.occupation.equals("Robotics")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    public int returnProfit() {
        return getRandomNumber(-10, 40);
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
