package exception;

public class LockedFeatureException extends GameException {
    public LockedFeatureException() {
        super("u r trying to access a locked feature!" +
                "\nPlease upgrade to unlock\n");
    }
}
