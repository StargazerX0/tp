package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

public class Polkadot implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Polkadot is a sharded multichain network, meaning it can process many transactions " +
                    "on several chains in parallel, eliminating the bottlenecks that occurred on older " +
                    "networks that processed transactions one by one. This parallel processing power " +
                    "improves scalability for chains connected to Polkadot, making it attractive " +
                    "for building and connecting decentralized applications, services, and institutions.";
    private static final String NAME = "Polkadot";
    private static final String HIDDEN_INFO =
            "With its growing ecosystem and the upcoming developments, Polkadot is strategically positioned " +
                    "to play a significant role in the future of decentralized web, potentially leading to " +
                    "increased adoption and investment opportunities.";
    private int currentPrice;

    public Polkadot() {
        this.currentPrice = 30;
    }

    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION);
        ResponseManager.indentPrint("Crypto Name: " + NAME);
        ResponseManager.indentPrint("Current Price: " + currentPrice + " USD");

        // Display hidden information based on certain player conditions or occupations
        if (playerProfile.getOccupation().equals("Blockchain Developer") ||
                playerProfile.getOccupation().equals("Tech Investor")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int change = random.nextInt(8) - 4;
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

