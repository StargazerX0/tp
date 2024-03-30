package exception;

public class LockedFeatureException extends GameException {
    public LockedFeatureException() {
        super("u r trying to access a locked feature!" +
                "\nPlease complete the previous goal to unlock " +
                "SHOP, COMPANY or STOCK feature!");
    }
}
