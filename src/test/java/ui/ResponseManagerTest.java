package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(outputStreamCaptor.toString().contains("Current round: 5") &&
                outputStreamCaptor.toString().contains(GREEN));
    }

    @Test
    void printCurrentRound_nearEndingRoundInput_printsRoundMsg() {
        int testRound = 16;
        ResponseManager.printCurrentRound(testRound);
        assertTrue(outputStreamCaptor.toString().contains("Current round: 16") &&
                outputStreamCaptor.toString().contains(YELLOW));
    }

    @Test
    void printCurrentRound_lastRoundInput_printsRoundMsg() {
        int testRound = 20;
        ResponseManager.printCurrentRound(testRound);
        assertTrue(outputStreamCaptor.toString().contains("LAST") &&
                outputStreamCaptor.toString().contains(RED));
    }

    @Test
    void indentPrint_validInput_printsIndentedMsg() {
        String testMessage = "Hello, World!";
        ResponseManager.indentPrint(testMessage);
        assertEquals(INDENTATION + "\n" + testMessage + INDENTATION,
                outputStreamCaptor.toString().trim());
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
                INDENTATION, outputStreamCaptor.toString().trim());
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
                INDENTATION, outputStreamCaptor.toString().trim());
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
                INDENTATION, outputStreamCaptor.toString().trim());
    }

    @Test
    void printActionLeft_oneActionLeft_printsActionMsg() {
        int testActionsLeft = 1;
        ResponseManager.printActionLeft(testActionsLeft);
        assertTrue(outputStreamCaptor.toString().contains("You have 1 action left"));
    }

    @Test
    void endOfRoundMessage_validInput_printsEndOfRoundMsg() {
        ResponseManager.endOfRoundMessage(6);
        assertTrue(outputStreamCaptor.toString().contains("End of Round 6") &&
                outputStreamCaptor.toString().contains(ORANGE));
    }

}
