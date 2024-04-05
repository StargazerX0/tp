package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Ripple implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Ripple is a digital payment protocol more than just a cryptocurrency, " +
                    "designed for fast, cheap, and secure cross-border payments. It operates on a decentralized " +
                    "open-source protocol and supports tokens representing fiat currency, cryptocurrency, " +
                    "commodities, or other units of value such as frequent flier miles or mobile minutes.";
    private static final String NAME = "Ripple";
    private static final String HIDDEN_INFO =
            "Given its strong focus on integration with banking and money transfer systems, " +
                    "Ripple has the potential to revolutionize international finance " +
                    "if adopted widely by financial institutions.";
    private int currentPrice;

    public Ripple() {
        this.currentPrice = 1;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION);
        ResponseManager.indentPrint("Crypto Name: " + NAME);
        ResponseManager.indentPrint("Current Price: " + currentPrice + " USD");

        // Display hidden information based on certain player conditions or occupations
        if (playerProfile.getOccupation().equals("Financial Analyst") ||
                playerProfile.getOccupation().equals("Banker")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(5) - 2;
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

