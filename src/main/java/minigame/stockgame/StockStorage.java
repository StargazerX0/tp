package minigame.stockgame;

import exception.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockStorage {
    private final List<Stock> stocksAvailable = new ArrayList<>();
    private boolean completeTrade = false;

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

    public void play() throws GameException {
        if (stocksAvailable.isEmpty()) {
            setUp();
        }
        int index = getRandomNumber(0, stocksAvailable.size() - 1);
        Stock current = stocksAvailable.get(index);
        current.printInfo();
        int totalGain = stockCalculation(index, current);

    }

    private int stockCalculation(int index, Stock current) throws GameException{
        Scanner scanner = new Scanner(System.in);
        while (!completeTrade) {
            try {
                System.out.println("How many stock do you want to purchase? Input 0 if you want none");
                int response = Integer.parseInt(scanner.nextLine());
                if (response < 0) {
                    throw new GameException("Please input a number greater than 0 if you want to purchase stocks");
                } else {
                    completeTrade = true;
                }
                return current.investmentGain(response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter in the instructed format: positive integers only!");
            }
        }
        return 0;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
