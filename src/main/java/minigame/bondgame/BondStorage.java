package minigame.bondgame;

import exception.GameException;
import player.PlayerProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the available bonds and handles player interactions for buying bonds in the EconoCraft game.
 * This class is responsible for displaying bond options to the player and processing their choices.
 */
public class BondStorage {
    private final List<Bond> bondsAvailable = new ArrayList<>();
    private boolean completeTrade = false;
    private final PlayerProfile playerProfile;

    public BondStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        setUp();
    }

    /**
     * Initializes the list of available bonds. This method is called upon the creation
     * of the BondStorage instance and populates the list with various types of bonds.
     */
    private void setUp() {
        bondsAvailable.add(new GovernmentStabilityBond());
        bondsAvailable.add(new CorporateGrowthBond());
        bondsAvailable.add(new HighYieldBond());
        bondsAvailable.add(new InflationLinkedBond());
    }

    /**
     * Executes the bond-buying process, allowing the player to select and purchase bonds.
     * This method displays available bonds and handles the player's input to complete the purchase.
     *
     * @throws GameException if there is an issue during the bond-buying process.
     */
    public void play() throws GameException {
        if (bondsAvailable.isEmpty()) {
            setUp();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a bond to purchase:");
        for (int i = 0; i < bondsAvailable.size(); i++) {
            System.out.println(i + 1 + ": " + bondsAvailable.get(i).returnBondName());
        }
        System.out.println("Enter the number of the bond you wish to purchase, or 0 to exit:");

        int bondChoice = Integer.parseInt(scanner.nextLine()) - 1;
        if (bondChoice >= 0 && bondChoice < bondsAvailable.size()) {
            Bond current = bondsAvailable.get(bondChoice);
            current.printInfo(playerProfile);

            while (!completeTrade) {
                try {
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
                        if (playerProfile.getAsset().getAsset() >= cost) {
                            playerProfile.getAsset().deductAsset(cost);
                            playerProfile.getAsset().addBond(current, response);
                            completeTrade = true;
                            System.out.println("You've successfully purchased " + response + " units of " +
                                    current.returnBondName() +
                                    ". Expected total interest gain after " + current.returnBondMaturity() +
                                    " years is $" + String.format("%.2f", totalInterest) + ".");
                            String activityDescription = "Purchased " + response + " units of "
                                    + current.returnBondName() + " for $" + cost + ". Expected interest: $"
                                    + String.format("%.2f", totalInterest);
                            playerProfile.recordFinancialActivity("Bond Purchase", activityDescription, -cost);
                        } else {
                            throw new GameException("Insufficient funds: " +
                                    "Your current assets cannot afford this many bonds.");
                        }
                    }
                } catch (NumberFormatException | GameException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (bondChoice != -1) {
            System.out.println("Invalid selection. Please select a valid bond.");
            play();
        }
    }

}

