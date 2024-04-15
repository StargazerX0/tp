package minigame.stockgame;

import exception.GameException;
import player.PlayerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the initialization, storage, and purchase of stocks within the virtual stock market.
 */
public class StockStorage {
    private final List<Stock> stocksAvailable = new ArrayList<>();
    private boolean completeTrade = false;
    private final PlayerProfile playerProfile;

    public StockStorage(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    /**
     * Initializes the list of available stocks for trading. This method populates the stock
     * storage with a predefined set of stock entities, ready for player interaction.
     */
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
     * Begins the trading process, allowing the player to engage in stock market transactions.
     * This method orchestrates the trading session, presenting stock options to the player and
     * processing their buy or sell decisions.
     *
     * @throws GameException if an error occurs that prevents the continuation of the trading process.
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
     * Manages the purchase calculations for a selected stock and updates the player's portfolio
     * and financial status accordingly. It ensures that transactions are valid and that the player
     * has sufficient assets for the purchase.
     *
     * @param index   The index of the currently selected stock in the available stocks list.
     * @param current The stock entity being purchased.
     * @throws GameException If the transaction is invalid due to insufficient assets or other constraints.
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
     * Utility method for generating a random integer within a specified range. This can be used
     * for selecting random stocks or simulating market movements.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random integer between the specified min (inclusive) and max (exclusive).
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
