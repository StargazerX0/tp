package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class StockTen implements Stock {
    public static final String STOCK_GRAPH =
            "▲                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│     x                                                 \n" +
            "│    xxxx                                               \n" +
            "│   xx  x                                               \n" +
            "│  xx   x                                               \n" +
            "│ xx    xx     xxx                                      \n" +
            "│xx      xx   x  xx    xxxx        xx         xx        \n" +
            "│         x xx    xxxxxx   xx    xxxxxxxxxxxxxx xxxx    \n" +
            "│         xxx               xx xxx          xx     x    \n" +
            "│                            xxx                   xxx  \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "│                                                       \n" +
            "└──────────────────────────────────────────────────────►\n";
    private static final String STOCK_INFORMATION =
            "Technology sector in this quarter shows large potential -CNN \n" +
                    "Focus on niche market or expand? The two roads for start ups -Economists \n" +
                    "One of the team of Web 3.0 is a disaster -User899 from X \n";
    private static final String STOCK_NAME = "Web 3.0 (Software start-up) \n";

    private static final String HIDDEN_INFO =
            "The board have decided to trim some teams out of the company \n";

    private static final int STOCK_PRICE = 27;

    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME);
        ResponseManager.indentPrint("Price per stock : " + STOCK_PRICE + '\n');
        if (playerProfile.occupation.equals("Artificial intelligence")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    public int returnProfit() {
        return getRandomNumber(-10, 1);
    }

    public int returnStockPrice() {
        return STOCK_PRICE;
    }

    public String returnStockName() {
        return STOCK_NAME;
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
