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
     * Initialises the list of available stocks for trading.
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
     * Begins the trading process by presenting a random stock with respective information.
     *
     * @throws GameException if player input an invalid number or does not have enough money.
     */
    public void play() throws GameException {
        if (stocksAvailable.isEmpty()) {
            setUp();
        }
        int index = getRandomNumber(0, stocksAvailable.size() - 1);
        Stock current = stocksAvailable.get(index);
        current.printInfo(playerProfile);
        stockCalculation(current);

    }

    /**
     * Calculates the money required to purchase a certain number of stocks and add them
     * to player profile.
     *
     * @param current the stock that is going to be purchased.
     * @throws GameException if the player have invalid input or not enough money.
     */
    private void stockCalculation(Stock current) throws GameException {
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
     * Generates a random integer within a certain range.
     *
     * @param min the lower bound of the range.
     * @param max the upper bound of the range.
     * @return the random integer generated within the range.
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
