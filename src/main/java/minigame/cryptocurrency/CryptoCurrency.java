package minigame.cryptocurrency;

import player.PlayerProfile;

/**
 * Represents the fundamental characteristics and actions required for cryptocurrency objects within the game.
 * This interface ensures that all cryptocurrencies share a common set of behaviors for displaying information,
 * calculating value changes, and providing basic cryptocurrency data. Implementing this interface allows for
 * consistent interaction with different types of cryptocurrencies in the game's economy system.
 */
public interface CryptoCurrency {

    /**
     * Presents detailed information about this cryptocurrency, tailored to the player's profile.
     * This can include general market status, specific value predictions, or other data relevant to
     * the player's in-game decisions. The display may vary based on the player's occupation or other
     * attributes.
     *
     * @param playerProfile The profile of the player requesting information. This parameter is used
     *                      to customize the information output to the player's characteristics.
     */
    public void printInfo(PlayerProfile playerProfile);

    /**
     * Calculates and returns the expected change in value of an investment in this cryptocurrency,
     * simulating the effect of market fluctuations. This method provides a way to estimate the potential
     * return or loss on an investment amount over a given period.
     *
     * @param amountInvested The amount of USD invested in the cryptocurrency.
     * @return The estimated change in the value of the investment, represented as an integer.
     */
    public int calculateChange(int amountInvested);

    /**
     * Retrieves the name of the cryptocurrency. Each cryptocurrency should have a unique name
     * that identifies it within the game.
     *
     * @return The name of the cryptocurrency as a String.
     */
    public String returnCryptoName();

    /**
     * Gets the current market price of the cryptocurrency. This is the price at which the cryptocurrency
     * can currently be bought or sold within the game's economy.
     *
     * @return The current market price as an integer.
     */
    public int returnCurrentPrice();

    /**
     * Evaluates and returns the risk factor associated with the cryptocurrency. The risk factor
     * is an integer that represents the volatility and unpredictability of the cryptocurrency's
     * value, with higher values indicating greater risk.
     *
     * @return The risk factor of the cryptocurrency as an integer.
     */
    public int getRiskFactor();
}

