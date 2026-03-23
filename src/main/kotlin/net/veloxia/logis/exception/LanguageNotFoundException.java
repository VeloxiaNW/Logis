package net.veloxia.logis.exception;

/**
 * Exception thrown when a language JSON file cannot be found at the given path.
 *
 * @param filePath The path to the missing language file
 * @author xRookieFight
 * @since 23/03/2026
 */
public class LanguageNotFoundException extends RuntimeException {

    public LanguageNotFoundException(String filePath) {
        super("Language not found in path: " + filePath);
    }
}