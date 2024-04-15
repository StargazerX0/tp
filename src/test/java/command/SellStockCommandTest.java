package command;

import exception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SellStockCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;
    private final PlayerProfile playerWithEnoughMoney =
            new PlayerProfile("test", "test", 100, 10000, 1, true);
    private final PlayerProfile playerWithoutEnoughMoney =
            new PlayerProfile("test", "test", 100, 100, 1, true);
    private final SellStockCommand workCommand = new SellStockCommand();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_playerWithEnoughMoneyButNoStock() throws GameException {
        SellStockCommand sellStockCommand = new SellStockCommand();
        System.setOut(new PrintStream(outContent));
        sellStockCommand.execute(playerWithEnoughMoney);
        assertTrue(outContent.toString().contains("You have nothing to sell! \n"));
    }

    @Test
    void isExit_emptyInput_returnsFalse() {
        StockCommand stockCommand = new StockCommand();
        assertFalse(stockCommand.isExit());
    }

    @Test
    void canGenerateEvent_emptyInput_returnsTrue() {
        StockCommand stockCommand = new StockCommand();
        assertTrue(stockCommand.isAnAction());
    }


}
