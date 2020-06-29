package by.halatsevich.string.exception;

public class InputDataException extends Exception {
    public InputDataException() {
    }

    public InputDataException(String message) {
        super(message);
    }

    public InputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputDataException(Throwable cause) {
        super(cause);
    }
}
