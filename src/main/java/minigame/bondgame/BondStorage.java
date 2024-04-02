package minigame.bondgame;

import exception.GameException;
import player.PlayerProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BondStorage {
    private final List<Bond> bondsAvailable = new ArrayList<>();
    private boolean completeTrade = false;
    private final PlayerProfile playerProfile;

    public BondStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
        setUp();
    }

    private void setUp() {
        bondsAvailable.add(new GovernmentStabilityBond());
        bondsAvailable.add(new CorporateGrowthBond());
        bondsAvailable.add(new HighYieldBond());
        bondsAvailable.add(new InflationLinkedBond());
        bondsAvailable.add(new MunicipalBond());
        bondsAvailable.add(new ZeroCouponBond());
    }

    public void play() throws GameException {
        if (bondsAvailable.isEmpty()) {
            setUp();
        }
        Scanner scanner = new Scanner(System.in);
        int index = getRandomNumber(0, bondsAvailable.size() - 1);
        Bond current = bondsAvailable.get(index);
        current.printInfo(playerProfile);
    
        while (!completeTrade) {
            try {
                System.out.println("How many units of " + current.returnBondName() + " do you want to purchase? Input 0 if you want none");
                int response = Integer.parseInt(scanner.nextLine());
                if (response < 0) {
                    throw new GameException("Invalid input: Please input a number greater than or equal to 0");
                }
                if (response == 0) {
                    completeTrade = true;
                    System.out.println("No bonds purchased.");
                } else {
                    int cost = response * current.returnBondPrice();
                    if (playerProfile.getAsset().getAsset() >= cost) {
                        playerProfile.getAsset().deductAsset(cost);
                        playerProfile.getAsset().addBond(current, response);
                        completeTrade = true;
                        System.out.println("You've successfully purchased " + response + " units of " + current.returnBondName() + ".");
                    } else {
                        throw new GameException("Insufficient funds: Your current assets cannot afford this many bonds.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please enter a positive integer.");
            } catch (GameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
