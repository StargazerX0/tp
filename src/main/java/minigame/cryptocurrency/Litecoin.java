package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Litecoin implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Litecoin is a peer-to-peer cryptocurrency that enables instant, near-zero cost payments " +
                    "to anyone in the world. It's an open-source, global payment network that is fully decentralized " +
                    "without any central authorities. Litecoin features faster transaction confirmation times " +
                    "and improved storage efficiency than the leading math-based currency.";
    private static final String NAME = "Litecoin";
    private static final String HIDDEN_INFO =
            "As digital currencies gain acceptance, Litecoin is positioned to benefit as a lighter, " +
                    "more agile alternative to Bitcoin, " +
                    "with its faster processing times making it attractive for everyday transactions.";
    private int currentPrice;

    public Litecoin() {
        this.currentPrice = 200;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION);
        ResponseManager.indentPrint("Crypto Name: " + NAME);
        ResponseManager.indentPrint("Current Price: " + currentPrice + " USD");

        // Display hidden information based on certain player conditions or occupations
        if (playerProfile.getOccupation().equals("Crypto Enthusiast") ||
                playerProfile.getOccupation().equals("Investor")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(10) - 5;
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

