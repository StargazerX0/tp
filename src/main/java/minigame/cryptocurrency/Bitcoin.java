package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Bitcoin implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Bitcoin, as the first decentralized digital currency, has led the way in blockchain technology. " +
                    "It offers a peer-to-peer system without a central authority, " +
                    "making it a revolutionary approach to currency.";
    private static final String NAME = "Bitcoin";
    private static final String HIDDEN_INFO =
            "Recent market trends and increasing adoption by " +
                    "financial institutions may significantly boost Bitcoin's value.";
    private int currentPrice;

    public Bitcoin() {
        this.currentPrice = 10000;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION);
        ResponseManager.indentPrint("Crypto Name: " + NAME);
        ResponseManager.indentPrint("Current Price: " + currentPrice + " USD");

        if (playerProfile.getOccupation().equals("Blockchain Developer") ||
                playerProfile.getOccupation().equals("Crypto Trader")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(20) - 10;
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

