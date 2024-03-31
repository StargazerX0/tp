package minigame.stockgame;

import exception.GameException;

public class Main {
    public static void main(String[] args) throws GameException {
        new StockStorage().play();
    }
}
