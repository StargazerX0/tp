package minigame.bondgame;
import exception.GameException;
import player.PlayerProfile;
import ui.ResponseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the storage and interaction of bond options in the EconoCraft game, providing functionalities for
 * the player to view and purchase available bonds. It maintains a list of bonds that can be bought by players,
 * processes player inputs for buying bonds, and updates player profiles based on the transactions.
 */
public class BondStorage {
    private final List<Bond> bondsAvailable = new ArrayList<>();
    private boolean completeTrade = false;
    private boolean completeSelection = false;
    private int bondChoice = -1;
    private final PlayerProfile playerProfile;

    /**
     * Constructs a BondStorage instance associated with a given player profile, initializing the list of
     * available bonds for purchase.
     *
     * @param playerProfile The profile of the player engaging in bond transactions.
     */
    public BondStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        setUp();
    }

    /**
     * Populates the list of available bonds. This setup method is called upon instance creation,
     * filling the internal storage with predefined bond types.
     */
    private void setUp() {
        bondsAvailable.add(new GovernmentStabilityBond());
        bondsAvailable.add(new CorporateGrowthBond());
        bondsAvailable.add(new HighYieldBond());
        bondsAvailable.add(new InflationLinkedBond());
    }

    /**
     * Manages the interactive bond-buying process. It displays the available bonds to the player,
     * processes their selections, and updates their profile upon successful transactions. The method
     * continues to run until the player decides to exit or completes a purchase.
     *
     * @throws GameException if an invalid input is provided or if there's an issue completing the purchase.
     */
    public void play() throws GameException {
        Scanner scanner = new Scanner(System.in);
        while (!completeTrade) {
            if (!completeSelection) {
                if (bondsAvailable.isEmpty()) {
                    setUp();
                }
                System.out.println("Select a bond to purchase:\n");
                for (int i = 0; i < bondsAvailable.size(); i++) {
                    System.out.println(i + 1 + ": " + bondsAvailable.get(i).returnBondName());
                }
                System.out.println("Enter the number of the bond you wish to purchase, or 0 to exit:");
            }
            try {
                if (!completeSelection) {
                    bondChoice = Integer.parseInt(scanner.nextLine()) - 1;
                }

                if (bondChoice >= 0 && bondChoice < bondsAvailable.size()) {
                    Bond current = bondsAvailable.get(bondChoice);
                    if (!completeSelection) {
                        current.printInfo(playerProfile);
                    }
                    completeSelection = true;

                    System.out.println("How many units of " + current.returnBondName() +
                            " do you want to purchase? Input 0 if you want none");
                    int response = Integer.parseInt(scanner.nextLine());
                    if (response < 0) {
                        throw new GameException("Invalid input: Please input a number greater than or equal to 0");
                    }
                    if (response == 0) {
                        completeTrade = true;
                        System.out.println("No bonds purchased.");
                    } else {
                        int cost = response * current.returnBondPrice();
                        double totalInterest = current.calculateInterest(cost);
                        if (playerProfile.getAsset().outputMoney() >= cost) {
                            playerProfile.getAsset().deductAsset(cost);
                            playerProfile.getAsset().addBond(current, response);
                            completeTrade = true;
                            System.out.println("You've successfully purchased " + response + " units of " +
                                    current.returnBondName() +
                                    ". Expected total interest gain is $"
                                    + String.format("%.2f", totalInterest) + ".");
                        } else {
                            throw new GameException("Insufficient funds: " +
                                    "Your current assets cannot afford this many bonds.");
                        }
                    }
                } else if (bondChoice == -1) {
                    System.out.println("Exiting the bond purchase interface.");
                    completeTrade = true;
                } else if (bondChoice >= bondsAvailable.size()) {
                    ResponseManager.indentPrint("Invalid selection. Please select a valid bond. \n");
                }

            } catch (NumberFormatException | GameException e) {
                ResponseManager.indentPrint("Please enter a valid input!\n");
            }
        }
    }
}


