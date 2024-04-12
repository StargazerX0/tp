package minigame.cryptocurrency;

import player.PlayerProfile;
import ui.ResponseManager;
import java.util.Random;

/**
 * Represents Bitcoin, the pioneering cryptocurrency, within the game's financial ecosystem.
 * This class embodies Bitcoin's core characteristics, including its market volatility and
 * potential for high returns, reflecting the real-world complexities and opportunities
 * presented by cryptocurrency investments. It also incorporates a unique risk factor and
 * provides dynamic interaction based on the player's profile and game events.
 */
public class Bitcoin implements CryptoCurrency {
    private static final String CRYPTO_INFORMATION =
            "Bitcoin, as the first decentralized digital currency, has led the way in blockchain technology. \n" +
                    "It offers a peer-to-peer system without a central authority, \n" +
                    "making it a revolutionary approach to currency. \n" +
                    "It provides return to you every round, but it might be listed as illegal items.\n";
    private static final int RISK_FACTOR = 55;
    private static final String NAME = "Bitcoin";
    private static final String HIDDEN_INFO =
            "Recent market trends and increasing adoption by \n" +
                    "financial institutions may significantly boost Bitcoin's value. \n";
    private int currentPrice;

    /**
     * Initializes a new instance of Bitcoin with a default market price, setting the stage for
     * in-game investment and trading.
     */
    public Bitcoin() {
        this.currentPrice = 10000;
    }

    /**
     * Provides a comprehensive overview of Bitcoin, including general information and current
     * market status. If the player's profile meets certain criteria (e.g., specific occupations),
     * additional insights may be revealed, simulating the access to privileged information in
     * investment scenarios.
     *
     * @param playerProfile The profile of the player interacting with Bitcoin, used to tailor the information provided.
     */
    @Override
    public void printInfo(PlayerProfile playerProfile) {
        ResponseManager.indentPrint(CRYPTO_INFORMATION + "\n"
            + "Crypto Name: " + NAME + "\n"
            + "Current Price: " + currentPrice + " USD" + "\n");

        if (playerProfile.getOccupation().equals(" Artificial intelligence ")) {
            ResponseManager.indentPrint(HIDDEN_INFO + "\n");
        }
    }

    /**
     * Simulates the impact of market fluctuations on Bitcoin's value based on a player's investment.
     * The method calculates changes in investment value using a randomized model to reflect the
     * unpredictability of real-world markets.
     *
     * @param amountInvested The USD amount invested by the player in Bitcoin.
     * @return The net change in investment value, positive or negative, as a result of market movement.
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
     * Returns the name of this cryptocurrency, identifying it uniquely within the game's economy.
     * This method provides a straightforward way to retrieve the cryptocurrency's name, which is
     * essential for player interactions and in-game displays.
     *
     * @return The name of the cryptocurrency, "Bitcoin".
     */
    @Override
    public String returnCryptoName() {

        return NAME;
    }

    /**
     * Provides the current market price of Bitcoin, reflecting its value at the moment within the game.
     * This price is crucial for calculations related to buying, selling, or investing in Bitcoin,
     * and it may fluctuate based on simulated market conditions or player actions.
     *
     * @return The current market price of Bitcoin in USD.
     */
    @Override
    public int returnCurrentPrice() {

        return currentPrice;
    }

    /**
     * Determines and returns the risk factor associated with investing in Bitcoin, as represented
     * within the game. The risk factor is an indicator of the investment's volatility and potential
     * for significant value changes, providing players with insight into the investment's stability.
     *
     * @return The risk factor of Bitcoin, reflecting its volatility and investment risk.
     */
    @Override
    public int getRiskFactor() {

        return RISK_FACTOR;
    }
}


