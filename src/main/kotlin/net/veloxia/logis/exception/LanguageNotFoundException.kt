package net.veloxia.logis.exception

/**
 * Exception thrown when a language JSON file cannot be found at the given path.
 *
 * @param filePath The path to the missing language file
 * @author xRookieFight
 */
class LanguageNotFoundException(
    filePath: String
) : Exception("Language not found in path: $filePath")
