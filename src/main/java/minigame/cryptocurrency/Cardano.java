package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Cardano implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Cardano is a blockchain platform for changemakers, innovators, and visionaries, " +
                    "offering tools and technologies required to create possibility for the many, " +
                    "as well as the few, and bring about positive global change.";
    private static final String NAME = "Cardano";
    private static final String HIDDEN_INFO =
            "With its unique dual-layer architecture and focus on sustainability, scalability, and transparency, " +
                    "Cardano is positioned to overcome common blockchain challenges " +
                    "and might see significant growth in utility and value.";
    private int currentPrice;

    public Cardano() {
        this.currentPrice = 2;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION);
        ResponseManager.indentPrint("Crypto Name: " + NAME);
        ResponseManager.indentPrint("Current Price: " + currentPrice + " USD");

        if (playerProfile.getOccupation().equals("Blockchain Developer") ||
                playerProfile.getOccupation().equals("Crypto Analyst")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(4) - 2;
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
