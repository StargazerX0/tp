package minigame;

import exception.InvalidMoveException;
import ui.ResponseManager;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Tic Tac Toe game class that supports single player against an AI.
 * It includes functionality for playing the game, checking win conditions, and managing the game state.
 */
public class TicTacToe implements MiniGame {
    private static final Logger logger = Logger.getLogger("TacLog");
    protected char[][] board = new char[3][3];
    private char playerMark;
    private char aiMark;
    private char currentMark;
    protected boolean isGameOver = false;
    private boolean isDraw = false;

    static {
        logger.setLevel(Level.OFF); // Disable logging for production
    }

    /**
     * Constructs a new Tic Tac Toe game with the specified player mark.
     * Initializes the game board and sets up player and AI marks.
     *
     * @param playerMark the mark ('X' or 'O') that the player chooses to play with.
     */
    public TicTacToe(char playerMark) {
        this.playerMark = playerMark;
        this.aiMark = (playerMark == 'X') ? 'O' : 'X';
        this.currentMark = playerMark;
        initializeBoard();
    }

    /**
     * Initializes the game board by filling it with dashes ('-') to signify empty spaces.
     */
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    /**
     * Prints the current state of the board to the console.
     */
    private void printBoard() {
        StringBuilder boardInfo = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardInfo.append(board[i][j]).append(" ");
            }
            boardInfo.append("\n");
        }
        ResponseManager.printBoard(boardInfo.toString());
    }

    /**
     * Checks all possible win conditions on the board (rows, columns, diagonals).
     *
     * @return true if a win condition is met, false otherwise.
     */
    protected boolean checkForWin() {
        return checkRowForWin() || checkColumnForWin() || checkDiagonalForWin();
    }

    /**
     * Checks for a win condition in any row.
     *
     * @return true if any row is a win condition, false otherwise.
     */
    protected boolean checkRowForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkCellForWin(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a win condition in any column.
     *
     * @return true if any column is a win condition, false otherwise.
     */
    protected boolean checkColumnForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkCellForWin(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a win condition in both diagonals.
     *
     * @return true if any diagonal is a win condition, false otherwise.
     */
    protected boolean checkDiagonalForWin() {
        return checkCellForWin(board[0][0], board[1][1], board[2][2]) ||
            checkCellForWin(board[0][2], board[1][1], board[2][0]);
    }

    /**
     * Helper method to check if three cells (possibly making a row, column, or diagonal) are the same as the
     * current mark.
     *
     * @param c1 Character in cell 1
     * @param c2 Character in cell 2
     * @param c3 Character in cell 3
     * @return true if all cells match the current mark, false otherwise.
     */
    private boolean checkCellForWin(char c1, char c2, char c3) {
        return (c1 == currentMark && c2 == currentMark && c3 == currentMark);
    }

    /**
     * Places a mark at the specified position on the board and checks if the game is over.
     *
     * @param row the row to place the mark in
     * @param column the column to place the mark in
     * @throws InvalidMoveException if the move is invalid (e.g., outside the board or spot already taken)
     */
    private void placeMark(int row, int column) throws InvalidMoveException {
        if (row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == '-') {
            board[row][column] = playerMark;
            printBoard();
            checkGameOver();
        } else {
            throw new InvalidMoveException("Move at (" + (row + 1) + ", " + (column + 1) + ") is invalid.\n");
        }
    }

    /**
     * Determines the current game status based on the board state.
     *
     * @return 1 if player wins, -1 if AI wins, 0 otherwise.
     */
    public int getStatus() {
        if (isGameOver) {
            return (isDraw ? 0 : (currentMark == playerMark ? 1 : -1));
        }
        return 0;
    }

    /**
     * Checks if all cells on the board are filled, indicating that no more moves can be made.
     * This is used to determine if the game has ended in a draw when there are no empty cells left
     * and no player has won yet.
     *
     * @return true if the board is completely filled, false otherwise.
     */
    protected boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the game is over either by win condition or draw.
     */
    private void checkGameOver() {
        if (checkForWin()) {
            isGameOver = true;
            outputResult();
        } else if (isBoardFull()) {
            isDraw = true;
            isGameOver = true;
            outputResult();
        }
    }

    /**
     * Handles the AI's turn, placing a mark in a random empty spot.
     */
    private void placeAIMark() {
        Random rand = new Random();
        int row;
        int column;
        do {
            row = rand.nextInt(3);
            column = rand.nextInt(3);
        } while (board[row][column] != '-');

        board[row][column] = aiMark;
        printBoard();
        checkGameOver();
    }

    /**
     * Outputs the result of the game based on whether it ended in a draw or a win.
     */
    public void outputResult() {
        if (isDraw) {
            ResponseManager.indentPrint("It's a draw!\n");
        } else {
            ResponseManager.indentPrint("Congratulations, " + (currentMark == playerMark ? "you win!" :
                "AI wins!") + "\n");
        }
    }

    /**
     * Initiates and manages the Tic Tac Toe gameplay loop, controlling the sequence of player and AI turns.
     * This method handles game initialization, turn management, input validation, and game termination checks.
     *
     * Steps in the gameplay loop:
     * 1. Display the current game board.
     * 2. Prompt the player for a move, validating the input to ensure it is within the allowed range and
     * targeting an empty cell.
     * 3. Update the board with the player's move, check for a win or draw, and display the updated board.
     * 4. If the game continues, allow the AI to make a move, update the board, and check for game termination
     * conditions.
     * 5. Repeat steps 2-4 until the game ends due to a win or a draw.
     *
     * Inputs are checked for validity (must be integers within 1-3). Incorrect inputs prompt a re-entry without
     * penalty.
     * The game alternates turns between the player and AI until a win condition or a draw is achieved.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        printBoard();
        while (!isGameOver) {
            try {
                ResponseManager.indentPrint("Player " + playerMark + ", enter your move (row [1-3] column [1-3]):\n");
                int row = scanner.nextInt() - 1;
                int column = scanner.nextInt() - 1;
                placeMark(row, column);

                if (!isGameOver) {
                    ResponseManager.indentPrint("AI's turn!\n");
                    placeAIMark();
                }
            } catch (InputMismatchException e) {
                ResponseManager.indentPrint("Invalid input! Please enter numbers only.\n");
                scanner.nextLine(); // clear the buffer
            } catch (InvalidMoveException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
    }
}
