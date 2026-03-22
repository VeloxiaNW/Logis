package net.veloxia.logis

import kotlinx.serialization.json.*
import net.veloxia.logis.exception.LanguageNotFoundException
import net.veloxia.logis.objects.LanguageData
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Central language system for loading and retrieving translations.
 * Made for Veloxia Network and only compatible for Veloxia Network translations.
 * @see "https://github.com/VeloxiaNW/locale" for more information
 * @author xRookieFight
 */
object Logis {

    private val cache = ConcurrentHashMap<String, LanguageData>()
    private const val DEFAULT_LANG = "en_US"

    /**
     * Loads a language JSON file into the cache.
     *
     * @param langCode The language code (e.g., "en_US")
     * @param filePath Path to the JSON language file
     * @throws LanguageNotFoundException if the file does not exist
     */
    fun loadLanguage(langCode: String, filePath: String) {
        val file = File(filePath)

        if (!file.exists()) throw LanguageNotFoundException(filePath)

        val json = file.readText()
        val data = Json.decodeFromString<LanguageData>(json)
        cache[langCode] = data
    }

    /**
     * Gets language from cache, uses fallback language if language not found
     *
     * @param langCode The language code
     * @param key Language key
     * @param args Arguments for replacements
     * @return Language string, default key
     */
    fun get(langCode: String, key: String, args: Map<String, String>): String {
        val lang = cache[langCode] ?: cache[DEFAULT_LANG]
        var translation = lang?.translations?.get(key) ?: key

        args.forEach { (key, value) -> translation = translation.replace(key, value) }

        return translation
    }

    /**
     * Gets language from cache, uses fallback language if language not found
     *
     * @param langCode The language code
     * @param key Language key
     * @return Language string, default key
     */
    fun get(langCode: String, key: String): String {
        val lang = cache[langCode] ?: cache[DEFAULT_LANG]
        return lang?.translations?.get(key) ?: key
    }

    /**
     * Retrieves the full LanguageFile object from the cache.
     *
     * @param langCode The language code to retrieve
     * @return The LanguageFile object, or null if not loaded
     */
    fun getLanguageInfo(langCode: String): LanguageData? = cache[langCode]

    /**
     * Clears language cache. Usually used when disabling the main plugin.
     */
    fun clearCache() = cache.clear()

    /**
     * Retrieves default language string
     *
     * @return Default language string
     */
    fun getDefaultLanguage() : String = DEFAULT_LANG

    /**
     * Retrieves all cache data
     *
     * @return Cache data
     */
    fun getAllData() : Map<String, LanguageData> = cache.toMap()
}