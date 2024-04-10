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

    private static final List<Bond> bondList = new ArrayList<>();

    private static final List<Integer> bondCount = new ArrayList<>();

    private List<CryptoCurrency> cryptoList = new ArrayList<>();

    private List<Integer> cryptoCount = new ArrayList<>();

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

    public void addBond(Bond bond, int count) {
        boolean duplication = false;
        int index = -1;
        for (Bond b : bondList) {
            if (b.returnBondName().equals(bond.returnBondName())) {
                duplication = true;
                index = bondList.indexOf(b);
                break;
            }
        }
        if (duplication) {
            int oldCount = bondCount.get(index);
            int newCount = count + oldCount;
            bondCount.set(index, newCount);
        } else {
            bondList.add(bond);
            bondCount.add(count);
        }
    }

    public void sellBond() {
        if (bondList.isEmpty()) {
            ResponseManager.indentPrint("You have no bonds to sell! \n");
            return;
        }
        for (Bond b : bondList) {
            int index = bondList.indexOf(b);
            int count = bondCount.get(index);
            int bondPrice = b.returnBondPrice();
            int totalPrincipal = bondPrice * count;
            double totalInterest = totalPrincipal * b.returnBondInterestRate() / 100.0;
            int totalReturn = (int) (totalPrincipal + totalInterest);
            addAsset(totalReturn);
            ResponseManager.indentPrint("$" + totalReturn + " returned to your account from "
                    + b.returnBondName() + ". \n");
        }
        bondList.clear();
        bondCount.clear();
    }

    public void addCrypto(CryptoCurrency crypto, int dollarsInvested) {
        int quantity = dollarsInvested / crypto.returnCurrentPrice();
        int index = cryptoList.indexOf(crypto);
        if (index != -1) {
            cryptoCount.set(index, cryptoCount.get(index) + quantity);
        } else {
            cryptoList.add(crypto);
            cryptoCount.add(quantity);
        }
    }

    public void sellCrypto() {
        if (cryptoList.isEmpty()) {
            ResponseManager.indentPrint("You do not own any cryptocurrency to sell.\n");
            return;
        }
        int totalReturn = 0;
        for (int i = 0; i < cryptoList.size(); i++) {
            CryptoCurrency crypto;
            crypto = cryptoList.get(i);
            int quantity = cryptoCount.get(i);
            int investmentReturn = quantity * crypto.returnCurrentPrice();
            totalReturn += investmentReturn;
            ResponseManager.indentPrint("Sold " + quantity + " units of " + crypto.returnCryptoName() + "," +
                    " returning $" + investmentReturn + " to your account.\n");
        }
        addAsset(totalReturn);
        cryptoList.clear();
        cryptoCount.clear();
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

    public int outputMoney() {
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
