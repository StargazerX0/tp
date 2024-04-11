package minigame.bondgame;

import player.PlayerProfile;

/**
 * Represents a bond in the EconoCraft game. This interface defines the necessary methods
 * that all types of bonds must implement to be interacted with within the game.
 */

public interface Bond {


    /**
     * Prints detailed information about the bond to the player.
     *
     * @param playerProfile The profile of the player interacting with the bond.
     *                      This may influence the information displayed (e.g., hidden info for certain occupations).
     */
    public void printInfo(PlayerProfile playerProfile);

    /**
     * Calculates the interest earned on an amount invested in this bond.
     *
     * @param amountInvested The amount of money invested.
     * @return The amount of interest earned.
     */
    public int calculateInterest(int amountInvested);

    /**
     * Returns the name of the bond.
     *
     * @return The bond's name.
     */
    public String returnBondName();

    /**
     * Returns the purchase price of a unit of the bond.
     *
     * @return The price per unit of the bond.
     */
    public int returnBondPrice();

    /**
     * Returns the annual interest rate of the bond.
     *
     * @return The bond's interest rate.
     */
    public int returnBondInterestRate();

}

