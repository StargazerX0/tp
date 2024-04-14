package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.ResponseManager.RED;
import static ui.ResponseManager.RESET;
import static ui.ResponseManager.INDENTATION;
import static ui.ResponseManager.BLUE;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.YELLOW;
import static ui.ResponseManager.ORANGE;

class ResponseManagerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void echoChosenIndustry_validInput_printsChosenMsg() {
        String testIndustry = "Robotics";
        ResponseManager.echoChosenIndustry(testIndustry);
        assertEquals("You have chosen Robotics", outputStreamCaptor.toString().trim());
    }

    @Test
    void printCurrentRound_normalRoundInput_printsRoundMsg() {
        int testRound = 5;
        ResponseManager.printCurrentRound(testRound);
        assertEquals("Current round: 5\nyou have " +
                        GREEN + "15" + RESET +
                        " rounds left before the game ends!\n" +
                        INDENTATION + "\n", outputStreamCaptor.toString());
    }

    @Test
    void printCurrentRound_nearEndingRoundInput_printsRoundMsg() {
        int testRound = 16;
        ResponseManager.printCurrentRound(testRound);
        assertEquals("Current round: 16\nyou have " +
                YELLOW + "4" + RESET +
                " rounds left before the game ends!\n" +
                INDENTATION + "\n", outputStreamCaptor.toString());
    }

    @Test
    void printCurrentRound_lastRoundInput_printsRoundMsg() {
        int testRound = 20;
        ResponseManager.printCurrentRound(testRound);
        assertEquals("THIS IS THE " + RED + "LAST" + RESET +
                " ROUND!\n" + ResponseManager.INDENTATION + "\n", outputStreamCaptor.toString());
    }

    @Test
    void indentPrint_validInput_printsIndentedMsg() {
        String testMessage = "Hello, World!";
        ResponseManager.indentPrint(testMessage);
        assertEquals(INDENTATION + "\n" + testMessage + INDENTATION + "\n",
                outputStreamCaptor.toString());
    }

    @Test
    void printRoundEarned_positiveEarned_printsEarnedMsg() {
        int testCompanyEarned = 100;
        int testBondEarned = 200;
        int testCryptoEarned = 300;
        ResponseManager.printRoundEarned(testCompanyEarned, testBondEarned, testCryptoEarned);
        assertEquals(INDENTATION + "\n" +
                BLUE + "ROUND SUMMARY:\n" + RESET +
                "Company Earned: $" + GREEN + "100" + RESET + "\n" +
                "Bond Earned: $" + GREEN + "200" + RESET + "\n" +
                "Crypto Earned: $" + GREEN + "300" + RESET + "\n" +
                "\n" +
                "Total Earned: $" + GREEN + "600" + RESET + "\n" +
                INDENTATION + "\n", outputStreamCaptor.toString());
    }

    @Test
    void printRoundEarned_negativeEarned_printsEarnedMsg() {
        int testCompanyEarned = -100;
        int testBondEarned = -200;
        int testCryptoEarned = -300;
        ResponseManager.printRoundEarned(testCompanyEarned, testBondEarned, testCryptoEarned);
        assertEquals(INDENTATION + "\n" +
                BLUE + "ROUND SUMMARY:\n" + RESET +
                "Company Lost: -$" + RED + "100" + RESET + "\n" +
                "Bond Lost: -$" + RED + "200" + RESET + "\n" +
                "Crypto Lost: -$" + RED + "300" + RESET + "\n" +
                "\n" +
                "Total Lost: -$" + RED + "600" + RESET + "\n" +
                INDENTATION + "\n", outputStreamCaptor.toString());
    }

    @Test
    void printRoundEarned_zeroEarned_printsEmptyMsg() {
        int testCompanyEarned = 0;
        int testBondEarned = 0;
        int testCryptoEarned = 0;
        ResponseManager.printRoundEarned(testCompanyEarned, testBondEarned, testCryptoEarned);
        assertEquals(INDENTATION + "\n" +
                BLUE + "ROUND SUMMARY:\n" + RESET +
                "No earnings or losses this round!\n" +
                INDENTATION + "\n", outputStreamCaptor.toString());
    }

    @Test
    void printActionLeft_oneActionLeft_printsActionMsg() {
        int testActionsLeft = 1;
        ResponseManager.printActionLeft(testActionsLeft);
        assertEquals("You have 1 action left\n" +
                "Input your action! If needed, type 'help' for more info\n" +
                INDENTATION + "\n", outputStreamCaptor.toString());
    }

    @Test
    void endOfRoundMessage_validInput_printsEndOfRoundMsg() {
        ResponseManager.endOfRoundMessage(6);
        assertEquals(ORANGE + "End of round 6!\n" + INDENTATION + RESET + "\n",
                outputStreamCaptor.toString());
    }

}
