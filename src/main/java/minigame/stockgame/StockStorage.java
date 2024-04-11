package minigame.stockgame;

import exception.GameException;
import player.PlayerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the storage and interaction with stocks in the stock market mini-game.
 * This class initializes available stocks and handles player interactions for buying stocks.
 */
public class StockStorage {
    private final List<Stock> stocksAvailable = new ArrayList<>();
    private boolean completeTrade = false;
    private final PlayerProfile playerProfile;

    /**
     * Initializes a new stock storage with a given player profile.
     *
     * @param playerProfile The player profile associated with stock transactions.
     */
    public StockStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    private void setUp() {
        stocksAvailable.add(new StockOne());
        stocksAvailable.add(new StockTwo());
        stocksAvailable.add(new StockThree());
        stocksAvailable.add(new StockFour());
        stocksAvailable.add(new StockFive());
        stocksAvailable.add(new StockSix());
        stocksAvailable.add(new StockSeven());
        stocksAvailable.add(new StockEight());
        stocksAvailable.add(new StockNine());
        stocksAvailable.add(new StockTen());
    }

    /**
     * Sets up available stocks and starts the stock trading process.
     *
     * @throws GameException If an error occurs during the stock trading process.
     */
    public void play() throws GameException {
        if (stocksAvailable.isEmpty()) {
            setUp();
        }
        int index = getRandomNumber(0, stocksAvailable.size() - 1);
        Stock current = stocksAvailable.get(index);
        current.printInfo(playerProfile);
        stockCalculation(index, current);

    }

    /**
     * Handles the logic for stock purchase calculations and updates the player's profile accordingly.
     *
     * @param index   Index of the current stock.
     * @param current The current stock being traded.
     * @throws GameException If invalid stock quantities are provided or player assets are insufficient.
     */
    private void stockCalculation(int index, Stock current) throws GameException {
        Scanner scanner = new Scanner(System.in);
        while (!completeTrade) {
            try {
                System.out.println("How many stock do you want to purchase? Input 0 if you want none. " +
                        "Maximum purchase is 100000 stocks");
                int response = Integer.parseInt(scanner.nextLine());
                if (response < 0 || response > 100000) {
                    throw new GameException("Please input a number greater than 0 or less " +
                            "than 100000 if you want to purchase stocks");
                } else if (response == 0) {
                    completeTrade = true;
                    System.out.println("No stocks purchased.");
                } else {
                    completeTrade = true;
                }

                if ((response * current.returnStockPrice()) > playerProfile.getAsset().getAsset()) {
                    throw new GameException("Your current asset cannot afford this many stock.");
                } else {
                    playerProfile.getAsset().deductAsset(response * current.returnStockPrice());
                    playerProfile.getAsset().addStock(current, response);
                    completeTrade = true;
                    System.out.println("You've successfully purchased " + response + " units of "
                            + current.returnStockName() + ".");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please enter an integer between 0 and 100000.");
            } catch (GameException e) {
                System.out.println(e.getMessage());
                completeTrade = false;
            }
        }
    }

    /**
     * Generates a random number within a given range, inclusive of the minimum and exclusive of the maximum.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random number within the specified range.
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
