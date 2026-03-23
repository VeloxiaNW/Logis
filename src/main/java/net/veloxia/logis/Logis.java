package net.veloxia.logis;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.veloxia.logis.exception.LanguageNotFoundException;
import net.veloxia.logis.objects.LanguageData;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 /**
 * Central language system for loading and retrieving translations.
 * Made for Veloxia Network and only compatible for Veloxia Network translations.
 * @see "https://github.com/VeloxiaNW/locale" for more information
 *
 * @author xRookieFight
 * @since 23/03/2026
 */
public class Logis {

    private static final ConcurrentHashMap<String, LanguageData> cache = new ConcurrentHashMap<>();
    private static final String DEFAULT_LANG = "en_US";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Loads a language JSON file into the cache.
     *
     * @param langCode The language code (e.g., "en_US")
     * @param filePath Path to the JSON language file
     * @throws LanguageNotFoundException if the file does not exist
     */
    public static void loadLanguage(String langCode, String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new LanguageNotFoundException(filePath);
        }

        try {
            LanguageData data = MAPPER.readValue(file, LanguageData.class);
            cache.put(langCode, data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse language file: " + filePath, e);
        }
    }

    /**
     * Gets language from cache, uses fallback language if language not found
     *
     * @param langCode The language code
     * @param key Language key
     * @param args Arguments for replacements
     * @return Language string, default key
     */
    public static String get(String langCode, String key, Map<String, String> args) {
        LanguageData lang = cache.getOrDefault(langCode, cache.get(DEFAULT_LANG));

        String translation = key;
        if (lang != null && lang.getTranslations() != null) {
            translation = lang.getTranslations().getOrDefault(key, key);
        }

        for (Map.Entry<String, String> entry : args.entrySet()) {
            translation = translation.replace(entry.getKey(), entry.getValue());
        }

        return translation;
    }

    /**
     * Gets language from cache, uses fallback language if language not found
     *
     * @param langCode The language code
     * @param key Language key
     * @return Language string, default key
     */
    public static String get(String langCode, String key) {
        LanguageData lang = cache.getOrDefault(langCode, cache.get(DEFAULT_LANG));

        if (lang != null && lang.getTranslations() != null) {
            return lang.getTranslations().getOrDefault(key, key);
        }

        return key;
    }

    /**
     * Retrieves the full LanguageData object from the cache.
     *
     * @param langCode The language code to retrieve
     * @return The LanguageFile object, or null if not loaded
     */
    public static LanguageData getLanguageInfo(String langCode) {
        return cache.get(langCode);
    }

    /**
     * Clears language cache. Usually used when disabling the main plugin.
     */
    public static void clearCache() {
        cache.clear();
    }

    /**
     * Retrieves all cache data
     * @return The cache data
     */
    public static Map<String, LanguageData> getAllData() {
        return Map.copyOf(cache);
    }
}