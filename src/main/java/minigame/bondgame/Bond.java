package minigame.bondgame;

import player.PlayerProfile;

/**
 * Defines the essential characteristics and actions that any bond in the EconoCraft game must possess.
 * A bond represents a financial instrument used by players to earn interest over time. This interface
 * outlines the methods required to interact with various types of bonds within the game environment,
 * ensuring consistency and facilitating easy integration of new bond types.
 *
 * Implementing classes should provide specific functionality for each method based on the bond's characteristics.
 */
public interface Bond {

    /**
     * Displays detailed information about this bond to a specific player. The information presented can vary
     * depending on the player's profile, such as occupation or game level, potentially hiding or revealing
     * additional details accordingly.
     *
     * @param playerProfile The profile of the player requesting information about the bond. This parameter is used
     *                      to customize the information output based on player-specific criteria.
     */
    void printInfo(PlayerProfile playerProfile);

    /**
     * Calculates and returns the interest earned on an investment in this bond over a specified period.
     * The calculation depends on the bond's interest rate and the amount of money invested.
     *
     * @param amountInvested The principal amount of money invested in the bond.
     * @return The calculated interest earned on the invested amount as an integer.
     */
    int calculateInterest(int amountInvested);

    /**
     * Retrieves the name of the bond. Each bond type should have a unique name that distinguishes it from others.
     *
     * @return A {@code String} representing the unique name of the bond.
     */
    String returnBondName();

    /**
     * Provides the purchase price for a single unit of this bond. This is the initial investment required to
     * buy one unit of the bond.
     *
     * @return The cost per unit of the bond as an integer.
     */
    int returnBondPrice();

    /**
     * Retrieves the annual interest rate for the bond. The interest rate is a crucial factor in calculating
     * the return on investment for the bondholder.
     *
     * @return The annual interest rate of the bond as a percentage, represented as an integer.
     */
    int returnBondInterestRate();

}


