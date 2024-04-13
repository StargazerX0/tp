package player;

import minigame.bondgame.Bond;
import minigame.cryptocurrency.CryptoCurrency;
import minigame.stockgame.Stock;
import ui.ResponseManager;

import java.util.ArrayList;
import java.util.List;

import static ui.ResponseManager.indentPrint;
import static ui.ResponseManager.RED;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.YELLOW;
import static ui.ResponseManager.RESET;
import static ui.ResponseManager.INDENTATION;


/**
 * Represents the financial assets of a player in the game. This includes various investments like stocks, bonds,
 * and cryptocurrencies. Provides methods to manage these assets, including buying, selling, and tracking total value.
 */
public class Asset {
    public static final int PERCENT_RATIO = 100;
    public static double ASSET_MULTIPLIER = 1.0;
    public static int RISK_FACTOR = 0;
    private static final int FINAL_GOAL = 900000;
    private static List<Stock> stockList = new ArrayList<>();
    private static  List<Integer> stockCount = new ArrayList<>();
    private static List<Bond> bondList = new ArrayList<>();
    private static List<Integer> bondCount = new ArrayList<>();
    private List<CryptoCurrency> cryptoList = new ArrayList<>();
    private List<Integer> cryptoCount = new ArrayList<>();
    private int totalAsset;

    public Asset() {
        this.totalAsset = 5000;
    }

    public Asset(int totalAsset) {
        this.totalAsset = totalAsset;
    }

    public static void setStockList(List<Stock> stockList) {
        Asset.stockList = stockList;
    }

    public static void setStockCount(List<Integer> stockCount) {
        Asset.stockCount = stockCount;
    }

    public static void setBondList(List<Bond> bondList) {
        Asset.bondList = bondList;
    }

    public static void setBondCount(List<Integer> bondCount) {
        Asset.bondCount = bondCount;
    }

    public void setCryptoList(List<CryptoCurrency> cryptoList) {
        this.cryptoList = cryptoList;
    }

    public void setCryptoCount(List<Integer> cryptoCount) {
        this.cryptoCount = cryptoCount;
    }
    public List<Bond> getBondList() {
        return bondList;
    }

    public List<Integer> getBondCount() {
        return bondCount;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public List<Integer> getStockCount() {
        return stockCount;
    }

    public List<CryptoCurrency> getCryptoList() {
        return cryptoList;
    }

    public List<Integer> getCryptoCount() {
        return cryptoCount;
    }


    public void addAsset(int amount) {
        if (amount <= 0) {
            return;
        }
        int actualAmount = (int) (amount * ASSET_MULTIPLIER);
        int multiPercentage = (int) (ASSET_MULTIPLIER * PERCENT_RATIO);
        String color = ASSET_MULTIPLIER >= 1.0 ? GREEN : RED;

        totalAsset += actualAmount;
        indentPrint(String.format("$%s%d%s has been added to ur asset - Detail: %s(%d * %d%%)%s.\n" +
                        "Your total asset is now $%d.\n",
               GREEN, actualAmount, RESET, color, amount, multiPercentage, RESET, totalAsset));
    }

    public void deductAsset(int amount) {
        if (amount <= 0) {
            return;
        }
        totalAsset -= amount;
        indentPrint(String.format("$%s%d%s has been deducted from ur asset.\n Your total asset is now $%d.\n",
                RED, amount, RESET, totalAsset));
    }

    /**
     * Adds a specified number of stocks to the player's portfolio.
     *
     * @param stock The stock to add.
     * @param count The number of shares to add.
     */
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
        }
        stockList.clear();
        stockCount.clear();
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

    public int bondReturn() {
        if (bondList.isEmpty()) {
            return 0;
        }
        int totalReturn = 0;
        for (Bond b : bondList) {
            int index = bondList.indexOf(b);
            int count = bondCount.get(index);
            int bondPrice = b.returnBondPrice();
            int totalPrincipal = bondPrice * count;
            double totalInterest = totalPrincipal * b.returnBondInterestRate() / 100.0;
            totalReturn += (int) (totalInterest);
        }
        return totalReturn;
    }

    public void addCrypto(CryptoCurrency crypto, int dollarsInvested) {
        int quantity = dollarsInvested / crypto.returnCurrentPrice();
        int index = cryptoList.indexOf(crypto);
        if (index != -1) {
            cryptoCount.set(index, cryptoCount.get(index) + quantity);
        } else {
            RISK_FACTOR = (RISK_FACTOR * cryptoList.size() + crypto.getRiskFactor())/(cryptoList.size() + 1);
            cryptoList.add(crypto);
            cryptoCount.add(quantity);
        }
    }

    public int cryptoReturn() {
        if (cryptoList.isEmpty()) {
            return 0;
        }
        if (getRandomNumber(0, 100) < RISK_FACTOR) {
            ResponseManager.indentPrint("Unfortunately, Government intervention causes all of your " +
                    "cryptos to be listed as illegal items\n" +
                    "All of your cryptos have been confiscated :(\n");
            cryptoList.clear();
            cryptoCount.clear();
            return 0;
        }
        int totalReturn = 0;
        for (int i = 0; i < cryptoList.size(); i++) {
            CryptoCurrency crypto;
            crypto = cryptoList.get(i);
            int quantity = cryptoCount.get(i);
            int investmentReturn = quantity * crypto.returnCurrentPrice();
            totalReturn += investmentReturn;
        }
        return totalReturn;
    }

    public boolean isAchieved() {
        return totalAsset >= FINAL_GOAL;
    }

    public boolean isBankrupt() {
        return totalAsset <= 0;
    }

    public int getAsset() {
        return totalAsset;
    }

    public boolean moreThan(int amount) {
        return totalAsset > amount;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < stockList.size(); i++) {
            int stockNum = stockCount.get(i);
            output += stockNum == 0 ? "" :
                    stockList.get(i).returnStockName() + " current share count : "
                    + stockNum + "\n";
        }
        for (int i = 0; i < bondList.size(); i++) {
            int bondNum = bondCount.get(i);
            output += bondNum == 0 ? "" :
                    bondList.get(i).returnBondName() + " current bond count : "
                    + bondNum + "\n";
        }
        for (int i = 0; i < cryptoList.size(); i++) {
            int cryptoNum = cryptoCount.get(i);
            output += cryptoNum == 0 ? "" :
                    cryptoList.get(i).returnCryptoName() + " current crypto count : "
                    + cryptoNum + "\n";
        }

        String investmentInfo = output.isEmpty() ? output :
                INDENTATION + "\n" +
                YELLOW + "Your current investments are: \n" + RESET + output;
        String reminder = totalAsset <= 1000 ? RED + " You are running low on cash!\n" + RESET : "";

        return totalAsset + reminder + "\n" +
                String.format("you need $%s%d%s more to win the game\n", YELLOW, FINAL_GOAL - totalAsset, RESET) +
                investmentInfo;
    }
}
