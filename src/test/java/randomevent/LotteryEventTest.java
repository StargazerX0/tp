package randomevent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.PlayerProfile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LotteryEventTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PlayerProfile playerProfile = new PlayerProfile("test", "test");

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void triggerEvent_emptyInputPlayerReject_printsRejectMsg() {
        LotteryEvent event = new LotteryEvent(1.0);
        String simulatedInput = "no";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        event.triggerEvent(playerProfile);

        assertTrue(outContent.toString().contains("You have chosen not to buy a ticket."));
    }

    @Test
    void triggerEvent_emptyInputPlayerAccept_printsAcceptMsg() {
        LotteryEvent event = new LotteryEvent(1.0);
        String simulatedInput = "yes";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        event.triggerEvent(playerProfile);

        assertTrue(outContent.toString().contains("You have bought a ticket for $2000."));
    }
}
