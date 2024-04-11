package gamelogic;

/**
 * Main class for the EconoCraft game. This class contains the entry point for the game,
 * kicking off the initialization and starting the game loop.
 */
public class EconoCraft {

    /**
     * Main entry point for the EconoCraft game. This method starts the game by initializing
     * the game environment and then entering the main game loop.
     *
     * @param args Command-line arguments, not used in this application.
     */
    public static void main(String[] args) {
        EconoCraftLogic.initializeGame()
                .startEcono();
    }
}
