package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Litecoin implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Litecoin is a peer-to-peer cryptocurrency that enables instant, near-zero cost payments to \n" +
                    "anyone in the world. It's an open-source, global payment network that is fully decentralized \n"+
                    "without any central authorities. Litecoin features faster transaction confirmation times \n" +
                    "and improved storage efficiency than the leading math-based currency. \n" +
                    "It provides return to you every round, but it might be listed as illegal items. \n";
    private static final String NAME = "Litecoin";
    private static final int RISK_FACTOR = 29;
    private static final String HIDDEN_INFO =
            "As digital currencies gain acceptance, Litecoin is positioned to benefit as a lighter, \n" +
                    "more agile alternative to Bitcoin, " +
                    "with its faster processing times making it attractive for everyday transactions. \n";
    private int currentPrice;

    public Litecoin() {
        this.currentPrice = 200;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION + "\n"
            + "Crypto Name: " + NAME + "\n"
            + "Current Price: " + currentPrice + " USD" + "\n");

        if (playerProfile.getOccupation().equals("Artificial intelligence") ||
                playerProfile.getOccupation().equals("Investor")) {
            ResponseManager.indentPrint(HIDDEN_INFO + "\n");
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int changePercentage = random.nextInt(11) - 5;
        int changeAmount = amountInvested * changePercentage / 100;
        this.currentPrice += this.currentPrice * changePercentage / 100;

        // Provide feedback on market changes
        if (changePercentage > 0) {
            System.out.println("Litecoin's value has seen a positive trend, increasing by " + changePercentage + "%.");
        } else if (changePercentage < 0) {
            System.out.println("Litecoin's value has decreased by " + Math.abs(changePercentage) +
                    "%, reflecting recent market volatility.");
        } else {
            System.out.println("The market for Litecoin has remained stable, with no significant price changes.");
        }

        return changeAmount;
    }

    @Override
    public String returnCryptoName() {
        return NAME;
    }

    @Override
    public int returnCurrentPrice() {
        return currentPrice;
    }

    @Override
    public int getRiskFactor() {
        return RISK_FACTOR;
    }
}


