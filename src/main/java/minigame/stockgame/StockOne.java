package minigame.stockgame;

import player.PlayerProfile;
import ui.ResponseManager;

public class StockOne implements Stock{
    private static final String STOCK_GRAPH =
            " ^            xxxxx \n" +
            " |           xx     \n" +
            " |    xxx   xx      \n" +
            " |   xx  xxxx        \n" +
            " |xxxx               \n" +
            " |                   \n" +
            " |____________________> \n";
    private static final String STOCK_INFORMATION =
            "WaveScan is planning to invest 100M in Ai chip -CnA \n"
            + "WaveScan have the risk of overExpansion -Economists \n"
            + "I have heard that WaveScan is not doing so well -User10086 from X \n";

    private static final String STOCK_NAME = "WaveScan Technologies (Start ups) ";
    private static final String HIDDEN_INFO = "They are close to a technical break through\n";
    private static final int STOCK_PRICE = 10;

    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(STOCK_GRAPH);
        ResponseManager.indentPrint(STOCK_INFORMATION);
        ResponseManager.indentPrint(STOCK_NAME + "\n");
        ResponseManager.indentPrint("Price per stock : " + STOCK_PRICE + '\n');
        if (playerProfile.occupation.equals("Semi-conductor")) {
            ResponseManager.indentPrint((HIDDEN_INFO));
        }
    }

    public int returnProfit() {
        return getRandomNumber(-2, 10);
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
        System.out.println("The stock price rised by: " + gainPerStock);
        System.out.println("Your gain in stock for this round is: " + gain);
        return gain;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


}
