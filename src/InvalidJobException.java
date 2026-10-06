/**
 * Custom exception for invalid job data (e.g. blank name, negative values).
 * Suits the Tailoring Shop business domain.
 */
public class InvalidJobException extends Exception {
    public InvalidJobException(String message) {
        super(message);
    }
}
