package minigame.cryptocurrency;

import player.PlayerProfile;

/**
 * The CryptoCurrency interface defines the essential functionalities
 * that all cryptocurrency objects must implement in the game.
 */
public interface CryptoCurrency {

    /**
     * Displays information about the cryptocurrency, including its current market status
     * and any special information relevant to the player's occupation.
     *
     * @param playerProfile The player's profile, used to customize the information displayed.
     */
    public void printInfo(PlayerProfile playerProfile);

    /**
     * Calculates the change in the cryptocurrency's value based on an amount invested.
     * This simulates market fluctuations and their impact on the investment.
     *
     * @param amountInvested The amount of USD invested in the cryptocurrency.
     * @return The change in investment value as an int.
     */
    public int calculateChange(int amountInvested);

    /**
     * Returns the name of the cryptocurrency.
     *
     * @return The name as a String.
     */
    public String returnCryptoName();

    /**
     * Returns the current market price of the cryptocurrency.
     *
     * @return The current price as an int.
     */
    public int returnCurrentPrice();
}

