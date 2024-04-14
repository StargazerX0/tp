package randomevent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PositiveEventTest {
    private static final int ITERATION = 100;
    private final PlayerProfile advancedProfile =
            new PlayerProfile("test", "test", 100,
                    50000, 1, true);
    private final PositiveEvent event = new PositiveEvent(1.0);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void triggerEvent_emptyInput_printsEvent() {
        for (int i = 0; i < ITERATION; i++) {
            event.triggerEvent(advancedProfile);
        }

        //verify bonus event
        assertTrue(outContent.toString().contains("Your boss gave you a bonus!"));
        //verify family event
        assertTrue(outContent.toString().contains("Your had a great dinner with your family!"));
        //verify subsidy event
        assertTrue(outContent.toString().contains("Due to pandemic, the government gave you subsidy of $3000!"));
        //verify revenue increase event
        assertTrue(outContent.toString().contains("Your company products sold very well this month!"));
    }
}