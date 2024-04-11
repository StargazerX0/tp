package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

/**
 * Represents the Bitcoin cryptocurrency in the game, including its market behavior
 * and interaction with the player.
 */
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

    /**
     * Constructs a Bitcoin instance with an initial market price.
     */
    public Bitcoin() {
        this.currentPrice = 10000;
    }

    /**
     * Displays information about Bitcoin to the player, including its name, current market price,
     * and any special information based on the player's profile.
     *
     * @param playerProfile The player's profile, used to determine if additional info should be shown.
     */
    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION);
        ResponseManager.indentPrint("Crypto Name: " + NAME);
        ResponseManager.indentPrint("Current Price: " + currentPrice + " USD");

        if (playerProfile.getOccupation().equals("Artificial intelligence")) {
            ResponseManager.indentPrint(HIDDEN_INFO);
        }
    }

    /**
     * Simulates market changes affecting Bitcoin's value based on an amount invested by the player.
     * This method randomly generates a market change and applies it to the current price.
     *
     * @param amountInvested The amount of USD invested by the player in Bitcoin.
     * @return The net change in the player's investment as a result of market fluctuations.
     */
    @Override
    public int calculateChange(int amountInvested) {
        Random random = new Random();
        int changePercentage = random.nextInt(21) - 10;
        int changeAmount = amountInvested * changePercentage / 100;
        this.currentPrice += this.currentPrice * changePercentage / 100;

        if (changePercentage > 0) {
            System.out.println("Positive news has increased the value of Bitcoin by " + changePercentage + "%.");
        } else if (changePercentage < 0) {
            System.out.println("Negative news has decreased the value of Bitcoin by " +
                    Math.abs(changePercentage) + "%.");
        } else {
            System.out.println("The market for Bitcoin remains stable, with no change in price.");
        }

        return changeAmount;
    }

    /**
     * Returns the name of the cryptocurrency.
     *
     * @return The name of the cryptocurrency, "Bitcoin".
     */
    @Override
    public String returnCryptoName() {
        return NAME;
    }

    /**
     * Returns the current market price of Bitcoin.
     *
     * @return The current market price of Bitcoin.
     */
    @Override
    public int returnCurrentPrice() {
        return currentPrice;
    }
}


