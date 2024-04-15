package command;

import exception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockCommandTest {
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
    void execute_playerWithoutEnoughMoney() {
        StockCommand stockCommand = new StockCommand();
        assertThrows(NoSuchElementException.class, () -> stockCommand.execute(playerWithoutEnoughMoney));
    }

    @Test
    void execute_checkNoExecution() throws GameException {
        SellStockCommand sellStockCommand = new SellStockCommand();
        System.setOut(new PrintStream(outContent));
        sellStockCommand.execute(playerWithoutEnoughMoney);
        assertFalse(outContent.toString().contains("StockCommand executed"));
    }

    @Test
    void execute_checkPurchaseCompletion() throws GameException {
        StockCommand stockCommand = new StockCommand();
        System.setOut(new PrintStream(outContent));
        String simulatedInput = "10";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        stockCommand.execute(playerWithEnoughMoney);
        assertTrue(outContent.toString().contains("StockCommand executed"));
    }

    @Test
    void isExit_emptyInput_returnsFalse() {
        assertFalse(workCommand.isExit());
    }

    @Test
    void canGenerateEvent_emptyInput_returnsTrue() {
        assertTrue(workCommand.isAnAction());
    }



}
