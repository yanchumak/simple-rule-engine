package engine.validator;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public static ValidationException createFormatted(String format, Object... args) {
        return new ValidationException(String.format(format, args));
    }
}
