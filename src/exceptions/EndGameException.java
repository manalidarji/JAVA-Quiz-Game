package exceptions;

public class EndGameException extends Exception {

    public EndGameException() {
    }

    public EndGameException(String message) {
        super(message);
    }
}