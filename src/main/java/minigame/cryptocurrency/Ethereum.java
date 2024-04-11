package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Ethereum implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Ethereum is a decentralized, open-source blockchain system that features its own \n" +
                    "cryptocurrency, Ether. ETH works as a platform for numerous other cryptocurrencies, \n" +
                    "as well as for the execution of decentralized smart contracts. \n";
    private static final int RISK_FACTOR = 11;
    private static final String NAME = "Ethereum";
    private static final String HIDDEN_INFO =
            "With the upcoming transition to Ethereum 2.0 and the shift to proof-of-stake, \n" +
                    "Ethereum aims to become more scalable, sustainable, and secure. This could potentially \n" +
                    "increase its adoption and value significantly. \n" +
                    "It provides return to you every round, but it might be listed as illegal items. \n";
    private int currentPrice;

    public Ethereum() {
        this.currentPrice = 2500;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION + "\n"
            + "Crypto Name: " + NAME + "\n"
            + "Current Price: " + currentPrice + " USD" + "\n");

        if (playerProfile.getOccupation().equals("Semi-conductor")
                || playerProfile.getOccupation().equals("Crypto Investor")) {
            ResponseManager.indentPrint(HIDDEN_INFO + "\n");
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int changePercentage = random.nextInt(15) - 7;
        int updatedPrice = currentPrice + (currentPrice * changePercentage / 100);
        int changeAmount = amountInvested * changePercentage / 100;

        this.currentPrice = updatedPrice;

        if (changePercentage > 0) {
            System.out.println("Ethereum has shown a positive movement, increasing by " + changePercentage + "%.");
        } else if (changePercentage < 0) {
            System.out.println("Ethereum's value has decreased by " + Math.abs(changePercentage) +
                    "% due to market fluctuations.");
        } else {
            System.out.println("The market for Ethereum has remained stable with no significant price changes.");
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

