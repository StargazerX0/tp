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
    protected boolean isGameOver = false;
    private char playerMark;
    private char aiMark;
    private char currentMark;
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
        currentMark = playerMark;
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
        currentMark = aiMark;
        Random rand = new Random();
        int row;
        int column;
        boolean foundEmpty = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    foundEmpty = true;
                    break;
                }
            }
            if (foundEmpty) {
                break;
            }
        }
        assert foundEmpty : "No empty space available for AI to place mark";

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
            ResponseManager.indentPrint((currentMark == playerMark ? "Congratulations, you win!" :
                "Sorry, AI wins!") + "\n");
        }
    }

    /**
     * Manages the Tic Tac Toe gameplay loop. Alternates turns between the player and the AI, handling the game logic.
     * The loop continues until a player wins or the board is filled completely (draw).
     * Inputs are validated for correctness, and incorrect inputs allow for re-entry without penalties.
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
