package player;

import minigame.cryptocurrency.CryptoCurrency;
import minigame.bondgame.Bond;
import minigame.stockgame.Stock;
import ui.ResponseManager;

import java.util.ArrayList;
import java.util.List;

public class Asset {
    public static double assetMultiplier = 1.0;
    private static final int FINAL_GOAL = 1000000;
    private static final List<Stock> stockList = new ArrayList<>();
    private static final List<Integer> stockCount = new ArrayList<>();

    private int totalAsset;

    public Asset() {
        this.totalAsset = 5000;
    }

    public Asset(int totalAsset) {
        this.totalAsset = totalAsset;
    }

    public int getAsset() {
        return this.totalAsset;
    }

    public void addAsset(int amount) {
        totalAsset += (int)(amount * assetMultiplier);
    }

    public void addStock(Stock stock, int count) {
        boolean duplication = false;
        int index = -1;
        for (Stock s : stockList) {
            if (s.returnStockName().equals(stock.returnStockName())) {
                duplication = true;
                index = stockList.indexOf(s);
            }
        }
        if (duplication) {
            int oldCount = stockCount.get(index);
            int newCount = count + oldCount;
            stockCount.set(index, newCount);
        } else {
            stockList.add(stock);
            stockCount.add(count);
        }
    }

    public void addCrypto(CryptoCurrency crypto, int count) {

    }

    public void addBond(Bond bond, int count) {

    }

    public void sellStock() {
        if (stockList.isEmpty()) {
            ResponseManager.indentPrint("You have nothing to sell! \n");
        }
        for (Stock s : stockList) {
            int index = stockList.indexOf(s);
            int count = stockCount.get(index);
            int profit = s.investmentGain(count);
            addAsset(count * s.returnStockPrice() + profit);
            ResponseManager.indentPrint("$" + (count * s.returnStockPrice()
                    + profit) + " returned to your account. \n");
        }
        stockList.clear();
    }

    public void deductAsset(int amount) {
        totalAsset -= amount;
    }

    public boolean isAchieved() {
        return totalAsset >= FINAL_GOAL;
    }

    public boolean isBankrupt() {
        if (totalAsset <= 0) {
            ResponseManager.indentPrint("You have gone bankrupt!\n");
            return true;
        }
        return false;
    }

    public int outputAsset() {
        return totalAsset;
    }

    public boolean moreThan(int amount) {
        return totalAsset > amount;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < stockList.size(); i++) {
            output += stockList.get(i).returnStockName() + " currently share count : "
                    + stockCount.get(i) + "\n";
        }
        return String.format("%d, you need %d more to win the game", totalAsset, FINAL_GOAL - totalAsset)
                + "\n" + output;
    }
}
