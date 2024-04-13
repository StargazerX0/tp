package minigame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link TicTacToe}.
 * Ensures that the game initializes correctly and that win conditions and board state checks function as expected.
 */
class TicTacToeTest {

    @InjectMocks
    private TicTacToe ticTacToe;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Sets up the environment before each test.
     * Initializes a new Tic TacToe game with 'X' as the player's mark and redirects system output to capture it
     * for assertions.
     */
    @BeforeEach
    void setup() {
        ticTacToe = new TicTacToe('X');  // Assume player chooses 'X'
        System.setOut(new PrintStream(outContent));  // Redirect system output to capture for verification
    }

    /**
     * Tests that the game is initialized properly without any win conditions met and with the game board set to start.
     */
    @Test
    void testGameInitialization() {
        assertNotNull(ticTacToe, "TicTacToe instance should be created.");
        assertEquals(0, ticTacToe.getStatus(), "Game should start without a win or draw.");
    }

    /**
     * Verifies that a win can be correctly identified across any of the rows when all cells in a row contain t
     * he same mark.
     */
    @Test
    void testRowWin() {
        ticTacToe.board[0][0] = 'X';
        ticTacToe.board[0][1] = 'X';
        ticTacToe.board[0][2] = 'X';
        assertTrue(ticTacToe.checkRowForWin(), "Player should win by filling the first row.");
    }

    /**
     * Verifies that a win can be correctly identified across any of the columns when all cells in a column contain
     * the same mark.
     */
    @Test
    void testColumnWin() {
        ticTacToe.board[0][0] = 'X';
        ticTacToe.board[1][0] = 'X';
        ticTacToe.board[2][0] = 'X';
        assertTrue(ticTacToe.checkColumnForWin(), "Player should win by filling the first column.");
    }

    /**
     * Verifies that a win can be correctly identified across either of the diagonals when all cells in a diagonal
     * contain the same mark.
     */
    @Test
    void testDiagonalWin() {
        ticTacToe.board[0][0] = 'X';
        ticTacToe.board[1][1] = 'X';
        ticTacToe.board[2][2] = 'X';
        assertTrue(ticTacToe.checkDiagonalForWin(), "Player should win by filling the main diagonal.");
    }

    /**
     * Ensures that the game correctly identifies that no win has been achieved under certain conditions.
     */
    @Test
    void testNoWin() {
        ticTacToe.board[0][0] = 'X';
        ticTacToe.board[1][1] = 'O';
        ticTacToe.board[2][2] = 'X';
        assertFalse(ticTacToe.checkForWin(), "There should be no win yet.");
    }

    /**
     * Checks if the method can accurately detect when the board is completely filled, indicating a full board
     * without any empty spaces.
     */
    @Test
    void testBoardFullness() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToe.board[i][j] = 'X';
            }
        }
        assertTrue(ticTacToe.isBoardFull(), "The board should be considered full.");
    }

    /**
     * Checks if the method correctly identifies that the board is not full when at least one cell remains empty.
     */
    @Test
    void testBoardNotFull() {
        ticTacToe.board[0][0] = 'X';
        ticTacToe.board[0][1] = 'O';
        ticTacToe.board[0][2] = '-';
        assertFalse(ticTacToe.isBoardFull(), "The board should not be considered full.");
    }
}
