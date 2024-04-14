package minigame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrueFalseGameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private TrueFalseGame trueFalseGame;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testTrueFalseGame_inputBothCorrectAnswers() {
        TrueFalseGame tfGame = new TrueFalseGame();
        tfGame.testActivate();
        tfGame.correctnessCheck(3, "T");
        tfGame.correctnessCheck(4, "T");
        assertEquals(tfGame.getCorrectCount(), 2);
    }

    @Test
    void testTrueFalseGame_checkQuestionValidity_correctAnswer() {
        TrueFalseGame tfGame = new TrueFalseGame();
        tfGame.testActivate();
        tfGame.correctnessCheck(3, "T");
        assertEquals(tfGame.getCorrectCount(), 1);
    }

    @Test
    void testTrueFalseGame_checkQuestionValidity_incorrectAnswer() {
        TrueFalseGame tfGame = new TrueFalseGame();
        tfGame.testActivate();
        tfGame.correctnessCheck(4, "F");
        assertEquals(tfGame.getCorrectCount(), 0);
    }

    @Test
    void testTrueFalseGame_checkQuestionValidity_wrongFormat() {
        TrueFalseGame tfGame = new TrueFalseGame();
        tfGame.testActivate();
        tfGame.correctnessCheck(8, "asd ack kjasdnkja");
        assertEquals(tfGame.getCorrectCount(), 0);
    }

    @Test
    void testTrueFalseGame_checkMultipleInvalidInput() {
        TrueFalseGame tfGame = new TrueFalseGame();
        String simulatedInput = "adnfkascnsk" + "\n" + "akjsd ca ";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        tfGame.startGame();
        assertEquals(tfGame.getCorrectCount(), 0);
    }

}