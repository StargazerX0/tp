package randomevent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NegativeEventTest {
    private static final int ITERATION = 100;
    private final PlayerProfile advancedProfile =
            new PlayerProfile("test", "test", 100,
                    50000, 1, true);
    private final NegativeEvent event = new NegativeEvent(1.0);
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

        //verify economy crisis event
        assertTrue(outContent.toString().contains("Economy crisis!"));
        //verify sick event
        assertTrue(outContent.toString().contains("You got sick!"));
        //verify wallet lost event
        assertTrue(outContent.toString().contains("You lost your wallet!"));
        //verify employee riot event
        assertTrue(outContent.toString().contains("Company employees are not satisfied with their salary!"));
    }

    @Test
    void triggerEvent_emptyInputAndUnhealthyPlayer_printsEvent() {
        for (int i = 0; i < ITERATION; i++) {
            advancedProfile.loseHealth(90);
            event.triggerEvent(advancedProfile);
            advancedProfile.setHealth(100);
        }

        //verify sick event
        assertTrue(outContent.toString().contains("You got sick!"));
        //other events should not be triggered
        assertFalse(outContent.toString().contains("Economy crisis!"));
        assertFalse(outContent.toString().contains("You lost your wallet!"));
        assertFalse(outContent.toString().contains("Company employees are not satisfied with their salary!"));
    }

    @Test
    void triggerEvent_emptyInputAndLowSalary_printsEvent() {
        for (int i = 0; i < ITERATION; i++) {
            advancedProfile.updateSalary(-500);
            event.triggerEvent(advancedProfile);
        }

        //verify employee riot event
        assertTrue(outContent.toString().contains("Company employees are not satisfied with their salary!"));
        //other events should not be triggered
        assertFalse(outContent.toString().contains("Economy crisis!"));
        assertFalse(outContent.toString().contains("You got sick!"));
        assertFalse(outContent.toString().contains("You lost your wallet!"));
    }
}
