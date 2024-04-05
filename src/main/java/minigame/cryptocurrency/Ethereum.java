package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Ethereum implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Ethereum is a decentralized, open-source blockchain system that features its own " +
                    "cryptocurrency, Ether. ETH works as a platform for numerous other cryptocurrencies, " +
                    "as well as for the execution of decentralized smart contracts.";
    private static final String NAME = "Ethereum";
    private static final String HIDDEN_INFO =
            "With the upcoming transition to Ethereum 2.0 and the shift to proof-of-stake, " +
                    "Ethereum aims to become more scalable, sustainable, and secure. This could potentially " +
                    "increase its adoption and value significantly.";
    private int currentPrice;

    public Ethereum() {
        this.currentPrice = 2500;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION);
        ResponseManager.indentPrint("Crypto Name: " + NAME);
        ResponseManager.indentPrint("Current Price: " + currentPrice + " USD");

        // Display hidden information based on certain player conditions or occupations
        if (playerProfile.getOccupation().equals("Blockchain Developer")
                || playerProfile.getOccupation().equals("Crypto Investor")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(15) - 7;
        this.currentPrice += change;
        return amountInvested * change / 100;
    }

    @Override
    public String returnCryptoName() {
        return NAME;
    }

    @Override
    public int returnCurrentPrice() {
        return currentPrice;
    }
}

