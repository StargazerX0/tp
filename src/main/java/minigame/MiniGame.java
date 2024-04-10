package minigame;

/**
 * Defines the common structure for mini-games within the application.
 * All mini-games must implement methods to start the game and output the result.
 */
public interface MiniGame {

    /**
     * Starts the mini-game, handling all necessary initialization and gameplay logic.
     */
    public void startGame();

    /**
     * Outputs the result of the mini-game, such as displaying scores or win/lose messages.
     */
    public void outputResult();
}
