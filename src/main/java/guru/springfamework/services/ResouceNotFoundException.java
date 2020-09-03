package guru.springfamework.services;

public class ResouceNotFoundException extends RuntimeException {

    public ResouceNotFoundException() {
    }

    public ResouceNotFoundException(String message) {
        super(message);
    }

    public ResouceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResouceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResouceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
